package ro.fortech.caching;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.cache.UserCache;
import ro.fortech.model.User;

/**
 * Class used to initiate the user cache.
 *
 */
@Stateless
public class AccountCachingService {
	
	@EJB
	private UserCache userCache;
	
	@PostConstruct
	public void init() {
		System.out.println("AccountCachingService: Stateless");
	}
	
	public void initUserCache() {
		User user = null;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.setCountry("Romania");
			user.setId(1);
			user.setUsername("user" + i);
			user.setPassword("pass" + i);
			userCache.getUserCache().put(user.getUsername(), user);
		}
	}
	
}
