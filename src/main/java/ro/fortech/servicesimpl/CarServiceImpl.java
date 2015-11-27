package ro.fortech.servicesimpl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.services.VehicleService;

@Stateless
@Named("carServiceImpl")
public class CarServiceImpl implements VehicleService{

	@Override
	public List<Vehicle> getVehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle getVehicle(int idCar) {
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
	public void saveVehicle(Vehicle car) {
		// TODO Auto-generated method stub
		
	}

	

}
