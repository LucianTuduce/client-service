package ro.fortech.beans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ro.fortech.cache.HistorySearchCache;
import ro.fortech.cache.UserCache;
import ro.fortech.constants.Constants;
import ro.fortech.credentials.LoginCredentials;
import ro.fortech.search.VehicleSearchResult;
import ro.fortech.search.response.SearchResponseService;
import ro.fortech.services.SearchRequestService;
import ro.fortech.validation.AccountValidationService;

/**
 * Class used to check the user credentials with the ones that are present in
 * the database.
 *
 */
@Named
@RequestScoped
public class UserBean {

	@EJB
	private SearchResponseService searchResponseService;
	
	@EJB
	private AccountValidationService accountValidation;	
	
	@EJB
	private UserCache userCache;
	
	@EJB
	private HistorySearchCache historySearchCache;
	
	@EJB
	private SearchRequestService searchRequestService;
	
	@EJB
	private VehicleSearchResult searchResult;
	
	
	/**
	 * Check the credentials that are provided in the JSF page in order to
	 * authenticate a user.
	 * 
	 * @param username
	 *            - the user username for the account
	 * @param password
	 *            - the user password for the account
	 * @return - a string that will redirect to the index page if the account is
	 *         good or will keep the user on the same page if the account is
	 *         invalid
	 * @throws IOException 
	 */
	public String login(String username, String password) throws IOException{
		System.out.println(username);
		System.out.println(password);
		clearFileds();
		LoginCredentials credentials = new LoginCredentials();
		credentials.setUsername(username);
		credentials.setPassword(password);
		String accountToken = searchResponseService.generateAndGetUserToken(credentials);
		if(!accountValidation.isUserValid(accountToken)){
			FacesContext.getCurrentInstance().addMessage("myform:username", new FacesMessage("Invalid", "Invalid Username or password"));
			FacesContext.getCurrentInstance().addMessage("myform:password", new FacesMessage("Invalid", "Invalid Username or password"));
			return "loginJSF.xhtml?faces-redirect=true";
		}else {	
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute(Constants.AUTHORIZATION, accountToken);
		return "IndexJSF.xhtml?faces-redirect=true";
		}
	}

	public String logout() {
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String accountToken = (String) httpSession.getAttribute(Constants.AUTHORIZATION);
		String decodedAccountToken = searchResponseService.decodeUserToken(accountToken);
		historySearchCache.getSearchHistory().remove(decodedAccountToken);
		userCache.getUserConfirmation().remove(decodedAccountToken);
		searchResult.getSearchedVehicles().remove(decodedAccountToken);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		clearFileds();
		return "/loginJSF.xhtml?faces-redirect=true";
	}
	
	private void clearFileds(){
		searchRequestService.setSearchFin(null);
		searchRequestService.setSearchFuelType(null);
		searchRequestService.setSearchLocation(null);
		searchRequestService.setSearchMaxCapacity(0);
		searchRequestService.setSearchMinCapacity(0);
		searchRequestService.setSearchMaxPrice(0);
		searchRequestService.setSearchMinPrice(0);
		searchRequestService.setSearchMaxYear(0);
		searchRequestService.setSearchMinYear(0);
		searchRequestService.setSearchModel(null);
		searchRequestService.setSearchVehicleType(null);
	}
}
