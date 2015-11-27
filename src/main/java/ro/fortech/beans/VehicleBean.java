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

<<<<<<< Upstream, based on origin/master
	@Inject 
	@Named("fakeCarServiceImpl")
	private CarService fakeCarService;
=======
	@Inject
	@Named("fakeCar")
	private CarService carService;
>>>>>>> 1350786 Updated Car mdel class, created search class
	
	private List<Vehicle> cars ;
	
<<<<<<< Upstream, based on origin/master
	public List<Car> getAllCars(){
		return fakeCarService.getCars();
=======
	public List<Vehicle> getAllCars(){
		return carService.getCars();
>>>>>>> 1350786 Updated Car mdel class, created search class
	}

<<<<<<< Upstream, based on origin/master
	public List<Car> getCars() {
		cars=fakeCarService.getCars();
=======
	public List<Vehicle> getCars() {
		cars=carService.getCars();
>>>>>>> 1350786 Updated Car mdel class, created search class
		return cars;
	}

	public void setCars(List<Vehicle> cars) {
		this.cars = cars;
	}
	
}
