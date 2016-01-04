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
import ro.fortech.validation.SaveVehicleValidation;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

/**
 * REST service class used to establish connection between the WEB and the
 * application login. This rest class is use for the vehicle part.
 *
 */
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
	
	/**
	 * Method used to get from the backed the filtered vehicles based on a
	 * search criteria and send them to the front-end.
	 * 
	 * @param accountToken
	 *            - the token of the account that is making the search
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - status 200 if the account is ok and all went good or 401 if the
	 *         account is not valid
	 */
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
	
	/**
	 * Method used to get from the back-end the search history for every user
	 * based on the user token and sent it to the front-end.
	 * 
	 * @param accountToken
	 *            - the user token used to get the search history
	 * @return - the search history of an specific user
	 */
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

	/**
	 * Method used to get from the back-end the user saved search history and
	 * send it to the front-end.
	 * 
	 * @param accountToken
	 *            - the token used to identify the each user
	 * @return - the user search saved history that is identified by the token
	 */
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

	/**
	 * Method used to save the user search request.
	 * 
	 * @param accountToken
	 *            - the token used to identify a user in order to get access to
	 *            the application
	 * @param saveName
	 *            - the name of the saved search request
	 * @param search
	 *            - the search request that will be saved in the database
	 * @return - 200 if it was saved successfully or 401 if the user is
	 *         unauthorized.
	 */
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

	/**
	 * Method used to get from the backed the extra information about a a
	 * vehicle based on its fin.
	 * 
	 * @param fin
	 *            - the id that will identify a car
	 * @param accountToken
	 *            - the token of the user that is making the request to the
	 *            server
	 * @return - 200 and the vehicle if the user is authorized or 401 if the
	 *         user is unauthorized
	 */
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
	
	/**
	 * method used to get from the front end a new vehicle information and save
	 * it in the database
	 * 
	 * @param accountToken
	 *            - the token of the user that will be making the request to the
	 *            server
	 * @param vehicle
	 *            - the vehicle that will be added in the system
	 * @return 200 if all went ok or 401 if the user is unauthorized or 406 if the information are null or not ok
	 */
	@POST
	@Path("/add")
	@Consumes("application/json")
	public Response addVehicle(@HeaderParam("Authorization") String accountToken, VehicleEnhanced vehicle){
		Boolean validationVehicle = SaveVehicleValidation.validationForSaveVehicle(vehicle);
		if(accountValidation.isUserValid(accountToken)){
			if(validationVehicle){
				fakeService.saveVehicleEnhanced(vehicle);
				fakeService.saveVehicle(vehicle.getVehicle());
				response.setHeader(Constants.AUTHORIZATION, accountToken);
				return Response.status(Response.Status.OK).build();
			}
			else{
				return Response.status(Response.Status.NOT_ACCEPTABLE).build();
			}
			
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
		
}
