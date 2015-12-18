package ro.fortech.beans;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.model.Vehicle;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

@Stateless
public class EnhancedSearchResult {
	
	@EJB(beanName = "vehicleSearchServiceImpl")
	private VehicleSearchService searchService;
	
	private List<VehicleEnhanced> vehicleEnhanceds;
	
	public String searchForEnhancedVehicle(Vehicle vehicle) {
		VehicleEnhanced vehicleEnhanced = searchService.getVehicleEnhancedByFin(vehicle.getFin());
		setVehicleEnhanceds(Arrays.asList(vehicleEnhanced));
		return "extraInfoJSF.xhtml?faces-redirect=true";
	}

	public List<VehicleEnhanced> getVehicleEnhanceds() {
		return vehicleEnhanceds;
	}

	public void setVehicleEnhanceds(List<VehicleEnhanced> vehicleEnhanceds) {
		this.vehicleEnhanceds = vehicleEnhanceds;
	}
	
}
