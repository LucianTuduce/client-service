package ro.fortech.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import ro.fortech.cache.VehicleCache;
import ro.fortech.model.Vehicle;

@Named
@ViewScoped
public class DummyBean {
	
	@EJB
	private VehicleCache cache;
	
	private List<Vehicle> vehicles;

	public List<Vehicle> getVehicles() {
		this.vehicles = cache.getVehicles();
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public String ceva(Vehicle vehicle){
		System.out.println("I am in ceva method "+vehicle.getFin());
		return "extraInfo";
	}
	
}
