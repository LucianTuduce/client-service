package ro.fortech.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import ro.fortech.search.VehicleEnhanceSearchResponse;
import ro.fortech.search.response.SearchResponseService;

@ManagedBean
@RequestScoped
public class EnhancedVehicleBean {

	@EJB
	private SearchResponseService searchResponseService;
	
	private VehicleEnhanceSearchResponse enhancedVehicle;
	private String fin;

	public VehicleEnhanceSearchResponse getEnhancedVehicle() {
		searchForEnhancedVehicle();
		return enhancedVehicle;
	}

	public void searchForEnhancedVehicle() {
		//VehicleEnhanceSearchResponse response = searchResponseService.getVehicleEnhancedByFin("alandala", fin); 
		VehicleEnhanceSearchResponse response = searchResponseService.getVehicleEnhancedByFin("alandala", "RO5347UK34"); 
		this.enhancedVehicle = response;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}
	
}
