package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Car;

public interface CarService {
	public List<Car> getCars();
	
	public Car getCar(int idCar);
	
	public void delete(int idCar);
	
	public void update(Car car);
	
	public void saveCar(Car car);
}
