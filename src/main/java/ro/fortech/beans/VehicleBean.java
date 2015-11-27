package ro.fortech.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.services.VehicleService;

@ManagedBean
@RequestScoped
public class VehicleBean {

	@Inject 
	@Named("fakeVehicleServiceImpl")
	private VehicleService fakeCarService;

	
	private List<Vehicle> cars ;
	

	public List<Vehicle> getAllCars(){
		return fakeCarService.getVehicles();
	}

	public List<Vehicle> getCars() {
		cars=fakeCarService.getVehicles();
		return cars;
	}

	public void setCars(List<Vehicle> cars) {
		this.cars = cars;
	}
	
}
