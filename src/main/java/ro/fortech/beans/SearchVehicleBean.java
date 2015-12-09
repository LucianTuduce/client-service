package ro.fortech.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

/**
 * Class used to obtain the vehicles from the database in order to display them
 * on the JSF page. Also the cars can be filtered obtained by performing a
 * search on some values.
 *
 */
@ManagedBean(name = "searchVehicleBean")
@SessionScoped
public class SearchVehicleBean {

	private String searchFin;
	private String searchModel;
	private String searchFuelType;
	private int searchMinCapacity;
	private int searchMaxCapacity;
	private int searchMinYear;
	private int searchMaxYear;
	private String searchLocation="Germany";
	private int searchMaxPrice;
	private int searchMinPrice;
	private String searchVehicleType = "CAR";

	public VehicleSearchRequest createSearchVechicle() {

		VehicleSearchRequest searchRequest = new VehicleSearchRequest();

		searchRequest.setFin(searchFin);
		FuelType fuelTypeEnum = FuelType.getEnum(searchFuelType);

		searchRequest.setFin(" ");
		System.out.println("Vehicle Type: "+searchVehicleType);
		System.out.println("Location "+ searchLocation);
		System.out.println("Model: "+ searchModel);
		System.out.println("FuelTyp: "+searchFuelType);
		
		searchRequest.setLocation(searchLocation);
		searchRequest.setMaxCapacity(30000);
		searchRequest.setMaxPrice(40000);
		searchRequest.setMaxYear(2015);
		searchRequest.setMinCapacity(searchMinCapacity);
		searchRequest.setMinPrice(searchMinPrice);
		searchRequest.setMinYear(searchMinYear);
		searchRequest.setModel(searchModel);
		VehicleType vehicleTypeEnum = VehicleType.getEnum(searchVehicleType);
		searchRequest.setVehicleType(vehicleTypeEnum);
		searchRequest.setModel(" ");
		searchRequest.setFuelType(FuelType.DEFAULT);
		
		searchRequest.setVehicleType(VehicleType.CAR);

		return searchRequest;
	}
	
	public String getSearchFin() {
		return searchFin;
	}

	public void setSearchFin(String searchFin) {
		this.searchFin = searchFin;
	}

	public String getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(String searchModel) {
		this.searchModel = searchModel;
	}

	public String getSearchFuelType() {
		return searchFuelType;
	}

	public void setSearchFuelType(String searchFuelType) {
		this.searchFuelType = searchFuelType;
	}

	public int getSearchMinCapacity() {
		return searchMinCapacity;
	}

	public void setSearchMinCapacity(int searchMinCapacity) {
		this.searchMinCapacity = searchMinCapacity;
	}

	public int getSearchMaxCapacity() {
		return searchMaxCapacity;
	}

	public void setSearchMaxCapacity(int searchMaxCapacity) {
		this.searchMaxCapacity = searchMaxCapacity;
	}

	public int getSearchMinYear() {
		return searchMinYear;
	}

	public void setSearchMinYear(int searchMinYear) {
		this.searchMinYear = searchMinYear;
	}

	public int getSearchMaxYear() {
		return searchMaxYear;
	}

	public void setSearchMaxYear(int searchMaxYear) {
		this.searchMaxYear = searchMaxYear;
	}

	public String getSearchLocation() {
		return searchLocation;
	}

	public void setSearchLocation(String searchLocation) {
		this.searchLocation = searchLocation;
	}

	public int getSearchMaxPrice() {
		return searchMaxPrice;
	}

	public void setSearchMaxPrice(int searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}

	public int getSearchMinPrice() {
		return searchMinPrice;
	}

	public void setSearchMinPrice(int searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}

	public String getSearchVehicleType() {
		return searchVehicleType;
	}

	public void setSearchVehicleType(String searchVehicleType) {
		this.searchVehicleType = searchVehicleType;
	}
}
