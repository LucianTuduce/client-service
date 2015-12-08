package ro.fortech.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
@ManagedBean(name = "searchVehicleBean")
@RequestScoped
public class SearchVehicleBean {

	@Inject
	@Named("fakeVehicleServiceImpl")
	private VehicleService fakeCarService;

	@Inject
	@Named("searchServiceUtils")
	private VehicleSearchService searchServiceUtils;

	@Inject
	@Named("vehicleSearchServiceImpl")
	private VehicleSearchService searchService;

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
		FuelType fuelTypeEnum = FuelType.getEnum(this.searchVehicleType);
		searchRequest.setFuelType(fuelTypeEnum);
		if (searchLocation == null) {
			searchLocation = "Romania";
		}
		searchRequest.setLocation(searchLocation);
		searchRequest.setMaxCapacity(searchMaxCapacity);
		searchRequest.setMaxPrice(searchMaxPrice);
		searchRequest.setMaxYear(searchMaxYear);
		searchRequest.setMinCapacity(searchMinCapacity);
		searchRequest.setMinPrice(searchMinPrice);
		searchRequest.setMinYear(searchMinYear);
		searchRequest.setModel(searchModel);
		VehicleType vehicleTypeEnum;
		if (searchVehicleType == null) {
			vehicleTypeEnum = VehicleType.DEFAULT;
		} else {
			vehicleTypeEnum = VehicleType.getEnum(searchFuelType);
		}
		searchRequest.setVehicleType(vehicleTypeEnum);

		return searchRequest;
	}
	public VehicleService getFakeCarService() {
		return fakeCarService;
	}

	public void setFakeCarService(VehicleService fakeCarService) {
		this.fakeCarService = fakeCarService;
	}

	public VehicleSearchService getSearchServiceUtils() {
		return searchServiceUtils;
	}

	public void setSearchServiceUtils(VehicleSearchService searchServiceUtils) {
		this.searchServiceUtils = searchServiceUtils;
	}

	public VehicleSearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(VehicleSearchService searchService) {
		this.searchService = searchService;
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
