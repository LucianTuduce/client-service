package ro.fortech.fake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import ro.fortech.cache.VehicleCache;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.SuspensionType;
import ro.fortech.type.TireCondition;
import ro.fortech.type.VehicleType;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

/**
 * Class used to perform CRUD operation on the vehicles.
 *
 */
@Stateless(name = "fakeVehicleServiceImpl")
@Named("fakeVehicleServiceImpl")
public class FakeVehicleServiceImpl implements VehicleService {

	@EJB(beanName = "vehicleSearchServiceImpl")
	private VehicleSearchService searchService;

	@EJB
	private VehicleCache cache;
	
	@PostConstruct
	public void init() {
		System.out.println("FakeVehicleServiceImpl: Stateless");
	}
	

	private List<Vehicle> generateRandomVehicles(int vehicleCount) {
		
		Vehicle vehicle = null;
		List<Vehicle> vehicles = new ArrayList<>();

		for (int i = 0; i <= vehicleCount; i++) {
			vehicle = new Vehicle();
			vehicle.setFin("GR3847UC32" + i);
			vehicle.setModel("Volskwagen Passat");
			vehicle.setFuelType(FuelType.DIESEL);
			vehicle.setEngineCapacity(1990 + i);
			vehicle.setYear(2003 + i);
			vehicle.setLocation("Germany");
			vehicle.setPrice(3000 + i);
			vehicle.setVehicleType(VehicleType.CAR);
			vehicles.add(vehicle);
		}

		Vehicle vehicle6 = new Vehicle();
		vehicle6.setFin("GR3847UC32");
		vehicle6.setModel("Volskwagen Passat");
		vehicle6.setFuelType(FuelType.DIESEL);
		vehicle6.setEngineCapacity(1990);
		vehicle6.setYear(2003);
		vehicle6.setLocation("Germania");
		vehicle6.setPrice(3000);
		vehicle6.setVehicleType(VehicleType.CAR);

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setFin("RO5347UK34");
		vehicle1.setModel("Dacia Logan");
		vehicle1.setFuelType(FuelType.GASOLINE);
		vehicle1.setEngineCapacity(1400);
		vehicle1.setYear(2005);
		vehicle1.setLocation("Romania");
		vehicle1.setPrice(4500);
		vehicle1.setVehicleType(VehicleType.CAR);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setFin("RO3847UC32");
		vehicle2.setModel("Volskwagen Transporter");
		vehicle2.setFuelType(FuelType.DIESEL);
		vehicle2.setEngineCapacity(2500);
		vehicle2.setYear(2008);
		vehicle2.setLocation("Romania");
		vehicle2.setPrice(7450);
		vehicle2.setVehicleType(VehicleType.VAN);

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setFin("GR7897FG56");
		vehicle3.setModel("Mercedes Sprinter");
		vehicle3.setFuelType(FuelType.DIESEL);
		vehicle3.setEngineCapacity(3100);
		vehicle3.setYear(2009);
		vehicle3.setLocation("Germania");
		vehicle3.setPrice(11560);
		vehicle3.setVehicleType(VehicleType.VAN);

		Vehicle vehicle4 = new Vehicle();
		vehicle4.setFin("RO3337PK21");
		vehicle4.setModel("Volvo Truck");
		vehicle4.setFuelType(FuelType.DIESEL);
		vehicle4.setEngineCapacity(5500);
		vehicle4.setYear(2013);
		vehicle4.setLocation("Romania");
		vehicle4.setPrice(53000);
		vehicle4.setVehicleType(VehicleType.TRUCK);

		Vehicle vehicle5 = new Vehicle();
		vehicle5.setFin("GR8887PL90");
		vehicle5.setModel("Mercedes Truck");
		vehicle5.setFuelType(FuelType.DIESEL);
		vehicle5.setEngineCapacity(5700);
		vehicle5.setYear(2013);
		vehicle5.setLocation("Germania");
		vehicle5.setPrice(73000);
		vehicle5.setVehicleType(VehicleType.TRUCK);

		vehicles.add(vehicle6);
		vehicles.add(vehicle1);
		vehicles.add(vehicle2);
		vehicles.add(vehicle3);
		vehicles.add(vehicle4);
		vehicles.add(vehicle5);

		return vehicles;
	}

	@Override
	public Vehicle getVehicle(int idVehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idVehicle) {
		System.out.println("Delete from fake DB successful");

	}

	@Override
	public void update(Vehicle Vehicle) {
		System.out.println("Update into fake DB successful");

	}

	@Override
	public void saveVehicle(Vehicle vehicle) {
		if(cache.getVehicles() == null){
			List<Vehicle> vehicles = new ArrayList<>();
			vehicles.add(vehicle);
			cache.setVehicles(vehicles);
		}else{
			cache.getVehicles().add(vehicle);
		}
	}

	@Override
	public void setVehicles(List<Vehicle> vehicles) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Vehicle> getVehicles(VehicleSearchRequest request) {
		if (cache.getVehicles() == null) {
			cache.setVehicles(generateRandomVehicles(1002));
			return searchService.getSearch(request, cache.getVehicles());
		} else {
			return searchService.getSearch(request, cache.getVehicles());
		}
	}

	
	private List<VehicleEnhanced> getAllVehiclesEnhanced(List<Vehicle> vehicles){
		List<VehicleEnhanced> vehicleEnhanceds = new ArrayList<>();
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
				vehicleEnhanced.setBodyLenght("340cm");
				vehicleEnhanced.setBodyWeight("1800kg");
				vehicleEnhanced.setDealer("DAIMLER Romania");
				vehicleEnhanced.setOwner("John Doe");
			}else if(other == 2){
				vehicleEnhanced.setBodyHeight("168cm");
				vehicleEnhanced.setBodyLenght("400cm");
				vehicleEnhanced.setBodyWeight("1950kg");
				vehicleEnhanced.setDealer("DACIA Romania");
				vehicleEnhanced.setOwner("John Doe Senior");
			}else if(other == 3){
				vehicleEnhanced.setBodyHeight("145cm");
				vehicleEnhanced.setBodyLenght("320cm");
				vehicleEnhanced.setBodyWeight("1400kg");
				vehicleEnhanced.setDealer("PORSCHE Romania");
				vehicleEnhanced.setOwner("John Doe Junior");
			}else if(other == 4){
				vehicleEnhanced.setBodyHeight("220cm");
				vehicleEnhanced.setBodyLenght("540cm");
				vehicleEnhanced.setBodyWeight("2800kg");
				vehicleEnhanced.setDealer("RENAULT Romania");
				vehicleEnhanced.setOwner("Vitezomanul Gica");
			}
			
			vehicleEnhanceds.add(vehicleEnhanced);
		}
		return vehicleEnhanceds;
	}
	
	@Override
	public List<VehicleEnhanced> getVehicles() {
		if (cache.getVehicleEnhanceds() == null) {
			List<Vehicle> vehicles = generateRandomVehicles(1000);
			cache.setVehicleEnhanceds(getAllVehiclesEnhanced(vehicles));
			return cache.getVehicleEnhanceds();
		} else {
			return cache.getVehicleEnhanceds();
		}
	}
}
