package ro.fortech.startup;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import ro.fortech.caching.AccountCachingService;
import ro.fortech.caching.VehicleCachingService;

@Singleton
@Startup
public class CacheInitializator {
	
	@EJB
	private VehicleCachingService vehicleCacheService;
	
	@EJB
	private AccountCachingService accountCachingService;
	
	@PostConstruct
	public void initCache(){
		System.out.println("CacheInitializator: startup class");		
		accountCachingService.initUserCache();
		vehicleCacheService.initVehicleCache();
	}
	
}
