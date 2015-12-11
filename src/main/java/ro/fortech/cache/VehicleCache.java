package ro.fortech.cache;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import ro.fortech.model.Vehicle;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

@Singleton
public class VehicleCache {

	@PostConstruct
	public void init() {
		System.out.println("Built: Singleton VehicleCache.");
	}

	private List<Vehicle> vehicles;
	
	private List<VehicleEnhanced> vehicleEnhanceds;

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<VehicleEnhanced> getVehicleEnhanceds() {
		return vehicleEnhanceds;
	}

	public void setVehicleEnhanceds(List<VehicleEnhanced> vehicleEnhanceds) {
		this.vehicleEnhanceds = vehicleEnhanceds;
	}

}
