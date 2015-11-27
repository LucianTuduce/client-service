package ro.fortech.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import ro.fortech.model.Car;
import ro.fortech.services.CarService;

@ManagedBean
@RequestScoped
public class CarBean {

	@Inject 
	private CarService carService;
	
	private List<Car> cars ;
	
	public List<Car> getAllCars(){
		return carService.getCars();
	}

	public List<Car> getCars() {
		cars=carService.getCars();
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
}
