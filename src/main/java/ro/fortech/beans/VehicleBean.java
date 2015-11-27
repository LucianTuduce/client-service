package ro.fortech.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearch;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

@ManagedBean
@RequestScoped
public class VehicleBean {

	@Inject
	@Named("fakeVehicleServiceImpl")
	private VehicleService fakeCarService;

	@Inject
	private VehicleSearchService searchService;

	private List<Vehicle> cars;

//	public List<Vehicle> getAllCars() {
//		return fakeCarService.getVehicles();
//	}

	public List<Vehicle> getCars() {
		VehicleSearch search = new VehicleSearch();
		search.setFin(" ");
		search.setModel(" ");
		search.setFuelType(FuelType.DIESEL);
		search.setMinCapacity(1200);
		search.setMaxCapacity(7500);
		search.setMinYear(2002);
		search.setMaxYear(20090);
		search.setLocation("Germany");
		search.setPriceMax(0);
		search.setPriceMin(0);
		search.setVehicleType(VehicleType.DEFAULT);
		fakeCarService.initVehicleList();
		long startTime = System.nanoTime();
		cars = searchService.getSearchWithColections(search);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Execution: " + duration / 1000000 + " nanoseconds, car size is "+ cars.size());
		return cars;
	}

//	public void setCars(List<Vehicle> cars) {
//		this.cars = cars;
//	}

}
