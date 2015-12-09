package ro.fortech.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;


@ManagedBean(name = "vehicleBean")
@RequestScoped
public class VehicleBean {

	@ManagedProperty(value="#{searchVehicleBean}")
	SearchVehicleBean searchVehicleBean;
	
	@Inject
	@Named("fakeVehicleServiceImpl")
	private VehicleService fakeCarService;

	@Inject
	@Named("searchServiceUtils")
	private VehicleSearchService searchServiceUtils;

	@Inject
	@Named("vehicleSearchServiceImpl")
	private VehicleSearchService searchService;
		
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
		VehicleSearchRequest searchRequest = searchVehicleBean.createSearchVechicle();
		searchedVehicles = fakeCarService.getVehicles(searchRequest);

		return "success";
	}

	public SearchVehicleBean getSearchVehicleBean() {
		return searchVehicleBean;
	}

	public void setSearchVehicleBean(SearchVehicleBean searchVehicleBean) {
		this.searchVehicleBean = searchVehicleBean;
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
