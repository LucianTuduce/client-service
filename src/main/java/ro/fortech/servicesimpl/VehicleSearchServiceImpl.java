package ro.fortech.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import ro.fortech.def.value.DefaultValues;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearch;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;

@Stateless
public class VehicleSearchServiceImpl implements VehicleSearchService {

	@EJB(beanName = "FakeVehicleServiceImpl")
	private VehicleService vehicleService;

	@Override
	public List<Vehicle> getSearch(VehicleSearch search) {
		List<Vehicle> vehicles = vehicleService.initVehicleList();
		List<Vehicle> intermediat1 = null;
		List<Vehicle> intermediat2 = null;
		List<Vehicle> searchResultVehicles = new ArrayList<>();

		if (search.getFin().equals(DefaultValues.FIN_DEFAULT.getDef())) {
			if (search.getModel().equals(DefaultValues.MODEL_DEFAULT.getDef())) {
				intermediat1 = new ArrayList<>(vehicles);
			} else {
				intermediat1 = new ArrayList<>();
				for (Vehicle vehicle : vehicles) {
					if (vehicle.getModel().equals(search.getModel())) {
						intermediat1.add(vehicle);
					}
				}
			}

			if (search.getFuelType().getFuel().equals(DefaultValues.FUEL_TYPE_DEFAULT.getDef())) {
				intermediat2 = new ArrayList<>(intermediat1);
				intermediat1.clear();
			} else {
				intermediat2 = new ArrayList<>();
				for (Vehicle vehicle : intermediat1) {
					if (vehicle.getFuelType().equals(search.getFuelType())) {
						intermediat2.add(vehicle);
					}
				}
				intermediat1.clear();
			}
/////////////////////////////////////////////
			if (search.getMinCapacity() == DefaultValues.MIN_CAPACITY_DEFAULT.getDefValue()) {
				intermediat1 = new ArrayList<>(intermediat2);
				intermediat2.clear();
			} else {
				for (Vehicle vehicle : intermediat2) {
					if (vehicle.getEngineCapacity() >= search.getMinCapacity()) {
						intermediat1.add(vehicle);
					}
				}
				intermediat2.clear();
			}
////////////////////////
			if (search.getMaxCapacity() == DefaultValues.MAX_CAPACITY_DEFAULT.getDefValue()) {
				intermediat2 = new ArrayList<Vehicle>(intermediat1);
				intermediat1.clear();
			} else {

				for (Vehicle vehicle : intermediat1) {
					if (vehicle.getEngineCapacity() <= search.getMaxCapacity()) {
						intermediat2.add(vehicle);
					}
				}
				intermediat1.clear();
			}
///////////////////////
			if (search.getMinYear() == DefaultValues.MIN_YEAR_DEAFULT.getDefValue()) {
				intermediat1 = new ArrayList<Vehicle>(intermediat2);
				intermediat2.clear();
			} else {
				for (Vehicle vehicle : intermediat2) {
					if (vehicle.getYear() >= search.getMinYear()) {
						intermediat1.add(vehicle);
					}
				}
				intermediat2.clear();
			}
/////////////////////////////
			if (search.getMaxYear() == DefaultValues.MAX_YEAR_DEFAULT.getDefValue()) {
				intermediat2 = new ArrayList<Vehicle>(intermediat1);
				intermediat1.clear();
			} else {
				for (Vehicle vehicle : intermediat1) {
					if (vehicle.getYear() <= search.getMaxYear()) {
						intermediat2.add(vehicle);
					}
				}
				intermediat1.clear();
			}
///////////////////////
			if (search.getLocation().equals(DefaultValues.LOCATION_DEFAULT.getDef())) {
				intermediat1 = new ArrayList<>(intermediat2);
				intermediat2.clear();
			} else {
				for (Vehicle vehicle : intermediat2) {
					if (vehicle.getLocation().equals(search.getLocation())) {
						intermediat1.add(vehicle);
					}
				}
				intermediat2.clear();
			}
///////////////////////
			if (search.getPriceMax() == DefaultValues.MAX_PRICE_DEFAULT.getDefValue()) {
				intermediat2 = new ArrayList<Vehicle>(intermediat1);
				intermediat1.clear();
			} else {
				for (Vehicle vehicle : intermediat1) {
					if (vehicle.getPrice() <= search.getPriceMax()) {
						intermediat2.add(vehicle);
					}
				}
				intermediat1.clear();
			}
////////////////////////////////
			if (search.getPriceMin() == DefaultValues.MIN_PRICE_DEFAULT.getDefValue()) {
				intermediat1 = new ArrayList<>(intermediat2);
				intermediat2.clear();
			} else {
				for (Vehicle vehicle : intermediat2) {
					if (vehicle.getPrice() >= search.getPriceMin()) {
						intermediat1.add(vehicle);
					}
				}
				intermediat2.clear();
			}
//////////////////////////////////
			if (search.getVehicleType().getType().equals(DefaultValues.VEHICLE_TYPE_DEFAULT.getDef())) {
				intermediat2 = new ArrayList<Vehicle>(intermediat1);
				intermediat1.clear();
			} else {
				for (Vehicle vehicle : intermediat1) {
					if (vehicle.getVehicleType().equals(search.getVehicleType())) {
						intermediat2.add(vehicle);
					}
				}
				intermediat1.clear();
			}

			if (intermediat1.size() == 0) {
				return intermediat2;
			} else {
				return intermediat1;
			}

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
	public List<Vehicle> getSearchWithColections(VehicleSearch search) {

		List<Vehicle> vehicles = vehicleService.generateRandomVehicles(100000);

		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getFin().equals(DefaultValues.FIN_DEFAULT.getDef())) {
					return true;
				} else {
					if (((Vehicle) v).getFin().equals(search.getFin())) {
						return true;
					}
					return false;
				}
			}
		});

		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getModel().equals(DefaultValues.MODEL_DEFAULT.getDef())) {
					return true;
				} else {
					if (((Vehicle) v).getModel().equals(search.getModel())) {
						return true;
					}
					return false;
				}
			}
		});
		
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getFuelType().getFuel().equals(DefaultValues.FUEL_TYPE_DEFAULT.getDef())) {
					return true;
				} else {
					if (((Vehicle) v).getFuelType().getFuel().equals(search.getFuelType().getFuel())) {
						return true;
					}
					return false;
				}
			}
		});
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getMinCapacity() == DefaultValues.MIN_CAPACITY_DEFAULT.getDefValue()) {
					return true;
				} else {
					if (((Vehicle) v).getEngineCapacity() >= search.getMinCapacity()) {
						return true;
					}
					return false;
				}
			}
		});
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getMaxCapacity() == DefaultValues.MAX_CAPACITY_DEFAULT.getDefValue()) {
					return true;
				} else {
					if (((Vehicle) v).getEngineCapacity() <= search.getMaxCapacity()) {
						return true;
					}
					return false;
				}
			}
		});
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getMinYear() == DefaultValues.MIN_YEAR_DEAFULT.getDefValue()) {
					return true;
				} else {
					if (((Vehicle) v).getYear() >= search.getMinYear()) {
						return true;
					}
					return false;
				}
			}
		});
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getMinYear() == DefaultValues.MAX_YEAR_DEFAULT.getDefValue()) {
					return true;
				} else {
					if (((Vehicle) v).getYear() <= search.getMaxYear()) {
						return true;
					}
					return false;
				}
			}
		});
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getLocation().equals(DefaultValues.LOCATION_DEFAULT.getDef())) {
					return true;
				} else {
					if (((Vehicle) v).getLocation().equals(search.getLocation())) {
						return true;
					}
					return false;
				}
			}
		});
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getPriceMax() == DefaultValues.MAX_PRICE_DEFAULT.getDefValue()) {
					return true;
				} else {
					if (((Vehicle) v).getPrice() <= search.getPriceMax()) {
						return true;
					}
					return false;
				}
			}
		});
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getPriceMax() == DefaultValues.MIN_PRICE_DEFAULT.getDefValue()) {
					return true;
				} else {
					if (((Vehicle) v).getPrice() >= search.getPriceMin()) {
						return true;
					}
					return false;
				}
			}
		});
		
		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v) {
				if (search.getVehicleType().getType().equals(DefaultValues.VEHICLE_TYPE_DEFAULT.getDef())) {
					return true;
				} else {
					if (((Vehicle) v).getVehicleType().equals(search.getVehicleType())) {
						return true;
					}
					return false;
				}
			}
		});
		return vehicles;
	}

}
