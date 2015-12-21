package ro.fortech.validation;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.cache.UserCache;

@Stateless
public class AccountValidationService {

	@EJB
	private UserCache userCache;

	@PostConstruct
	public void init() {
		System.out.println("AccountValidationService: Stateless");
	}
	
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
			return true;
		}
	}
}
