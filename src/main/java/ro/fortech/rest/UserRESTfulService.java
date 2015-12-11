package ro.fortech.rest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
		
	@PostConstruct
	public void init() {
		System.out.println("UserRESTfulService: Stateless");
	}
	
	
	@POST
	@Path("/login")
	public Response confirmUser(LoginCredentials credentials){
		String accountToken = searchResponseService.generateAndGetUserToken(credentials);
		if(!accountValidation.isUserValid(accountToken)){
			return Response.status(Response.Status.UNAUTHORIZED).entity(Constants.Y_NO_HAVE_ACCOUNT).build();
		}else{
			return Response.status(Response.Status.OK).entity("User confirmed").build();
		}
	}
	
	@POST
	@Path("/logout")
	public Response invalidateUserSession(@HeaderParam("Authorization") String accountToken){
		historySearchCache.getSearchHistory().remove(accountToken);
		userCache.getUserConfirmation().remove(accountToken);			
		return Response.status(Response.Status.OK).build();
	}
	
}
