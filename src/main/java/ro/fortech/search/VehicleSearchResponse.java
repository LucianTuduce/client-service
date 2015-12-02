package ro.fortech.search;

import java.util.List;

import ro.fortech.model.Vehicle;

public class VehicleSearchResponse {

	private int vehicleCount;
	private String errorMessage;
	private List<Vehicle> vehicles;
	public int getVehicleCount() {
		return vehicleCount;
	}
	public void setVehicleCount(int vehicleCount) {
		this.vehicleCount = vehicleCount;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
}
