package ro.fortech.search;

import ro.fortech.pagination.Pagination;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

/**
 * Vehicle search model class. This class is used in order
 * to filter the vehicles based on the class parameters. 
 *
 */
public class VehicleSearchRequest {

	private String fin;
	private String model;
	private FuelType fuelType;
	private int minCapacity;
	private int maxCapacity;
	private int minYear;
	private int maxYear;
	private String location;
	private int minPrice;
	private int maxPrice;
	private VehicleType vehicleType;
	private Pagination pagination;
	
	
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
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
	public FuelType getFuelType() {
		return fuelType;
	}
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
}
