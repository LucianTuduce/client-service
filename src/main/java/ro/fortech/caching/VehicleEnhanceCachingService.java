package ro.fortech.caching;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.cache.VehicleCache;
import ro.fortech.model.Vehicle;
import ro.fortech.type.SuspensionType;
import ro.fortech.type.TireCondition;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

/**
 * Class used to initiate the vehicle enhanced cache service. here all the extra
 * information about a car is provided to the application.
 *
 */
@Stateless
public class VehicleEnhanceCachingService {

	@PostConstruct
	public void init() {
		System.out.println("VehicleEnhanceCachingService: Stateless");
	}
	
	@EJB
	private VehicleCache vehicleCache;
	
	public void initVehiclesEnhancedCache(){
		List<VehicleEnhanced> vehicleEnhanceds = new ArrayList<>();
		List<Vehicle> vehicles = vehicleCache.getVehicles();
		VehicleEnhanced vehicleEnhanced = null;
		Random randomType = null;
		Random otherValue = null;
		int type, other;
		for(int i=0;i<vehicles.size();i++){
			vehicleEnhanced = new VehicleEnhanced();
			Vehicle vehicle = vehicles.get(i);
			randomType = new Random();
			otherValue = new Random();
			type = randomType.nextInt(3)+1;
			other = otherValue.nextInt(4)+1;
			
			if(type == 1){
				vehicleEnhanced.setSuspensionType(SuspensionType.MAC_PHERSON);
				vehicleEnhanced.setTireCondition(TireCondition.NEW);
			}else if(type == 2){
				vehicleEnhanced.setSuspensionType(SuspensionType.SWING_AXLE);
				vehicleEnhanced.setTireCondition(TireCondition.SLIGHTY_USED);
			}else if(type == 3){
				vehicleEnhanced.setSuspensionType(SuspensionType.TRAILING_LINK);
				vehicleEnhanced.setTireCondition(TireCondition.USED);
			}
			
			vehicleEnhanced.setVehicle(vehicle);
			
			if(other == 1){
				vehicleEnhanced.setBodyHeight("160cm");
				vehicleEnhanced.setBodyLength("340cm");
				vehicleEnhanced.setBodyWeight("1800kg");
				vehicleEnhanced.setDealer("DAIMLER Romania");
				vehicleEnhanced.setOwner("John Doe");
			}else if(other == 2){
				vehicleEnhanced.setBodyHeight("168cm");
				vehicleEnhanced.setBodyLength("400cm");
				vehicleEnhanced.setBodyWeight("1950kg");
				vehicleEnhanced.setDealer("DACIA Romania");
				vehicleEnhanced.setOwner("John Doe Senior");
			}else if(other == 3){
				vehicleEnhanced.setBodyHeight("145cm");
				vehicleEnhanced.setBodyLength("320cm");
				vehicleEnhanced.setBodyWeight("1400kg");
				vehicleEnhanced.setDealer("PORSCHE Romania");
				vehicleEnhanced.setOwner("John Doe Junior");
			}else if(other == 4){
				vehicleEnhanced.setBodyHeight("220cm");
				vehicleEnhanced.setBodyLength("540cm");
				vehicleEnhanced.setBodyWeight("2800kg");
				vehicleEnhanced.setDealer("RENAULT Romania");
				vehicleEnhanced.setOwner("Vitezomanul Gica");
			}
			
			vehicleEnhanceds.add(vehicleEnhanced);
		}
		vehicleCache.setVehicleEnhanceds(vehicleEnhanceds);
	}
	
}
