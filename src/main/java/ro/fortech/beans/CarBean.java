package ro.fortech.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.model.Car;
import ro.fortech.services.CarService;

@ManagedBean
@RequestScoped
public class CarBean {

	@Inject 
	@Named("fakeCarServiceImpl")
	private CarService fakeCarService;
	
	private List<Car> cars ;
	
	public List<Car> getAllCars(){
		return fakeCarService.getCars();
	}

	public List<Car> getCars() {
		cars=fakeCarService.getCars();
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	
}
