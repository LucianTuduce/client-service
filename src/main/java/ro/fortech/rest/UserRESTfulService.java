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
import ro.fortech.search.response.SearchResponseService;
import ro.fortech.validation.AccountValidationService;

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
	
	
	@POST
	@Path("/login")
	public Response confirmUser(LoginCredentials credentials){
		String accountToken = searchResponseService.generateAndGetUserToken(credentials);
		if(!accountValidation.isUserValid(accountToken)){
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return Response.status(Response.Status.UNAUTHORIZED).entity(Constants.Y_NO_HAVE_ACCOUNT).build();
		}else{
			return Response.status(Response.Status.OK).build();
		}
	}
	
	@POST
	@Path("/logout")
	public Response invalidateUserSession(@HeaderParam("Authorization") String accountToken){
		String decodedAccountToken = searchResponseService.decodeUserToken(accountToken);
		historySearchCache.getSearchHistory().remove(decodedAccountToken);
		userCache.getUserConfirmation().remove(decodedAccountToken);			
		return Response.status(Response.Status.OK).build();
	}
	
}
