package ro.fortech.validation;

import org.apache.commons.lang3.StringUtils;

import ro.fortech.model.Vehicle;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

public class SaveVehicleValidation {

	
	public static boolean validationForSaveVehicle(VehicleEnhanced vehicleReceived){
		
		Boolean validation = true;
		
		Vehicle vehicle = new Vehicle();
		vehicle = vehicleReceived.getVehicle();
		
		if(vehicle.getFin() == null){
			validation = false;
		}
		
		if(StringUtils.isBlank(vehicleReceived.getOwner())){
			validation = false;
		}
		
		return validation;
	}
}
