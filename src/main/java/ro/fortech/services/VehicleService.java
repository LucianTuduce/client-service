package ro.fortech.services;

import java.util.List;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;

public interface VehicleService {

	/**
	 * Method used to obtain a vehicle from the database based on a id.
	 * 
	 * @param idCar
	 *            - the id of the vehicle that will be obtained from the
	 *            database
	 * @return - the car that has the id provided as parameter
	 */
	Vehicle getVehicle(int idCar);

	/**
	 * Method used to delete a car from the database based on an id
	 * 
	 * @param idCar
	 *            - the id of the car that will be deleted.
	 */
	void delete(int idCar);

	/**
	 * Method used to update a vehicle in the database
	 * 
	 * @param car
	 *            - the new values of the vehicle that will be added in the
	 *            database
	 */
	void update(Vehicle car);

	/**
	 * Method used to insert a new vehicle in the database.
	 * 
	 * @param car
	 *            - the vehicle that will be introduced in the database
	 */
	void saveVehicle(Vehicle car);

	/**
	 * Method used to set the list of vehicles, list which will be processed
	 * later
	 * 
	 * @param vehicles
	 */
	void setVehicles(List<Vehicle> vehicles);

	/**
	 * Method used to generate random vehicles based on the input parameter.
	 * 
	 * @param vehicleCount
	 *            - the number of vehicles that will be generated
	 * @return - the list containing the vehicles that were generated
	 */
	List<Vehicle> getVehicles(VehicleSearchRequest request);

}
