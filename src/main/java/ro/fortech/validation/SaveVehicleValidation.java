package ro.fortech.validation;

import ro.fortech.cache.VehicleCache;
import javax.ejb.EJB;

import org.apache.commons.lang3.StringUtils;

import ro.fortech.model.Vehicle;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

public class SaveVehicleValidation {

	@EJB
	private static VehicleCache cache;
	
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
		if(StringUtils.isBlank(vehicleReceived.getDealer())){
			validation = false;
		}
		if(StringUtils.isBlank(vehicle.getLocation())){
			validation = false;
		}
		if(StringUtils.isBlank(vehicle.getVehicleType().getType())){
			validation = false;
		}
		if(StringUtils.isBlank(vehicle.getModel())){
			validation = false;
		}
		if(StringUtils.isBlank(String.valueOf(vehicle.getYear()))){
			validation = false;
		}
		if(StringUtils.isBlank(String.valueOf(vehicle.getPrice()))){
			validation = false;
		}
		if(StringUtils.isBlank(String.valueOf(vehicle.getEngineCapacity()))){
			validation = false;
		}
		if(StringUtils.isBlank(vehicleReceived.getBodyWeight())){
			validation = false;
		}
		if(StringUtils.isBlank(vehicleReceived.getBodyHeight())){
			validation = false;
		}
		if(StringUtils.isBlank(vehicleReceived.getBodyLength())){
			validation = false;
		}
		if(StringUtils.isBlank(vehicleReceived.getSuspensionType().toString())){
			validation = false;
		}
		if(StringUtils.isBlank(vehicleReceived.getTireCondition().toString())){
			validation = false;
		}
		
		return validation;
	}
	
	public static boolean validationFINSaveVehicle(String value){
		
		Boolean unicity =  true;
		
		for(Vehicle vehicle: cache.getVehicles()){
			if(vehicle.getFin().equals(value)){
				unicity = false;
			}
		}
		
		return unicity;
	}
	
}
