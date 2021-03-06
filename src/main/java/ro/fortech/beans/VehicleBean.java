package ro.fortech.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import ro.fortech.constants.Constants;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.search.VehicleSearchResponse;
import ro.fortech.search.VehicleSearchResult;
import ro.fortech.search.response.SearchResponseService;
import ro.fortech.services.SearchRequestService;
import ro.fortech.validation.AccountValidationService;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

@Named
@ViewScoped
public class VehicleBean implements Serializable {

	private static final long serialVersionUID = 8372021854454208604L;

	@EJB
	private VehicleSearchResult searchedVehicleBean;
	
	@EJB
	private SearchRequestService searchVehicleBean;
	
	@EJB
	private EnhancedSearchResult enhancedVehicleBean;
	
	@EJB
	private AccountValidationService accountValidation;	
	
	@EJB
	private SearchResponseService serachResponseService;
	
	
	private List<String> searchHistory;
	private List<Vehicle> searchedVehicles;
	private List<VehicleEnhanced> vehicleEnhanceds;
	
	private HttpSession httpSession;

	public SearchRequestService getSearchVehicleBean() {
		return searchVehicleBean;
	}

	public void setSearchVehicleBean(SearchRequestService searchVehicleBean) {
		this.searchVehicleBean = searchVehicleBean;
	}

	public void searchVechicle() throws IOException {
		String accountToken = getAccountToken();
		if(! accountValidation.isUserValid(accountToken)){
			FacesContext.getCurrentInstance().getExternalContext().redirect("loginJSF.xhtml?faces-redirect=true");
		}else{
			VehicleSearchRequest searchRequest = searchVehicleBean.createSearchVechicle();
			VehicleSearchResponse vehicleSearchResponse = serachResponseService.getFilteredVehiclesBySearchCriteriaWithoutPagination(accountToken, searchRequest);
			searchedVehicleBean.getSearchedVehicles().put(serachResponseService.decodeUserToken(accountToken),vehicleSearchResponse.getVehicles());
		}
	}

	public String searchForEnhancedVehicle(Vehicle vehicle) {
		return enhancedVehicleBean.searchForEnhancedVehicle(vehicle);
	}

	public List<String> getSearchHistory() throws IOException {
		String accountToken = getAccountToken();
		this.searchHistory = serachResponseService.getUserSearchHistory(accountToken);
		return searchHistory;
	}

	public void setSearchHistory(List<String> searchHistory) {
		this.searchHistory = searchHistory;
	}

	public List<Vehicle> getSearchedVehicles() {
		String accountToken = getAccountToken();
		this.searchedVehicles = searchedVehicleBean.getSearchedVehicles().get(serachResponseService.decodeUserToken(accountToken));
		return searchedVehicles;
	}

	private String getAccountToken() {
		httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		String accountToken = (String) httpSession.getAttribute(Constants.AUTHORIZATION);
		return accountToken;
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
