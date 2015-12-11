package ro.fortech.caching;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.cache.UserCache;
import ro.fortech.model.User;

@Stateless
public class AccountCachingService {
	
	@EJB
	private UserCache userCache;
	
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
