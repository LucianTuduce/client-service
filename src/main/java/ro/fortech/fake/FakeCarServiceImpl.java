package ro.fortech.fake;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.services.CarService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

@Stateless
@Named("fakeCar")
public class FakeCarServiceImpl implements CarService{

	private List<Vehicle> cars = new ArrayList<>();

	public List<Vehicle> getCars() {
		return initCarList();
	}
	
	private List<Vehicle> initCarList(){
		
		Vehicle car = new Vehicle();
		car.setFin("GR3847UC32");
		car.setModel("Volskwagen Passat");
		car.setFuelType(FuelType.DIESEL);
		car.setEngineCapacity(1990);
		car.setYear(2003);
		car.setLocation("Germania");
		car.setPrice(3000);
		car.setVehicleType(VehicleType.CAR);
		
		Vehicle car1 = new Vehicle();
		car1.setFin("RO5347UK34");
		car1.setModel("Dacia Logan");
		car1.setFuelType(FuelType.GASOLINE);
		car1.setEngineCapacity(1400);
		car1.setYear(2005);
		car1.setLocation("Romania");
		car1.setPrice(4500);
		car1.setVehicleType(VehicleType.CAR);
		
		Vehicle car2 = new Vehicle();
		car2.setFin("RO3847UC32");
		car2.setModel("Volskwagen Transporter");
		car2.setFuelType(FuelType.DIESEL);
		car2.setEngineCapacity(2500);
		car2.setYear(2008);
		car2.setLocation("Romania");
		car2.setPrice(7450);
		car2.setVehicleType(VehicleType.VAN);
		
		Vehicle car3 = new Vehicle();
		car3.setFin("GR7897FG56");
		car3.setModel("Mercedes Sprinter");
		car3.setFuelType(FuelType.DIESEL);
		car3.setEngineCapacity(3100);
		car3.setYear(2009);
		car3.setLocation("Germania");
		car3.setPrice(11560);
		car3.setVehicleType(VehicleType.VAN);
		
		Vehicle car4 = new Vehicle();
		car4.setFin("RO3337PK21");
		car4.setModel("Volvo Truck");
		car4.setFuelType(FuelType.DIESEL);
		car4.setEngineCapacity(5500);
		car4.setYear(2013);
		car4.setLocation("Romania");
		car4.setPrice(53000);
		car4.setVehicleType(VehicleType.TRUCK);
		
		Vehicle car5 = new Vehicle();
		car5.setFin("GR8887PL90");
		car5.setModel("Mercedes Truck");
		car5.setFuelType(FuelType.DIESEL);
		car5.setEngineCapacity(5700);
		car5.setYear(2013);
		car5.setLocation("Germania");
		car5.setPrice(73000);
		car5.setVehicleType(VehicleType.TRUCK);
		
		cars.add(car);
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		return cars;
	}


	@Override
	public Vehicle getCar(int idCar) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(int idCar) {
		System.out.println("Delete from fake DB successful");
		
	}


	@Override
	public void update(Vehicle car) {
		System.out.println("Update into fake DB successful");
		
	}

	@Override
	public void saveCar(Vehicle car) {
		System.out.println("Insert into fake DB successful");
	}
	
}
