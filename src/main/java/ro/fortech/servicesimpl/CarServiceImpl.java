package ro.fortech.servicesimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import ro.fortech.model.Car;
import ro.fortech.services.CarService;

@Stateless
@Named("carServiceImpl")
public class CarServiceImpl implements CarService{

	@Override
	public List<Vehicle> getCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle getCar(int idCar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idCar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vehicle car) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveCar(Vehicle car) {
		// TODO Auto-generated method stub
		
	}

}
