package ro.fortech.validator;

import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ro.fortech.cache.VehicleCache;
import ro.fortech.constraints.ConstraintUnicity;
import ro.fortech.model.Vehicle;

public class CheckUnicityValidator implements ConstraintValidator<ConstraintUnicity, String>{

	@EJB
	private VehicleCache cache;
	
	@Override
	public void initialize(ConstraintUnicity constraintAnnotation) {
				
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		for(Vehicle vehicle: cache.getVehicles()){
			System.out.println("checkunicity213");
			if(vehicle.getFin().equals(value)){
				System.out.println("checkunicity");
				return false;
			}
		}
		return true;
	}

}
