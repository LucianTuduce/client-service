package ro.fortech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ro.fortech.cache.HistorySearchCache;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;

@ManagedBean
@ViewScoped
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
	
	@EJB
	private HistorySearchCache historySearchCache;
	
	@Inject
	private EnhancedVehicleBean bean;

	private List<Vehicle> searchedVehicles;
	private Vehicle selectedVehicle = new Vehicle();
	private boolean edit;
	
	public void searchVechicle() {
		searchedVehicles = new ArrayList<Vehicle>();
		VehicleSearchRequest searchRequest = searchVehicleBean.createSearchVechicle();
		historySearchBean.getHistory(searchRequest);
		searchedVehicles = fakeCarService.getVehicles(searchRequest);
	}
	
	public String ceva(Vehicle data){
		System.out.println("Sunt in metoda ceva si "+ data.getFin());
		return "extraInfo";
	}
	
	public void simpleDo(){
		System.out.println("In dummy method");
		//return "login";
	}
	
	public void edit(Vehicle selectedVehicle){
		this.setSelectedVehicle(selectedVehicle);
		System.out.println("here GG");
		edit = true;
		//return "extraInfo";
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

	public List<Vehicle> getSearchedVehicles() {
		return searchedVehicles;
	}

	public void setSearchedVehicles(List<Vehicle> searchedVehicles) {
		this.searchedVehicles = searchedVehicles;
	}

	public Vehicle getSelectedVehicle() {
		return selectedVehicle;
	}

	public void setSelectedVehicle(Vehicle selectedVehicle) {
		this.selectedVehicle = selectedVehicle;
	}

	public boolean isEdit() {
        return edit;
    }

	public EnhancedVehicleBean getBean() {
		return bean;
	}

	public void setBean(EnhancedVehicleBean bean) {
		this.bean = bean;
	}
	
}
