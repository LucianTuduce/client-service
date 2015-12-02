package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearch;

public interface VehicleService {
	public List<Vehicle> getVehicles();
	
	public Vehicle getVehicle(int idCar);
	
	public List<Vehicle> getSearchVehicle(VehicleSearch vehicleSearch);
	
	public void delete(int idCar);
	
	public void update(Vehicle car);
	
	public void saveVehicle(Vehicle car);
}
