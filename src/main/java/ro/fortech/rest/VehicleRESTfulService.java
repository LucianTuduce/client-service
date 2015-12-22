package ro.fortech.rest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ro.fortech.constants.Constants;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.search.response.SearchResponseService;
import ro.fortech.services.VehicleService;
import ro.fortech.validation.AccountValidationService;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

@Path("/vehicle")
@Stateless
public class VehicleRESTfulService {
	
	@Context
	private HttpServletResponse response;
	
	@EJB
	private AccountValidationService accountValidation;
	
	@EJB
	private SearchResponseService searchResponseService;
	
	@EJB(beanName = "fakeVehicleServiceImpl")
	private VehicleService fakeService;

	@PostConstruct
	public void init() {
		System.out.println("VehicleRESTfulService: Stateless");
	}
	
	@POST
	@Path("/filtered")
	@Produces("application/json")
	public Response getVehiclesBySearchCriteria(@HeaderParam("Authorization") String accountToken, VehicleSearchRequest search) {
		if(accountValidation.isUserValid(accountToken)){
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return Response.status(Response.Status.OK).entity(searchResponseService.getFilteredVehiclesBySearchCriteria(accountToken, search)).build();
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
		
	@GET
	@Path("/search/history")
	@Produces("application/json")
	public Response getSearchHistory(@HeaderParam("Authorization") String accountToken) {
		if(accountValidation.isUserValid(accountToken)){
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return Response.status(Response.Status.OK).entity(searchResponseService.getUserSearchHistory(accountToken)).build();
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/search/history/saved")
	@Produces("application/json")
	public Response getSearchSavedHistory(@HeaderParam("Authorization") String accountToken) {
		if(accountValidation.isUserValid(accountToken)){
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return Response.status(Response.Status.OK).entity(searchResponseService.getUserSavedSearchHistory(accountToken)).build();
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@POST
	@Path("/search/history/save/{saveName}")
	@Produces("application/json")
	public Response saveSearchRequest(@HeaderParam("Authorization") String accountToken, @PathParam("saveName") String saveName, VehicleSearchRequest search) {
		if(accountValidation.isUserValid(accountToken)){
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return Response.status(Response.Status.OK).entity(searchResponseService.saveUserSearch(accountToken, saveName, search)).build();
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/enhanced/{fin}")
	@Produces("application/json")
	public Response getVehicleByFin(@PathParam("fin") String fin, @HeaderParam("Authorization") String accountToken){
		if(accountValidation.isUserValid(accountToken)){
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return Response.status(Response.Status.OK).entity(searchResponseService.getVehicleEnhancedByFin(fin)).build();
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	public Response addVehicle(@HeaderParam("Authorization") String accountToken, VehicleEnhanced vehicle){
		if(accountValidation.isUserValid(accountToken)){
			fakeService.saveVehicleEnhanced(vehicle);
			fakeService.saveVehicle(vehicle.getVehicle());
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return Response.status(Response.Status.OK).build();
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}
