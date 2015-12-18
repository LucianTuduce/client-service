package ro.fortech.beans;

import java.util.List;

import javax.ejb.Stateless;

import ro.fortech.model.Vehicle;


@Stateless
public class VehicleSerachResult {

	private List<Vehicle> searchedVehicles;

	public List<Vehicle> getSearchedVehicles() {
		return searchedVehicles;
	}

	public void setSearchedVehicles(List<Vehicle> searchedVehicles) {
		this.searchedVehicles = searchedVehicles;
	}	
}
