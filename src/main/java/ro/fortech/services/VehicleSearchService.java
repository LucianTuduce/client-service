package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

public interface VehicleSearchService {

	/**
	 * Method used to return a list of vehicles based on a search criteria
	 * 
	 * @param search
	 *            - the search criteria used to filter the list of vehicles.
	 * @return - the filtered list after applying the search criteria
	 */
	List<Vehicle> getSearch(VehicleSearchRequest search, List<Vehicle> vehicles);

	VehicleEnhanced getVehicleEnhancedByFin(String fin);
	
}
