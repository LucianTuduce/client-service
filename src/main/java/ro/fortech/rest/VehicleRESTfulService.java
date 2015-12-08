package ro.fortech.rest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ro.fortech.cache.UserCache;
import ro.fortech.cache.VehicleCache;
import ro.fortech.credentials.LoginCredentials;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.validation.AccountCachingAndValidationService;

@Path("/vehicle")
@RequestScoped
public class VehicleRESTfulService {

	@PostConstruct
	public void init() {
		System.out.println("Built: VehicleRESTfulService.");
	}

	@EJB
	private VehicleCache cache;

	@EJB
	private UserCache userCache;
	
	@EJB
	private AccountCachingAndValidationService cachingAndValidationService;

	@POST
	@Path("/users")
	public Response initUserCache() {
		return cachingAndValidationService.initUsersInCacheMemory();
	}

	@POST
	@Path("/filtered")
	@Produces("application/json")
	public Response getVehiclesBySearchCriteria(@HeaderParam("Authorization") String accountToken, VehicleSearchRequest search) {
		return cachingAndValidationService.validateUserforSearch(accountToken, search);
	}

	@GET
	@Path("/search/history")
	@Produces("application/json")
	public Response getSearchHistory(@HeaderParam("Authorization") String accountToken) {
		return cachingAndValidationService.validateUserForSearchHistory(accountToken);
	}

	@GET
	@Path("/search/history/saved")
	@Produces("application/json")
	public Response getSerachSavedHistory(@HeaderParam("Authorization") String accountToken) {
		return cachingAndValidationService.valiadateUserForSavedSearch(accountToken);
	}

	@POST
	@Path("/search/history/save/{saveName}")
	@Produces("application/json")
	public Response saveSearchRequest(@HeaderParam("Authorization") String accountToken, @PathParam("saveName") String saveName, VehicleSearchRequest search) {
		return cachingAndValidationService.validateUserForSavingSearchHistrory(accountToken, saveName, search);
	}

	@POST
	@Path("/token")
	public Response getUserToken(LoginCredentials credentials) {
		return cachingAndValidationService.generateUserToken(credentials);
	}
	
	@GET
	@Path("/search/{fin}")
	@Produces("application/json")
	public Response getVehicleByFin(@PathParam("fin") String fin, @HeaderParam("Authorization") String accountToken){
		return cachingAndValidationService.validateUserforVehicleEnhanceSearch(accountToken, fin);
	}
}
