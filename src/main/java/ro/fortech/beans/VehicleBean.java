package ro.fortech.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.vehicle.enhance.VehicleEnhanced;


@Named
@ViewScoped
public class VehicleBean implements Serializable {

	private static final long serialVersionUID = 8372021854454208604L;

	@EJB(beanName = "fakeVehicleServiceImpl")
	private VehicleService fakeCarService;

	@EJB(beanName = "searchServiceUtils")
	private VehicleSearchService searchServiceUtils;

	@EJB
	private HistorySearchBean historySearchBean;

	@EJB
	private VehicleSerachResult searchedVehicleBean;
	
	@EJB
	private SearchRequestService searchVehicleBean;
	
	@EJB
	private EnhancedSearchResult enhancedVehicleBean;
	
	private List<VehicleSearchRequest> searchHistory;
	private List<Vehicle> searchedVehicles;
	private List<VehicleEnhanced> vehicleEnhanceds;

	public SearchRequestService getSearchVehicleBean() {
		return searchVehicleBean;
	}

	public void setSearchVehicleBean(SearchRequestService searchVehicleBean) {
		this.searchVehicleBean = searchVehicleBean;
	}

	public String searchVechicle() {
		
		VehicleSearchRequest searchRequest = searchVehicleBean.createSearchVechicle();
		historySearchBean.addToHistorySearch(searchRequest);
		searchedVehicleBean.setSearchedVehicles(fakeCarService.getVehicles(searchRequest));
		
		return "succes";
	}

	public String searchForEnhancedVehicle(Vehicle vehicle) {
		return enhancedVehicleBean.searchForEnhancedVehicle(vehicle);
	}

	public List<VehicleSearchRequest> getSearchHistory() {
		this.searchHistory = historySearchBean.getSearchHistory();
		return searchHistory;
	}

	public void setSearchHistory(List<VehicleSearchRequest> searchHistory) {
		this.searchHistory = searchHistory;
	}

	public List<Vehicle> getSearchedVehicles() {
		this.searchedVehicles = searchedVehicleBean.getSearchedVehicles();
		return searchedVehicles;
	}

	public void setSearchedVehicles(List<Vehicle> searchedVehicles) {
		this.searchedVehicles = searchedVehicles;
	}

	public List<VehicleEnhanced> getVehicleEnhanceds() {
		return vehicleEnhanceds;
	}

	public void setVehicleEnhanceds(List<VehicleEnhanced> vehicleEnhanceds) {
		this.vehicleEnhanceds = vehicleEnhanceds;
	}
}
