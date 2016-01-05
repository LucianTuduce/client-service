package ro.fortech.rest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ro.fortech.cache.HistorySearchCache;
import ro.fortech.cache.UserCache;
import ro.fortech.constants.Constants;
import ro.fortech.credentials.LoginCredentials;
import ro.fortech.model.User;
import ro.fortech.search.response.SearchResponseService;
import ro.fortech.validation.AccountValidationService;

/**
 * Rest class used to identify the users that are using the application
 *
 */
@Path("/user")
@Stateless
public class UserRESTfulService {

	@EJB
	private AccountValidationService accountValidation;	
	
	@EJB
	private SearchResponseService searchResponseService;	
	
	@EJB
	private UserCache userCache;
	
	@EJB
	private HistorySearchCache historySearchCache;
	
	@Context
	private HttpServletResponse response;
		
	@PostConstruct
	public void init() {
		System.out.println("UserRESTfulService: Stateless");
	}

	/**
	 * Method used in order to authenticate the user based on his credentials.
	 * 
	 * @param credentials
	 *            - the users username and password that will be checked in
	 *            order to see if the user account is valid.
	 * @return - a response to the client that will tell it if the user is valid
	 *         or not
	 */
	@POST
	@Path("/login")
	public Response confirmUser(LoginCredentials credentials){
		String accountToken = searchResponseService.generateAndGetUserToken(credentials);
		if(!accountValidation.isUserValid(accountToken)){
			return Response.status(Response.Status.UNAUTHORIZED).entity(Constants.Y_NO_HAVE_ACCOUNT).build();
		}else{
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return Response.status(Response.Status.OK).build();
		}
	}
	
	/**
	 * Method used to logout the user from the application.
	 * 
	 * @param accountToken
	 *            - the user unique token that is used in order to identify him
	 * @return - success status 200 that the user has logout from the server
	 */
	@POST
	@Path("/logout")
	public Response invalidateUserSession(@HeaderParam("Authorization") String accountToken){
		String decodedAccountToken = searchResponseService.decodeUserToken(accountToken);
		historySearchCache.getSearchHistory().remove(decodedAccountToken);
		userCache.getUserConfirmation().remove(decodedAccountToken);			
		return Response.status(Response.Status.OK).build();
	}
	
	@POST
	@Path("/add")
	public Response addUser(User user){
		if(!userCache.isUsernamePresent(user)){
			userCache.getUserCache().put(user.getUsername(), user);
			return Response.status(Response.Status.CREATED).build();
		}else{
			return Response.status(Response.Status.CONFLICT).build();
		}
	}
}
