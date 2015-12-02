package ro.fortech.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

/**
 * Class used to obtain the vehicles from the database in order to display them
 * on the JSF page. Also the cars can be filtered obtained by performing a
 * search on some values.
 *
 */
@ManagedBean
@RequestScoped
public class VehicleBean {

	@Inject
	@Named("fakeVehicleServiceImpl")
	private VehicleService fakeCarService;

	@Inject
	@Named("searchServiceUtils")
	private VehicleSearchService searchServiceUtils;

	@Inject
	@Named("vehicleSearchServiceImpl")
	private VehicleSearchService searchService;

	private List<Vehicle> cars;

	public List<Vehicle> getCars() {
		VehicleSearchRequest search = new VehicleSearchRequest();
		search.setFin(" ");
		search.setModel(" ");
		search.setFuelType(FuelType.DIESEL);
		search.setMinCapacity(1200);
		search.setMaxCapacity(7500);
		search.setMinYear(2002);
		search.setMaxYear(20090);
		search.setLocation("Germany");
		search.setMinPrice(0);
		search.setMaxPrice(0);
		search.setVehicleType(VehicleType.DEFAULT);
		long startTime = System.nanoTime();
		cars = fakeCarService.getVehicles(search);
		// cars = searchService.getSearch(search);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Execution: " + duration / 1000000
				+ " nanoseconds, car size is " + cars.size());
		return cars;
	}

}
