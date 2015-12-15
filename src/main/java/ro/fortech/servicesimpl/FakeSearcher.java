package ro.fortech.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.cache.VehicleCache;
import ro.fortech.def.value.DefaultValues;
import ro.fortech.helpers.SearchHelper;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

@Stateless(name = "vehicleSearchServiceImpl")
public class FakeSearcher implements VehicleSearchService {

	@EJB(beanName = "fakeVehicleServiceImpl")
	private VehicleService vehicleService;

	@EJB
	private SearchHelper searchHelper;
	
	@EJB
	private VehicleCache vehicleCache;

	@PostConstruct
	public void init() {
		System.out.println("FakeSearcher: Stateless");
	}
	
	@Override
	public List<Vehicle> getSearch(VehicleSearchRequest search, List<Vehicle> vehicles) {
		List<Vehicle> intermediat1 = null;
		List<Vehicle> intermediat2 = null;
		List<Vehicle> searchResultVehicles = new ArrayList<>();

		if (search.getFin().equals(DefaultValues.FIN_DEFAULT.getDef())) {
			intermediat1 = searchHelper.getVehiclesByModel(vehicles, search);
			intermediat2 = searchHelper.getVehiclesByFuelType(intermediat1, search);
			intermediat1.clear();
			intermediat1 = searchHelper.getVehiclesByMinEngineCapacity(intermediat2, search);
			intermediat2.clear();
			intermediat2 = searchHelper.getVehiclesByMaxEngineCapacity(intermediat1, search);
			intermediat1.clear();
			intermediat1 = searchHelper.getVehiclesByMinBuildYear(intermediat2, search);
			intermediat2.clear();
			intermediat2 = searchHelper.getVehiclesByMaxBuildYear(intermediat1, search);
			intermediat1.clear();
			intermediat1 = searchHelper.getVehiclesByLocation(intermediat2, search);
			intermediat2.clear();
			intermediat2 = searchHelper.getVehiclesByMinSellPrice(intermediat1, search);
			intermediat1.clear();
			intermediat1 = searchHelper.getVehiclesByMaxSellPrice(intermediat2, search);
			intermediat2.clear();
			intermediat2 = searchHelper.getVehiclesByVehicleType(intermediat1, search);
			intermediat1.clear();
			intermediat1 = searchHelper.getVehiclesByLocation(intermediat2, search);
			intermediat2.clear();
			return intermediat1;

		} else {
			for (Vehicle vehicleFin : vehicles) {
				if (vehicleFin.getFin().equals(search.getFin())) {
					searchResultVehicles.add(vehicleFin);
					break;
				}
			}
		}
		return searchResultVehicles;
	}

	@Override
	public VehicleEnhanced getVehicleEnhancedByFin(String fin) {
		for (VehicleEnhanced vehicleEnhanced : vehicleCache.getVehicleEnhanceds()) {
			if (vehicleEnhanced.getVehicle().getFin().equals(fin)) {
				return vehicleEnhanced;
			}
		}
		return null;
	}
}
