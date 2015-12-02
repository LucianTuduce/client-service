package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearch;

public interface VehicleSearchService {

	List<Vehicle> getSearch(VehicleSearch search);
	
	List<Vehicle> getSearchWithColections(VehicleSearch search);
	
}
