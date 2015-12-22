package ro.fortech.search;

import java.util.List;

import ro.fortech.vehicle.enhance.VehicleEnhanced;

/**
 * Class used to save the enhance vehicle search response obtained from the search request.
 *
 */
public class VehicleEnhanceSearchResponse {

	private int vehicleEnhancedCount;
	private String errorMessage;
	private List<VehicleEnhanced> vehicleEnhanceds;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<VehicleEnhanced> getVehicleEnhanceds() {
		return vehicleEnhanceds;
	}
	public void setVehicleEnhanceds(List<VehicleEnhanced> vehicleEnhanceds) {
		this.vehicleEnhanceds = vehicleEnhanceds;
	}
	public int getVehicleEnhancedCount() {
		return vehicleEnhancedCount;
	}
	public void setVehicleEnhancedCount(int vehicleEnhancedCount) {
		this.vehicleEnhancedCount = vehicleEnhancedCount;
	}
	
}
