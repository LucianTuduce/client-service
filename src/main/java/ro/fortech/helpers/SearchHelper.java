package ro.fortech.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import ro.fortech.def.value.DefaultValues;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;

/**
 * Class used to filter the cars based on some values that are provided by the
 * user. The methods are called in the order that they are present in the class.
 * And the output of one is the input for the other in the calling class.
 */
@Stateless
public class SearchHelper {

	@PostConstruct
	public void init() {
		System.out.println("SearchHelper: Stateless");
	}
	
	
	/**
	 * Method used to filter the vehicles based on their model
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained from the database
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByModel(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		if (search.getModel().equals(DefaultValues.MODEL_DEFAULT.getDef())) {
			intermediateSearchVehicles = new ArrayList<>(vehicles);
		} else {
			intermediateSearchVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getModel().equalsIgnoreCase(search.getModel())) {
					intermediateSearchVehicles.add(vehicle);
				}
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their fuel type
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByFuelType(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		if (search.getFuelType().getFuel().equals(DefaultValues.FUEL_TYPE_DEFAULT.getDef())) {
			intermediateSearchVehicles = new ArrayList<>(vehicles);
		} else {
			intermediateSearchVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getFuelType().getFuel().equalsIgnoreCase(search.getFuelType().getFuel())) {
					intermediateSearchVehicles.add(vehicle);
				}
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their minimum engine capacity
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model and fuel type
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByMinEngineCapacity(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		if (search.getMinCapacity() == DefaultValues.MIN_CAPACITY_DEFAULT.getDefValue()) {
			intermediateSearchVehicles = new ArrayList<>(vehicles);
		} else {
			intermediateSearchVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getEngineCapacity() >= search.getMinCapacity()) {
					intermediateSearchVehicles.add(vehicle);
				}
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their maximum engine capacity
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model, fuel type and minimum engine capacity
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByMaxEngineCapacity(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		if (search.getMaxCapacity() == DefaultValues.MAX_CAPACITY_DEFAULT.getDefValue()) {
			intermediateSearchVehicles = new ArrayList<>(vehicles);
			vehicles.clear();
		} else {
			intermediateSearchVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getEngineCapacity() <= search.getMaxCapacity()) {
					intermediateSearchVehicles.add(vehicle);
				}
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their minimum build year
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model, fuel type, minimum engine capacity and maximum
	 *            engine capacity
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByMinBuildYear(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		if (search.getMinYear() == DefaultValues.MIN_YEAR_DEFAULT.getDefValue()) {
			intermediateSearchVehicles = new ArrayList<>(vehicles);
		} else {
			intermediateSearchVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getYear() >= search.getMinYear()) {
					intermediateSearchVehicles.add(vehicle);
				}
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their maximum build year
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model, fuel type, minimum engine capacity, maximum engine
	 *            capacity and minimum build year
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByMaxBuildYear(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		if (search.getMaxYear() == DefaultValues.MAX_YEAR_DEFAULT.getDefValue()) {
			intermediateSearchVehicles = new ArrayList<Vehicle>(vehicles);
		} else {
			intermediateSearchVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getYear() <= search.getMaxYear()) {
					intermediateSearchVehicles.add(vehicle);
				}
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their location
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model, fuel type, minimum engine capacity, maximum engine
	 *            capacity, minimum build year and maximum build year
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByLocation(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		intermediateSearchVehicles = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getLocation().equals(search.getLocation())) {
				intermediateSearchVehicles.add(vehicle);
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their minimum selling price
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model, fuel type, minimum engine capacity, maximum engine
	 *            capacity, minimum build year, maximum build year and location
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByMinSellPrice(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		if (search.getMinPrice() == DefaultValues.MIN_PRICE_DEFAULT.getDefValue()) {
			intermediateSearchVehicles = new ArrayList<>(vehicles);
		} else {
			intermediateSearchVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getPrice() >= search.getMinPrice()) {
					intermediateSearchVehicles.add(vehicle);
				}
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their maximum selling price
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model, fuel type, minimum engine capacity, maximum engine
	 *            capacity, minimum build year, maximum build year, location and
	 *            minimum selling price
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByMaxSellPrice(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		if (search.getMaxPrice() == DefaultValues.MAX_PRICE_DEFAULT.getDefValue()) {
			intermediateSearchVehicles = new ArrayList<>(vehicles);
		} else {
			intermediateSearchVehicles = new ArrayList<>();
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getPrice() <= search.getMaxPrice()) {
					intermediateSearchVehicles.add(vehicle);
				}
			}
		}
		return intermediateSearchVehicles;
	}

	/**
	 * Method used to filter the vehicles based on their type
	 * 
	 * @param vehicles
	 *            - the list of vehicles obtained after filtering it based on
	 *            the model, fuel type, minimum engine capacity, maximum engine
	 *            capacity, minimum build year, maximum build year, location,
	 *            minimum selling price and maximum selling price
	 * @param search
	 *            - the search criteria that the vehicles will be filtered
	 * @return - the filtered list based on the search criteria.
	 */
	public List<Vehicle> getVehiclesByVehicleType(List<Vehicle> vehicles, VehicleSearchRequest search) {
		List<Vehicle> intermediateSearchVehicles = null;
		intermediateSearchVehicles = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getVehicleType().getType().equals(search.getVehicleType().getType())) {
				intermediateSearchVehicles.add(vehicle);
			}
		}
	return intermediateSearchVehicles;
	}
}
