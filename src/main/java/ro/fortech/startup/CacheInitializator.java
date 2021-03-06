package ro.fortech.startup;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import ro.fortech.caching.AccountCachingService;
import ro.fortech.caching.VehicleCachingService;
import ro.fortech.caching.VehicleEnhanceCachingService;

/**
 * Class used to initiate the cache in the application. The cache is initiated
 * at application server start.
 *
 */
@Singleton
@Startup
public class CacheInitializator {

	@EJB
	private VehicleCachingService vehicleCacheService;
	
	@EJB
	private AccountCachingService accountCachingService;
	
	@EJB
	private VehicleEnhanceCachingService vehicleEnhanceCacheService;
	
	@PostConstruct
	public void initCache(){
		System.out.println("CacheInitializator: startup class");		
		accountCachingService.initUserCache();
		vehicleCacheService.initVehicleCache();
		vehicleEnhanceCacheService.initVehiclesEnhancedCache();
	}
	
}
