package ro.fortech.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

/**
 * Class used to obtain the vehicles from the database in order to display them
 * on the JSF page. Also the cars can be filtered obtained by performing a
 * search on some values.
 *
 */
@ManagedBean
@RequestScoped
public class VehicleBean {

	@Inject
	@Named("fakeVehicleServiceImpl")
	private VehicleService fakeCarService;

	@Inject
	@Named("searchServiceUtils")
	private VehicleSearchService searchServiceUtils;

	@Inject
	@Named("vehicleSearchServiceImpl")
	private VehicleSearchService searchService;

	private List<Vehicle> cars;

	public List<Vehicle> getCars() {
		VehicleSearchRequest search = new VehicleSearchRequest();
		search.setFin(" ");
		search.setModel(" ");
		search.setFuelType(FuelType.DIESEL);
		search.setMinCapacity(1200);
		search.setMaxCapacity(7500);
		search.setMinYear(2002);
		search.setMaxYear(20090);
		search.setLocation("Germany");
		search.setMinPrice(0);
		search.setMaxPrice(0);
		search.setVehicleType(VehicleType.DEFAULT);
		long startTime = System.nanoTime();
		cars = fakeCarService.getVehicles(search);
		// cars = searchService.getSearch(search);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Execution: " + duration / 1000000
				+ " nanoseconds, car size is " + cars.size());
		return cars;
	}

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
