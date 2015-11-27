package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Vehicle;

public interface CarService {
	public List<Vehicle> getCars();
	
	public Vehicle getCar(int idCar);
	
	public void delete(int idCar);
	
	public void update(Vehicle car);
	
	public void saveCar(Vehicle car);
}
