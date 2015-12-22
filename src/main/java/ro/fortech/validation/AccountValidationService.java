package ro.fortech.validation;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.cache.UserCache;

/**
 * Class used in oder to check if a user has confirmed his account. That is if
 * the user has logged at least once.
 *
 */
@Stateless
public class AccountValidationService {

	@EJB
	private UserCache userCache;

	@PostConstruct
	public void init() {
		System.out.println("AccountValidationService: Stateless");
	}
	
	/**
	 * Method used to check if a user is present in the system. In order to
	 * check if the user is present his token is used.
	 * 
	 * @param accountToken
	 *            - the token used in order to identify the user
	 * @return - true if the user id present or false if not
	 */
	public boolean isUserValid(String accountToken) {
		if (!userCache.isUserActive(accountToken)) {
			return false;
		} else {
			return true;
		}
	}
}
