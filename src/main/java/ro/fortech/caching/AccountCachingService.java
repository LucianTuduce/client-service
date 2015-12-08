package ro.fortech.caching;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import ro.fortech.cache.UserCache;
import ro.fortech.model.User;

@Stateless
public class AccountCachingService {

	
	@EJB
	private UserCache userCache;
	
	/**
	 * Method used to initiate the user cache map.
	 * @return - status 201 if its created or status 400 if it fails in creating
	 * the list.
	 */
	public Response initUsersInCacheMemory() {
		User user = null;
		for (int i = 0; i < 10; i++) {
			user = new User();
			user.setCountry("Romania" + i);
			user.setId(1);
			user.setUsername("user" + i);
			user.setPassword("pass" + i);
			userCache.getUserCache().put(user.getUsername(), user);
		}
		
		if(userCache.getUserCache().size() > 0){
			return Response.status(Response.Status.CREATED).build();
		}else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
}
