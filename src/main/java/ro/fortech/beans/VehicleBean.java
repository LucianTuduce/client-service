package ro.fortech.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import ro.fortech.model.Vehicle;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;


@ManagedBean(name = "vehicleBean")
@RequestScoped
public class VehicleBean {

	@ManagedProperty(value="#{searchVehicleBean}")
	SearchVehicleBean searchVehicleBean;
		
	private String fin;
	private String model;
	private FuelType fuelType;
	private int engineCapacity;
	private int year;
	private String location;
	private int price;
	private VehicleType vehicleType;
	private List<Vehicle> searchedVehicles;
	
	public String searchVechicle() {

		searchedVehicles = new ArrayList<Vehicle>();
		
		// List<Vehicle> vehicles = new ArrayList<Vehicle>();
		// vehicles = fakeCarService.getVehicles(searchRequest);
		// listVehicles = vehicles;

		Vehicle vehicle6 = new Vehicle();
		vehicle6.setFin("GR3847UC32");
		vehicle6.setModel("Volskwagen Passat");
		vehicle6.setFuelType(FuelType.DIESEL);
		vehicle6.setEngineCapacity(1990);
		vehicle6.setYear(2003);
		vehicle6.setLocation("Germania");
		vehicle6.setPrice(3000);
		vehicle6.setVehicleType(VehicleType.CAR);

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setFin("RO5347UK34");
		vehicle1.setModel("Dacia Logan");
		vehicle1.setFuelType(FuelType.GASOLINE);
		vehicle1.setEngineCapacity(1400);
		vehicle1.setYear(2005);
		vehicle1.setLocation("Romania");
		vehicle1.setPrice(4500);
		vehicle1.setVehicleType(VehicleType.CAR);

		searchedVehicles.add(vehicle1);
		
		searchedVehicles.add(vehicle6);

		return "success";
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
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public int getEngineCapacity() {
		return engineCapacity;
	}
	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public List<Vehicle> getSearchedVehicles() {
		return searchedVehicles;
	}

	public void setSearchedVehicles(List<Vehicle> searchedVehicles) {
		this.searchedVehicles = searchedVehicles;
	}
	
	
}
