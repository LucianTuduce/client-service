package ro.fortech.validation;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import ro.fortech.cache.UserCache;
import ro.fortech.constants.Constants;

@Stateless
public class AccountValidationService {

	@PostConstruct
	public void init() {
		System.out.println("AccountValidationService: Stateless");
	}
	
	
	@EJB
	private UserCache userCache;

	@Context
	private HttpServletResponse response;

	/**
	 * Method used to check if an user is present in the system, and if he is
	 * then his search history will be displayed, search history which is
	 * present on each users session.
	 * 
	 * @param accountToken
	 *            - the unique token generated for every account that an user
	 *            will be identified
	 * @return - the user search history list if account is present or an
	 *         unauthorized attempt
	 */
	public boolean isUserValid(String accountToken) {
		if (!userCache.isUserActive(accountToken)) {
			return false;
		} else {
			response.setHeader(Constants.AUTHORIZATION, accountToken);
			return true;
		}
	}
}
