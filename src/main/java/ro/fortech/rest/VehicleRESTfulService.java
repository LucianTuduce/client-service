package ro.fortech.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import ro.fortech.cache.SearchCache;
import ro.fortech.cache.UserCache;
import ro.fortech.cache.VehicleCache;
import ro.fortech.history.SearchSave;
import ro.fortech.model.User;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.search.VehicleSearchResponse;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

@Path("/vehicle")
@RequestScoped
public class VehicleRESTfulService {

	@PostConstruct
	public void init() {
		System.out.println("Built: VehicleRESTfulService.");
	}

	@Context
	private HttpServletRequest request;

	@Inject
	@Named("fakeVehicleServiceImpl")
	private VehicleService fakeService;

	@Inject
	@Named("vehicleSearchServiceImpl")
	private VehicleSearchService searchService;

	@EJB
	private VehicleCache cache;

	@EJB
	private UserCache userCache;

	@Inject
	private SearchCache searchCache;

	@GET
	@Path("/search")
	@Produces("application/json")
	public VehicleSearchRequest getSearchCriteria() {
		VehicleSearchRequest search = new VehicleSearchRequest();
		search.setFin(" ");
		search.setModel(" ");
		search.setFuelType(FuelType.DIESEL);
		search.setMinCapacity(1200);
		search.setMaxCapacity(7500);
		search.setMinYear(2002);
		search.setMaxYear(20090);
		search.setLocation("Germany");
		search.setMinPrice(0);
		search.setMaxPrice(0);
		search.setVehicleType(VehicleType.DEFAULT);
		return search;
	}

	@GET
	@Path("/get/all/{number}")
	@Produces("application/json")
	public List<Vehicle> getVehicles(@PathParam("number") int number) {
		VehicleSearchRequest search = new VehicleSearchRequest();
		search.setFin(" ");
		search.setModel(" ");
		search.setFuelType(FuelType.DIESEL);
		search.setMinCapacity(1200);
		search.setMaxCapacity(7500);
		search.setMinYear(2002);
		search.setMaxYear(20090);
		search.setLocation(" ");
		search.setMinPrice(0);
		search.setMaxPrice(0);
		search.setVehicleType(VehicleType.DEFAULT);
		return fakeService.getVehicles(search);
	}

	@POST
	@Path("/users")
	public void initUserCache() {
		Map<String, User> map = new HashMap<>();
		User user = null;
		if (userCache.getUserCache() == null) {
			for (int i = 0; i < 10; i++) {
				user = new User();
				user.setCountry("Romania" + i);
				user.setId(1);
				user.setUsername("user" + i);
				user.setPassword("pass" + i);
				map.put("username" + i, user);
			}
			userCache.setUserCache(map);
		}
		
	}

	@POST
	@Path("/filtered/{resultNumber}")
	@Produces("application/json")
	public VehicleSearchResponse getVehiclesBySearchCriteria(
			@PathParam("resultNumber") int number, VehicleSearchRequest search) {
		VehicleSearchResponse response = new VehicleSearchResponse();

		System.out.println(request.getSession());
		if (search.getLocation().equals(" ")
				|| search.getVehicleType().getType().equals(" ")) {
			response.setErrorMessage("Invalid location search or vehicle type. They are not provided! Check again after you provided them!");
		} else {
			List<Vehicle> vehicles = fakeService.getVehicles(search);
			if (vehicles == null) {
				response.setErrorMessage("Something went wrong, list is null");
			} else {
				response.setVehicleCount(vehicles.size());
				response.setErrorMessage("All good !");
				response.setVehicles(vehicles);
			}
		}
		initUserSearchCache(search);

		return response;
	}

	private void initUserSearchCache(VehicleSearchRequest search) {
		List<VehicleSearchRequest> searchRequests = null;
		if (searchCache.getSearchRequests() == null) {
			searchRequests = new ArrayList<VehicleSearchRequest>();
			searchRequests.add(search);
			searchCache.setSearchRequests(searchRequests);
		} else {
			searchRequests = searchCache.getSearchRequests();
			searchRequests.add(search);
			searchCache.setSearchRequests(searchRequests);
		}
	}

	@GET
	@Path("/search/history")
	@Produces("application/json")
	public List<VehicleSearchRequest> getSearchHistory() {

		if (searchCache.getSearchRequests() == null) {
			return new ArrayList<>();
		} else {
			return searchCache.getSearchRequests();
		}
	}

	@GET
	@Path("/search/history/saved/{userID}")
	@Produces("application/json")
	public List<SearchSave> getSerachSavedHistory(
			@PathParam("userID") int userID) {
		if (searchCache.getSearchSaveCache() == null) {
			return new ArrayList<SearchSave>();
		} else {
			return searchCache.getSearchSaveCache().get(userID);
		}
	}

	@POST
	@Path("/search/history/save/{userID}/{name}")
	@Produces("application/json")
	public void saveSearchRequest(@PathParam("userID") int userID,
			@PathParam("name") String name, VehicleSearchRequest search) {
		updateUserSearchList(userID, name, search);
	}

	private void updateUserSearchList(int userID, String name,
			VehicleSearchRequest search) {
		Map<Integer, List<SearchSave>> map = null;
		List<SearchSave> searchSaves = null;
		SearchSave searchSave = new SearchSave();
		searchSave.setName(name);
		searchSave.setRequest(search);
		if (searchCache.getSearchSaveCache() == null) {
			map = new HashMap<>();
			searchSaves = new ArrayList<SearchSave>();
			searchSaves.add(searchSave);
			map.put(userID, searchSaves);
			searchCache.setSearchSaveCache(map);
		} else {
			map = searchCache.getSearchSaveCache();
			searchSaves = map.get(userID);
			if (searchSaves == null) {
				searchSaves = new ArrayList<>();
			}
			searchSaves.add(searchSave);
			map.put(userID, searchSaves);
			searchCache.setSearchSaveCache(map);
		}
	}
}
