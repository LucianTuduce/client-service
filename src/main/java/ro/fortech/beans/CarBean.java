package ro.fortech.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.services.CarService;

@ManagedBean
@RequestScoped
public class CarBean {

	@Inject
	@Named("fakeCar")
	private CarService carService;
	
	private List<Vehicle> cars ;
	
	public List<Vehicle> getAllCars(){
		return carService.getCars();
	}

	public List<Vehicle> getCars() {
		cars=carService.getCars();
		return cars;
	}

	public void setCars(List<Vehicle> cars) {
		this.cars = cars;
	}
	
}
