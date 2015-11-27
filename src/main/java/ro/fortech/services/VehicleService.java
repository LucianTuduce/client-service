package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Vehicle;

public interface VehicleService {
	public List<Vehicle> getVehicles();
	
	public Vehicle getVehicle(int idCar);
	
	public void delete(int idCar);
	
	public void update(Vehicle car);
	
	public void saveVehicle(Vehicle car);
}
