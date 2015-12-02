package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearch;

public interface VehicleService {

	
	Vehicle getVehicle(int idCar);
	

	public List<Vehicle> getSearchVehicle(VehicleSearch vehicleSearch);
	
	void delete(int idCar);

	
	void update(Vehicle car);
	
	void saveVehicle(Vehicle car);
	
	void setVehicles(List<Vehicle> vehicles);
	
	List<Vehicle> generateRandomVehicles(int vehicleCount);
	
	List<Vehicle> initVehicleList();
}
