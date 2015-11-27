package ro.fortech.fake;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import ro.fortech.model.Car;
import ro.fortech.services.CarService;

@Stateless
@Named("fakeCarServiceImpl")
public class FakeCarServiceImpl implements CarService{

	private List<Car> cars = new ArrayList<>();

	public List<Car> getCars() {
		return initCarList();
	}
	
	private List<Car> initCarList(){
		
		Car car = new Car();
		car.setCarLocation("Germania");
		car.setCarPrice(3000);
		car.setCarYear(2003);
		car.setEngineCapacity(1990);
		car.setModel("Volskwagen");
		car.setMotorTye("Diesel");
		car.setVehicleType("Passat");
		
		Car car1 = new Car();
		car1.setCarLocation("Germania");
		car1.setCarPrice(7000);
		car1.setCarYear(2011);
		car1.setEngineCapacity(1990);
		car1.setModel("Audi");
		car1.setMotorTye("Diesel");
		car1.setVehicleType("A4");
		
		Car car2 = new Car();
		car2.setCarLocation("Romania");
		car2.setCarPrice(3500);
		car2.setCarYear(2006);
		car2.setEngineCapacity(1400);
		car2.setModel("Dacia");
		car2.setMotorTye("Benzina");
		car2.setVehicleType("Logan");
		
		Car car3 = new Car();
		car3.setCarLocation("Germania");
		car3.setCarPrice(7000);
		car3.setCarYear(2006);
		car3.setEngineCapacity(2700);
		car3.setModel("Audi");
		car3.setMotorTye("Diesel");
		car3.setVehicleType("A6");
		
		Car car4 = new Car();
		car4.setCarLocation("Franta");
		car4.setCarPrice(4400);
		car4.setCarYear(2008);
		car4.setEngineCapacity(1200);
		car4.setModel("Renault");
		car4.setMotorTye("Benzina");
		car4.setVehicleType("Laguna");
		
		cars.add(car);
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		return cars;
	}


	@Override
	public Car getCar(int idCar) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(int idCar) {
		System.out.println("Delete from fake DB successful");
		
	}


	@Override
	public void update(Car car) {
		System.out.println("Update into fake DB successful");
		
	}

	@Override
//	@EJB(beanName = "FakeCarServiceImpl")
	public void saveCar(Car car) {
		System.out.println("Insert into fake DB successful");
	}
	
}
