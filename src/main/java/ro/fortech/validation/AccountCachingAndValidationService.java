package ro.fortech.validation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

import ro.fortech.cache.SearchCache;
import ro.fortech.cache.UserCache;
import ro.fortech.credentials.LoginCredentials;
import ro.fortech.history.SearchSave;
import ro.fortech.model.User;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.search.VehicleSearchResponse;
import ro.fortech.services.VehicleService;

@Stateless
public class AccountCachingAndValidationService {

	private static final String BASIC_SECURITY_PREFIX = "Basic ";
	private static final String DEFAULT_VALUE = " ";
	private static final String AUTHORIZATION = "Authorization";
	private static final String Y_NO_HAVE_ACCOUNT = "Y NO have ACCOUNT";
	private static final String USER_CONFIRMED = "User confirmed";
	private static final String INVALID_LOCATION_SEARCH_OR_VEHICLE_TYPE = "Invalid location search or vehicle type. They are not provided! Check again after you provided them!";
	private static final String ALL_GOOD = "All good !";
	private static final String SOMETHING_WENT_WRONG = "Something went wrong, list is null";

	@EJB
	private UserCache userCache;
	
	@Context
	private HttpServletResponse response;
	
	@Inject
	private SearchCache searchCache;
	
	@Inject
	@Named("fakeVehicleServiceImpl")
	private VehicleService fakeService;
	
	
	/**
	 * Method used to initiate the user cache map.
	 * @return - status 201 if its created or status 400 if it fails in creating
	 * the list.
	 */
	public Response initUsersInCacheMemory() {
		User user = null;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.setCountry("Romania" + i);
			user.setId(1);
			user.setUsername("user" + i);
			user.setPassword("pass" + i);
			userCache.getUserCache().put(user.getUsername(), user);
		}
		
		if(userCache.getUserCache().size() > 0){
			return Response.status(Response.Status.CREATED).build();
		}else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
		
	
	/**
	 * Method used to check if an user is present in the system, and if he is then
	 * his search history will be displayed, search history which is present on each users
	 * session.
	 * @param accountToken - the unique token generated for every account that an user will be identified
	 * @return - the user search history list if account is present or an unauthorized attempt
	 */
	public Response validateUserForSearchHistory(String accountToken) {
		response.setHeader(AUTHORIZATION, accountToken);
		if (!userCache.isUserActive(accountToken)) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} else {
			return Response.status(Response.Status.OK).entity(searchCache.getSearchRequests().get(accountToken)).build();
		}
	}
	
	
	/**
	 * 
	 * @param accountToken
	 * @return
	 */
	public Response valiadateUserForSavedSearch(String accountToken) {
		response.setHeader(AUTHORIZATION, accountToken);
		if (!userCache.isUserActive(accountToken)) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} else {
			return Response.status(Response.Status.OK).entity(searchCache.getSearchSaveCache().get(accountToken)).build();
		}
	}
	
	
	/**
	 * 
	 * @param accountToken
	 * @param saveName
	 * @param search
	 * @return
	 */
	public Response validateUserForSavingSearchHistrory(String accountToken, String saveName, VehicleSearchRequest search) {
		response.setHeader(AUTHORIZATION, accountToken);
		List<SearchSave> searchSaves = null;
		if (!userCache.isUserActive(accountToken)) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} else {
			SearchSave searchSave = new SearchSave();
			searchSave.setName(saveName);
			searchSave.setRequest(search);
			if(searchCache.getSearchSaveCache().get(accountToken) == null){
				searchSaves = new ArrayList<>();
				searchSaves.add(searchSave);
				searchCache.getSearchSaveCache().put(accountToken, searchSaves);
			}else{
				searchCache.getSearchSaveCache().get(accountToken).add(searchSave);
			}
			return Response.status(Response.Status.OK).entity(searchCache.getSearchSaveCache().get(accountToken)).build();
		}
	}
	
	
	/**
	 * 
	 * @param credentials
	 * @return
	 */
	public Response generateUserToken(LoginCredentials credentials) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		String toEncode = BASIC_SECURITY_PREFIX + username + ":" + password;
		String encodedCredentials = null;
		byte[] token = null;
		boolean isPresent = false;
		User user = userCache.getUser(username);
		if (user == null) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(Y_NO_HAVE_ACCOUNT).build();
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
			return Response.status(Response.Status.UNAUTHORIZED).entity(Y_NO_HAVE_ACCOUNT).build();
		}
		response.setHeader(AUTHORIZATION, encodedCredentials);
		return Response.status(200).entity(USER_CONFIRMED).build();
	}
	
	
	/**
	 * 
	 * @param accountToken
	 * @param search
	 * @return
	 */
	public Response validateUserforSearch(String accountToken, VehicleSearchRequest search) {
		response.setHeader(AUTHORIZATION, accountToken);
		if (!userCache.isUserActive(accountToken)) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} else {
			VehicleSearchResponse searchResponse = new VehicleSearchResponse();
			if (search.getLocation().equals(DEFAULT_VALUE) || search.getVehicleType().getType().equals(DEFAULT_VALUE)) {
				searchResponse.setErrorMessage(INVALID_LOCATION_SEARCH_OR_VEHICLE_TYPE);
			} else {
				List<Vehicle> vehicles = fakeService.getVehicles(search);
				if (vehicles == null) {
					searchResponse.setErrorMessage(SOMETHING_WENT_WRONG);
				} else {
					searchResponse.setVehicleCount(vehicles.size());
					searchResponse.setErrorMessage(ALL_GOOD);
					searchResponse.setVehicles(vehicles);
				}
			}
			initUserSearchCache(search, accountToken);
			return Response.status(Response.Status.OK).entity(searchResponse).build();
		}
	}
	
	private void initUserSearchCache(VehicleSearchRequest search, String acountToken) {
		if (searchCache.getSearchRequests().get(acountToken) == null) {
			List<VehicleSearchRequest> vehicleSearchRequests = new ArrayList<VehicleSearchRequest>();
			vehicleSearchRequests.add(search);
			searchCache.getSearchRequests().put(acountToken, vehicleSearchRequests);
		} else {
			searchCache.getSearchRequests().get(acountToken).add(search);
		}
	}
}
