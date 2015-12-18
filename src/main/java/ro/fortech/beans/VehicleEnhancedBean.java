package ro.fortech.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import ro.fortech.vehicle.enhance.VehicleEnhanced;

@Named
@RequestScoped
public class VehicleEnhancedBean {

	@EJB
	private EnhancedSearchResult enhancedVehicleBean;
	
	private List<VehicleEnhanced> enhancedVehicles;

	public List<VehicleEnhanced> getEnhancedVehicles() {
		this.enhancedVehicles = enhancedVehicleBean.getVehicleEnhanceds();
		return enhancedVehicles;
	}

	public void setEnhancedVehicles(List<VehicleEnhanced> enhancedVehicles) {
		this.enhancedVehicles = enhancedVehicles;
	}
	
	
}
