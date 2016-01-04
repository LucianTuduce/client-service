package ro.fortech.fake;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.cache.VehicleCache;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

/**
 * Class used to perform CRUD operation on the vehicles.
 *
 */
@Stateless(name = "fakeVehicleServiceImpl")
public class FakeVehicleServiceImpl implements VehicleService {

	@EJB(beanName = "vehicleSearchServiceImpl")
	private VehicleSearchService searchService;

	@EJB
	private VehicleCache cache;
	
	@PostConstruct
	public void init() {
		System.out.println("FakeVehicleServiceImpl: Stateless");
	}
	
	@Override
	public Vehicle getVehicle(int idVehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idVehicle) {
		System.out.println("Delete from fake DB successful");

	}

	@Override
	public void update(Vehicle Vehicle) {
		System.out.println("Update into fake DB successful");

	}

	@Override
	public void saveVehicle(Vehicle vehicle) {
		cache.getVehicles().add(vehicle);
		System.out.println("vehicle added to chache");
	}

	@Override
	public void setVehicles(List<Vehicle> vehicles) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Vehicle> getVehicles(VehicleSearchRequest request) {
		return searchService.getSearch(request, cache.getVehicles());
	}
	
	@Override
	public void saveVehicleEnhanced(VehicleEnhanced vehicle) {
		cache.getVehicleEnhanceds().add(vehicle);
		System.out.println("Enhanced Vehicle added to cache");
		
	}
}
