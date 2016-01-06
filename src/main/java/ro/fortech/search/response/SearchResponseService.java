package ro.fortech.search.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.codec.binary.Base64;

import ro.fortech.cache.HistorySearchCache;
import ro.fortech.cache.SavedSearchCache;
import ro.fortech.cache.UserCache;
import ro.fortech.constants.Constants;
import ro.fortech.credentials.LoginCredentials;
import ro.fortech.history.SearchSave;
import ro.fortech.model.User;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleEnhanceSearchResponse;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.search.VehicleSearchResponse;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

/**
 * Class used to handle the user request from the server and redirect them to
 * the specific part of the application that is build in order to handle that
 * request.
 *
 */
@Stateless
public class SearchResponseService {
	
	private static final int PROGRAMMERS_SOLUTION = 1;
	private static final int MAGIC_NUMBER_2 = 2;
	private static final int MAGIC_NUMBER_10 = 10;

	@EJB
	private UserCache userCache;
	
	@EJB
	private SavedSearchCache searchCache;
	
	@EJB
	private HistorySearchCache historySearchCache;

	@EJB(beanName = "fakeVehicleServiceImpl")
	private VehicleService fakeService;

	@EJB(beanName = "vehicleSearchServiceImpl")
	private VehicleSearchService searchService;
	

	@PostConstruct
	public void init() {
		System.out.println("SearchResponseService: Stateless");
	}
	
	/**
	 * Method used to get the search history for a specific user. The user is
	 * identified by the method param.
	 * 
	 * @param accountToken
	 *            - the token used to identify the user.
	 * @return - the search history list of a user
	 */
	public List<String> getUserSearchHistory(String accountToken) {
		List<VehicleSearchRequest> searchHistory = historySearchCache.getSearchHistory().get(decodeUserToken(accountToken));
		List<String> userSearchHistory = new ArrayList<String>();
		String search = null;
		
		if(searchHistory == null){
			return userSearchHistory;
		}
		for(VehicleSearchRequest request: searchHistory){
			search = "FIN: " + request.getFin() + ", Location: "
					+ request.getLocation() + ", Model: " + request.getModel()
					+ ", Min_Capacity: " + request.getMinCapacity()
					+ ", Max_Capacity: " + request.getMaxCapacity()
					+ ", Min_Year: " + request.getMinYear() + ", Max_Year: "
					+ request.getMaxYear() + ", Min_Price: "
					+ request.getMinPrice() + ", Max_Price: "
					+ request.getMaxPrice() + ", Fuel_Type: "
					+ request.getFuelType() + ", Vehicle_Type: "
					+ request.getVehicleType();
			userSearchHistory.add(search);
		}
		return userSearchHistory;
	}

	/**
	 * Method used to get the search history that a user saved. The saved
	 * history search of an used is obtain by the token.
	 * 
	 * @param accountToken
	 *            - the token used to identify the user and his saved search
	 * @return - the list of saved car searches
	 */
	public List<SearchSave> getUserSavedSearchHistory(String accountToken) {
		return searchCache.getSearchSaveCache().get(decodeUserToken(accountToken));
	}

	/**
	 * Method used to save a user search history.
	 * 
	 * @param accountToken
	 *            - the token of the user that will make the search save
	 * @param saveName
	 *            - the name of the search save
	 * @param search
	 *            - the search request that will be saved.
	 * @return - should not return anything
	 */
	public List<SearchSave> saveUserSearch(String accountToken, String saveName, VehicleSearchRequest search) {
		List<SearchSave> searchSaves = null;
		String decodedToken = decodeUserToken(accountToken);
		SearchSave searchSave = createSearchSave(saveName, search);
		if (searchCache.getSearchSaveCache().get(decodedToken) == null) {
			searchSaves = new ArrayList<>();
			searchSaves.add(searchSave);
			searchCache.getSearchSaveCache().put(decodedToken, searchSaves);
		} else {
			searchCache.getSearchSaveCache().get(decodedToken).add(searchSave);
		}
		return searchCache.getSearchSaveCache().get(decodedToken);
	}

	private SearchSave createSearchSave(String saveName, VehicleSearchRequest search) {
		SearchSave searchSave = new SearchSave();
		searchSave.setName(saveName);
		searchSave.setRequest(search);
		return searchSave;
	}

	/**
	 * Method used to generate an unique token for every account. Token on which
	 * every user will be identified.
	 * 
	 * @param credentials
	 *            - the user credentials that will help in order to create the
	 *            token
	 * @return - the user encoded account token
	 */
	public String generateAndGetUserToken(LoginCredentials credentials) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		String toEncode = Constants.BASIC_SECURITY_PREFIX +" "+ username + ":" + password;
		String encodedCredentials = null;
		byte[] token = null;
		boolean isPresent = false;
		User user = userCache.getUser(username);
		if (user == null) {
			return null;
		} else if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
			token = Base64.encodeBase64(toEncode.getBytes());
			encodedCredentials = new String(token);
			if (userCache.isUserActive(encodedCredentials)) {
				isPresent = true;
			} else {
				isPresent = true;
				userCache.getUserConfirmation().put(encodedCredentials, user);
			}
		}
		if (!isPresent) {
			return Constants.Y_NO_HAVE_ACCOUNT;
		}
		
		return encodedCredentials;
	}
	
	/**
	 * Method used to decode the user account token.
	 * 
	 * @param token
	 *            - the encoded account token
	 * @return - the decoded token, more exactly the username that was used in
	 *         order to create this token
	 */
	public String decodeUserToken(String token){
		byte[] decodedToken = Base64.decodeBase64(token);
		String decodedTokenStringForm = new String(decodedToken);	
		String[] splittedToken = decodedTokenStringForm.split("[\\s\\:]+");
		return splittedToken[1];
	}

	/**
	 * Method used top create the user vehicle search response. The response
	 * consist of a message telling the status of the response, the vehicles
	 * obtained by applying the filter and the total number of vehicles
	 * obtained.
	 * 
	 * @param accountToken
	 *            - the user token that will the application if the request can
	 *            be made or not, that is the user must be valid in order to
	 *            make a search
	 * @param search
	 *            - the search criteria used to filter the vehicles.
	 * @return - the search response after applying the the login
	 */
	public VehicleSearchResponse getFilteredVehiclesBySearchCriteria(String accountToken, VehicleSearchRequest search) {
		VehicleSearchResponse searchResponse = new VehicleSearchResponse();
		List<Vehicle> vehicles = null;
		List<Vehicle> vehiclesPerPage = null;
		if (search.getLocation().equals(Constants.DEFAULT_VALUE) || search.getVehicleType().getType().equals(Constants.DEFAULT_VALUE)) {
			searchResponse.setErrorMessage(Constants.INVALID_LOCATION_SEARCH_OR_VEHICLE_TYPE);
		} else {
			vehicles = fakeService.getVehicles(search);
			if (vehicles == null) {
				searchResponse.setErrorMessage(Constants.SOMETHING_WENT_WRONG);
			} else {
				int toIndex = (MAGIC_NUMBER_2*search.getPagination().getPageNumber() - PROGRAMMERS_SOLUTION) * MAGIC_NUMBER_10 + MAGIC_NUMBER_10;
				int fromIndex = toIndex - MAGIC_NUMBER_2*MAGIC_NUMBER_10;
				if(fromIndex > vehicles.size()){
					vehiclesPerPage = new ArrayList<>();			
				}else if(toIndex > vehicles.size()){
					vehiclesPerPage = vehicles.subList(fromIndex, vehicles.size());
				}else{
					vehiclesPerPage = vehicles.subList(fromIndex, toIndex);
				}
				searchResponse.setVehicleCount(vehiclesPerPage.size());
				searchResponse.setErrorMessage(Constants.ALL_GOOD);
				searchResponse.setVehicles(vehiclesPerPage);
			}
		}
		
		initUserSearchCache(search, accountToken);
		return searchResponse;
	}

	
	/**
	 * Method used top create the user vehicle search response. The response
	 * consist of a message telling the status of the response, the vehicles
	 * obtained by applying the filter and the total number of vehicles
	 * obtained. The difference from the one above is that this method will not
	 * make a pagination.
	 * 
	 * @param accountToken
	 *            - the user token that will the application if the request can
	 *            be made or not, that is the user must be valid in order to
	 *            make a search
	 * @param search
	 *            - the search criteria used to filter the vehicles.
	 * @return - the search response after applying the the login
	 */
	public VehicleSearchResponse getFilteredVehiclesBySearchCriteriaWithoutPagination(String accountToken, VehicleSearchRequest search) {
		VehicleSearchResponse searchResponse = new VehicleSearchResponse();
		List<Vehicle> vehicles = null;
		if (search.getLocation().equals(Constants.DEFAULT_VALUE) || search.getVehicleType().getType().equals(Constants.DEFAULT_VALUE)) {
			searchResponse.setErrorMessage(Constants.INVALID_LOCATION_SEARCH_OR_VEHICLE_TYPE);
		} else {
			vehicles = fakeService.getVehicles(search);
			if (vehicles == null) {
				searchResponse.setErrorMessage(Constants.SOMETHING_WENT_WRONG);
			} else {
				searchResponse.setErrorMessage(Constants.ALL_GOOD);
				searchResponse.setVehicles(vehicles);
			}
		}
		
		initUserSearchCache(search, accountToken);
		return searchResponse;
	}
	
	private void initUserSearchCache(VehicleSearchRequest search, String accountToken) {
		historySearchCache.addHistorySearch(decodeUserToken(accountToken), search);
	}

	/**
	 * Method used to get extra information about a car based on its fin.
	 * 
	 * @param fin
	 *            - the fin used to identify a car
	 * @return - the car with the provided fin and extra information about it
	 */
	public VehicleEnhanceSearchResponse getVehicleEnhancedByFin(String fin) {
		VehicleEnhanceSearchResponse searchResponse = new VehicleEnhanceSearchResponse();

		VehicleEnhanced vehicle = searchService.getVehicleEnhancedByFin(fin);
		if (vehicle == null) {
			searchResponse.setErrorMessage(Constants.SOMETHING_WENT_WRONG);
		} else {
			searchResponse.setVehicleEnhancedCount(1);
			searchResponse.setErrorMessage(Constants.ALL_GOOD);
			searchResponse.setVehicleEnhanceds(Arrays.asList(vehicle));
		}
		return searchResponse;
	}
	
}
