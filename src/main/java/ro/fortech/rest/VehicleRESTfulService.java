package ro.fortech.rest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ro.fortech.caching.AccountCachingService;
import ro.fortech.credentials.LoginCredentials;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.search.response.SearchResponseService;
import ro.fortech.services.VehicleCacheService;
import ro.fortech.services.VehicleService;
import ro.fortech.validation.AccountValidationService;

@Path("/vehicle")
@RequestScoped
public class VehicleRESTfulService {

	@PostConstruct
	public void init() {
		System.out.println("Built: VehicleRESTfulService.");
	}
	
	@EJB
	private AccountValidationService accountValidation;
	
	@EJB
	private AccountCachingService accountCachingService;
	
	@EJB
	private SearchResponseService searchResponseService;
	
	@EJB(name = "fakeVehicleServiceImpl")
	private VehicleService fakeService;
	
	@EJB
	private VehicleCacheService vehicleCacheService;

	@POST
	@Path("/users")
	public Response initUserCache() {
		return accountCachingService.initUsersInCacheMemory();
	}

	@POST
	@Path("/filtered")
	@Produces("application/json")
	public Response getVehiclesBySearchCriteria(@HeaderParam("Authorization") String accountToken, VehicleSearchRequest search) {
		if(accountValidation.isUserValid(accountToken)){
			return searchResponseService.getFilteredVehiclesBySearchCriteria(accountToken, search);
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	@PUT
	@Path("/cache/vehicles")
	@Produces("aplication/json")
	public Response initVehicleCache(){
		vehicleCacheService.initVehicleCache();
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	@Path("/search/history")
	@Produces("application/json")
	public Response getSearchHistory(@HeaderParam("Authorization") String accountToken) {
		if(accountValidation.isUserValid(accountToken)){
			return searchResponseService.getUserSearchHistory(accountToken);
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/search/history/saved")
	@Produces("application/json")
	public Response getSearchSavedHistory(@HeaderParam("Authorization") String accountToken) {
		if(accountValidation.isUserValid(accountToken)){
			return searchResponseService.getUserSavedSearchHistory(accountToken);
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@Path("/search/history/save/{saveName}")
	@Produces("application/json")
	public Response saveSearchRequest(@HeaderParam("Authorization") String accountToken, @PathParam("saveName") String saveName, VehicleSearchRequest search) {
		if(accountValidation.isUserValid(accountToken)){
			return searchResponseService.saveUserSearch(accountToken, saveName, search);
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@Path("/token")
	public Response getUserToken(LoginCredentials credentials) {
		return searchResponseService.generateUserToken(credentials);
	}
	
	@GET
	@Path("/search/{fin}")
	@Produces("application/json")
	public Response getVehicleByFin(@PathParam("fin") String fin, @HeaderParam("Authorization") String accountToken){
		if(accountValidation.isUserValid(accountToken)){
			return searchResponseService.getVehicleEnhancedByFin(accountToken, fin);
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	public Response addVehicle(@HeaderParam("Authorization") String accountToken, Vehicle vehicle){
		if(accountValidation.isUserValid(accountToken)){
			fakeService.saveVehicle(vehicle);
			return Response.status(Response.Status.OK).build();
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}
