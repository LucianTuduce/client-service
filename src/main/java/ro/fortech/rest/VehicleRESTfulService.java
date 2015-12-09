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

import ro.fortech.caching.AccountCachingService;
import ro.fortech.credentials.LoginCredentials;
import ro.fortech.pagination.Pagination;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.search.response.SearchResponseService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;
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

	@POST
	@Path("/users")
	public Response initUserCache() {
		return accountCachingService.initUsersInCacheMemory();
	}

	@GET
	@Path("/req")
	@Produces("application/json")
	public VehicleSearchRequest geetReq(){
		VehicleSearchRequest request = new VehicleSearchRequest();
		Pagination pagination = new Pagination();
		pagination.setElemetsPerPage(20);
		pagination.setPageNumber(3);
		request.setFin(" ");
		request.setFuelType(FuelType.DEFAULT);
		request.setLocation("Germany");
		request.setMaxCapacity(0);
		request.setMinCapacity(0);
		request.setMaxPrice(0);
		request.setMinPrice(0);
		request.setVehicleType(VehicleType.CAR);
		request.setMaxYear(0);
		request.setMinYear(0);
		request.setModel(" ");
		request.setPagination(pagination);
		return request;
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
}
