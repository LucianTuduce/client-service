package ro.fortech.search.response;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	@Inject
	SearchCache searchCache;

	@EJB(name = "fakeVehicleServiceImpl")
	VehicleService fakeService;

	@Inject
	@Named("vehicleSearchServiceImpl")
	private VehicleSearchService vehicleSerachService;
	
	@Context
	private HttpServletResponse response;

	public Response getUserSearchHistory(String accountToken) {
		return Response.status(Response.Status.OK).entity(searchCache.getSearchRequests().get(accountToken)).build();
	}

	public Response getUserSavedSearchHistory(String accountToken) {
		return Response.status(Response.Status.OK).entity(searchCache.getSearchSaveCache().get(accountToken)).build();
	}

	/**
	 * 
	 * @param accountToken
	 * @param saveName
	 * @param search
	 * @return
	 */
	public Response saveUserSearch(String accountToken, String saveName, VehicleSearchRequest search) {
		List<SearchSave> searchSaves = null;

		SearchSave searchSave = new SearchSave();
		searchSave.setName(saveName);
		searchSave.setRequest(search);
		if (searchCache.getSearchSaveCache().get(accountToken) == null) {
			searchSaves = new ArrayList<>();
			searchSaves.add(searchSave);
			searchCache.getSearchSaveCache().put(accountToken, searchSaves);
		} else {
			searchCache.getSearchSaveCache().get(accountToken).add(searchSave);
		}
		return Response.status(Response.Status.OK).entity(searchCache.getSearchSaveCache().get(accountToken)).build();
	}

	/**
	 * 
	 * @param credentials
	 * @return
	 */
	public Response generateUserToken(LoginCredentials credentials) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		String toEncode = Constants.BASIC_SECURITY_PREFIX + username + ":" + password;
		String encodedCredentials = null;
		byte[] token = null;
		boolean isPresent = false;
		User user = userCache.getUser(username);
		if (user == null) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(Constants.Y_NO_HAVE_ACCOUNT).build();
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
			return Response.status(Response.Status.UNAUTHORIZED).entity(Constants.Y_NO_HAVE_ACCOUNT).build();
		}
		response.setHeader(Constants.AUTHORIZATION, encodedCredentials);
		return Response.status(200).entity(Constants.USER_CONFIRMED).build();
	}

	/**
	 * 
	 * @param accountToken
	 *            -
	 * @param search
	 * @return
	 */
	public Response getFilteredVehiclesBySearchCriteria(String accountToken, VehicleSearchRequest search) {
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
		return Response.status(Response.Status.OK).entity(searchResponse).build();
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

	public Response getVehicleEnhancedByFin(String accountToken, String fin) {
		VehicleEnhanceSearchResponse searchResponse = new VehicleEnhanceSearchResponse();

		VehicleEnhanced vehicle = vehicleSerachService.getVehicleByFin(fin, fakeService.getVehicles());
		if (vehicle == null) {
			searchResponse.setErrorMessage(Constants.SOMETHING_WENT_WRONG);
		} else {
			searchResponse.setVehicleEnhancedCount(1);
			searchResponse.setErrorMessage(Constants.ALL_GOOD);
			searchResponse.setVehicleEnhanceds(Arrays.asList(vehicle));
		}
		VehicleSearchRequest search = new VehicleSearchRequest();
		search.setFin(fin);
		initUserSearchCache(search, accountToken);
		return Response.status(Response.Status.OK).entity(searchResponse).build();
	}
	
}
