package ro.fortech.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
			intermediat1 = searchHelper.getVehiclesByLocation(vehicles, search);
			intermediat2 = searchHelper.getVehiclesByModel(intermediat1, search);
			intermediat1.clear();
			intermediat1 = searchHelper.getVehiclesByVehicleType(intermediat2, search);
			intermediat2.clear();
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
			intermediat1 = searchHelper.getVehiclesByMinSellPrice(intermediat2, search);
			intermediat2.clear();
			intermediat2 = searchHelper.getVehiclesByMaxSellPrice(intermediat1, search);
			intermediat1.clear();
			return intermediat2;

		} else {
			for (Vehicle vehicleFin : vehicles) {
				if (vehicleFin.getFin().equals(search.getFin()) && vehicleFin.getLocation().equals(search.getLocation())) {
					searchResultVehicles.add(vehicleFin);
					break;
				}
			}
		}
		return searchResultVehicles;
	}

	@Override
	public VehicleEnhanced getVehicleEnhancedByFin(String fin, List<VehicleEnhanced> vehicleEnhanceds) {
		for (VehicleEnhanced vehicleEnhanced : vehicleEnhanceds) {
			if (vehicleEnhanced.getVehicle().getFin().equals(fin)) {
				return vehicleEnhanced;
			}
		}
		return null;
	}
}
