package ro.fortech.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.fortech.def.value.DefaultValues;
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
	private String searchLocation;
	private int searchMaxPrice;
	private int searchMinPrice;
	private String searchVehicleType;

	public VehicleSearchRequest createSearchVechicle() {

		VehicleSearchRequest searchRequest = new VehicleSearchRequest();
		searchRequest.setFin(searchFin);

		if(searchLocation == null){
			searchRequest.setLocation(DefaultValues.LOCATION_DEFAULT.getDef());
		}else{
			searchRequest.setLocation(searchLocation);
		}
		
		if(searchMaxCapacity == 0){
			searchRequest.setMaxCapacity(DefaultValues.MAX_CAPACITY_DEFAULT.getDefValue());	
		}else{
			searchRequest.setMaxCapacity(searchMaxCapacity);
		}
		
		if(searchMinCapacity == 0){
			searchRequest.setMinCapacity(DefaultValues.MIN_CAPACITY_DEFAULT.getDefValue());	
		}else{
			searchRequest.setMinCapacity(searchMinCapacity);
		}
		
		if(searchMinYear == 0){
			searchRequest.setMinYear(DefaultValues.MIN_YEAR_DEFAULT.getDefValue());
		}else{
			searchRequest.setMinYear(searchMinYear);
		}
		
		if(searchMaxYear == 0){
			searchRequest.setMaxYear(DefaultValues.MAX_YEAR_DEFAULT.getDefValue());
		}else{
			searchRequest.setMaxYear(searchMaxYear);
		}
		
		searchRequest.setMaxPrice(searchMaxPrice);
		searchRequest.setMinPrice(searchMinPrice);
		searchRequest.setModel(searchModel);
		searchRequest.setFuelType(FuelType.getEnum(searchFuelType));
		
		if(searchVehicleType == null){
			searchRequest.setVehicleType(VehicleType.getEnum(DefaultValues.VEHICLE_TYPE_DEFAULT.getDef()));
		}else{
			searchRequest.setVehicleType(VehicleType.getEnum(searchVehicleType));
		}
	
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
