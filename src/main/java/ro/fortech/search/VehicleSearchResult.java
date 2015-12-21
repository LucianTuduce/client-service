package ro.fortech.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import ro.fortech.model.Vehicle;


@Stateless
public class VehicleSearchResult {

	private Map<String ,List<Vehicle>> searchedVehicles = new HashMap<String, List<Vehicle>>();

	public Map<String ,List<Vehicle>> getSearchedVehicles() {
		return searchedVehicles;
	}

	public void setSearchedVehicles(Map<String ,List<Vehicle>> searchedVehicles) {
		this.searchedVehicles = searchedVehicles;
	}

	
}
