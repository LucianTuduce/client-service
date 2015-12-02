package ro.fortech.cache;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import ro.fortech.model.Vehicle;

@Singleton
public class VehicleCache {

	@PostConstruct
	public void init() {
		System.out.println("Built: Singleton VehicleCache.");
	}

	private List<Vehicle> vehicles;

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
