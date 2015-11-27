package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Vehicle;

public interface VehicleService {

	
	Vehicle getVehicle(int idCar);
	
	void delete(int idCar);
	
	void update(Vehicle car);
	
	void saveVehicle(Vehicle car);
	
	void setVehicles(List<Vehicle> vehicles);
	
	List<Vehicle> generateRandomVehicles(int vehicleCount);
	
	List<Vehicle> initVehicleList();
}
