package ro.fortech.search.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

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
	
	@Context
	private HttpServletResponse response;

	@PostConstruct
	public void init() {
		System.out.println("SearchResponseService: Stateless");
	}
	
	public List<VehicleSearchRequest> getUserSearchHistory(String accountToken) {
		return historySearchCache.getSearchHistory().get(decodeUserToken(accountToken));
	}

	public List<SearchSave> getUserSavedSearchHistory(String accountToken) {
		return searchCache.getSearchSaveCache().get(decodeUserToken(accountToken));
	}

	/**
	 * 
	 * @param accountToken
	 * @param saveName
	 * @param search
	 * @return
	 */
	public List<SearchSave> saveUserSearch(String accountToken, String saveName, VehicleSearchRequest search) {
		List<SearchSave> searchSaves = null;
		String decodedToken = decodeUserToken(accountToken);
		SearchSave searchSave = new SearchSave();
		searchSave.setName(saveName);
		searchSave.setRequest(search);
		if (searchCache.getSearchSaveCache().get(decodedToken) == null) {
			searchSaves = new ArrayList<>();
			searchSaves.add(searchSave);
			searchCache.getSearchSaveCache().put(decodedToken, searchSaves);
		} else {
			searchCache.getSearchSaveCache().get(decodedToken).add(searchSave);
		}
		return searchCache.getSearchSaveCache().get(decodedToken);
	}

	/**
	 * 
	 * @param credentials
	 * @return
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
		response.setHeader(Constants.AUTHORIZATION, encodedCredentials);
		return encodedCredentials;
	}
	
	private String decodeUserToken(String token){
		byte[] decodedToken = Base64.decodeBase64(token);
		String decodedTokenStringForm = new String(decodedToken);	
		String[] splittedToken = decodedTokenStringForm.split("[\\s\\:]+");
		return splittedToken[1];
	}

	/**
	 * 
	 * @param accountToken
	 *            -
	 * @param search
	 * @return
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

	private void initUserSearchCache(VehicleSearchRequest search, String accountToken) {
		historySearchCache.addHistorySearch(decodeUserToken(accountToken), search);
	}

	public VehicleEnhanceSearchResponse getVehicleEnhancedByFin(String accountToken, String fin) {
		VehicleEnhanceSearchResponse searchResponse = new VehicleEnhanceSearchResponse();

		VehicleEnhanced vehicle = searchService.getVehicleEnhancedByFin(fin, fakeService.getVehicles());
		if (vehicle == null) {
			searchResponse.setErrorMessage(Constants.SOMETHING_WENT_WRONG);
		} else {
			searchResponse.setVehicleEnhancedCount(1);
			searchResponse.setErrorMessage(Constants.ALL_GOOD);
			searchResponse.setVehicleEnhanceds(Arrays.asList(vehicle));
		}
		VehicleSearchRequest search = new VehicleSearchRequest();
		search.setFin(fin);
		return searchResponse;
	}
	
}
