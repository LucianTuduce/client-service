package ro.fortech.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearch;
import ro.fortech.services.VehicleService;
import ro.fortech.type.VehicleType;

@ManagedBean(name = "vehicleBean")
@SessionScoped
public class VehicleBean {

	private String fin;
	private String model;
	private String fuelType;
	private int minCapacity;
	private int maxCapacity;
	private int minYear;
	private int maxYear;
	private String location;
	private int priceMin;
	private int priceMax;
	private VehicleType vehicleType;
	private List<Vehicle> listVechicles;
	
	@EJB(beanName="fakeVehicleServiceImpl")
	private VehicleService vehicleSearch;
	
	public String searchVechicle(){
		
		System.out.println("dasndfwelkfae");
		
		return "login";
	}
	
	
	
	public String getFin() {
		return fin;
	}
	
	public void setFin(String fin) {
		this.fin = fin;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public int getMinCapacity() {
		return minCapacity;
	}
	public void setMinCapacity(int minCapacity) {
		this.minCapacity = minCapacity;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public int getMinYear() {
		return minYear;
	}
	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}
	public int getMaxYear() {
		return maxYear;
	}
	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}
	public int getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	
}
