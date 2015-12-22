package ro.fortech.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import ro.fortech.model.User;

/**
 * Class used as a fake database, here all the users are stored.
 *
 */
@Singleton
public class UserCache {

	@PostConstruct
	public void init() {
		System.out.println("UserCache: Singleton");
	}
	
	private Map<String, User> userCache = new HashMap<>();
	
	private Map<String, User> userConfirmation = new HashMap<>();
	
	public Map<String, User> getUserConfirmation() {
		return userConfirmation;
	}

	public void setUserConfirmation(Map<String, User> userConfirmation) {
		this.userConfirmation = userConfirmation;
	}

	public Map<String, User> getUserCache() {
		return userCache;
	}

	public void setUserCache(Map<String, User> userCache) {
		this.userCache = userCache;
	}
	
	public User getUser(String key){
		return userCache.get(key);
	}
	
	public boolean isUserActive(String key){
		if(userConfirmation.get(key) == null){
			return false;
		}else{
			return true;
		}
	}
}
