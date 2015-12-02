package ro.fortech.cache;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import ro.fortech.model.User;

@Singleton
public class UserCache {

	@PostConstruct
	public void init() {
		System.out.println("Built: UserCache.");
	}
	
	private Map<String, User> userCache;

	public Map<String, User> getUserCache() {
		return userCache;
	}

	public void setUserCache(Map<String, User> userCache) {
		this.userCache = userCache;
	}
	
}
