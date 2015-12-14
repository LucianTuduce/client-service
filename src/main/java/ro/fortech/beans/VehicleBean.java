package ro.fortech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import ro.fortech.cache.HistorySearchCache;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

@ManagedBean(name = "vehicleBean")
@RequestScoped
public class VehicleBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{searchVehicleBean}")
	SearchVehicleBean searchVehicleBean;

	@ManagedProperty(value = "#{historySearchBean}")
	HistorySearchBean historySearchBean;

	
	@EJB(beanName ="fakeVehicleServiceImpl")
	private VehicleService fakeCarService;
	
	@EJB(beanName = "searchServiceUtils")
	private VehicleSearchService searchServiceUtils;
	
	@EJB(beanName = "vehicleSearchServiceImpl")
	private VehicleSearchService searchService;
	
	@Inject
	private HistorySearchCache historySearchCache;

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
		//TODO
		historySearchCache.addHistorySearch("dsafasd", searchRequest);
		searchedVehicles = fakeCarService.getVehicles(searchRequest);
		
		return "success";

	}
	public HistorySearchBean getHistorySearchBean() {
		return historySearchBean;
	}

	public void setHistorySearchBean(HistorySearchBean historySearchBean) {
		this.historySearchBean = historySearchBean;
	}

	public HistorySearchCache getHistorySearchCache() {
		return historySearchCache;
	}

	public void setHistorySearchCache(HistorySearchCache historySearchCache) {
		this.historySearchCache = historySearchCache;
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
