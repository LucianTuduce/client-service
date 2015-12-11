package ro.fortech.servicesimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import ro.fortech.def.value.DefaultValues;
import ro.fortech.helpers.SearchHelperUtils;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

@Stateless
@Named("searchServiceUtils")
public class FakeCollectionsUtilsSearcher implements VehicleSearchService{

	@EJB(beanName = "FakeVehicleServiceImpl")
	private VehicleService vehicleService;

	@EJB
	private SearchHelperUtils searchHelperUtil;
	
	@Override
	public List<Vehicle> getSearch(VehicleSearchRequest search, List<Vehicle> vehicles) {

		CollectionUtils.filter(vehicles, new Predicate() {

			@Override
			public boolean evaluate(Object v){ 
				
				boolean finFound = isAttributeEqual(search.getFin(), ((Vehicle)v).getFin(), DefaultValues.FIN_DEFAULT.getDef());
			    
				boolean modelFound = isAttributeEqual(search.getModel(), ((Vehicle)v).getModel(), DefaultValues.MODEL_DEFAULT.getDef());
			    
				boolean fuelTypeFound = isAttributeEqual(search.getFuelType().getFuel(), ((Vehicle)v).getFuelType().getFuel(), DefaultValues.FUEL_TYPE_DEFAULT.getDef());
			    
				boolean minCapacityFound = isAttributeGreater(search.getMinCapacity(), ((Vehicle)v).getEngineCapacity(), DefaultValues.MIN_CAPACITY_DEFAULT.getDefValue());
			    
				boolean maxCapacityFound = isAttributeLesser(search.getMaxCapacity(), ((Vehicle)v).getEngineCapacity(), DefaultValues.MAX_CAPACITY_DEFAULT.getDefValue());
			    
				boolean minYearFound = isAttributeGreater(search.getMinYear(), ((Vehicle)v).getYear(), DefaultValues.MIN_YEAR_DEFAULT.getDefValue());
			    
				boolean maxYearFound = isAttributeLesser(search.getMaxYear(), ((Vehicle)v).getYear(), DefaultValues.MAX_YEAR_DEFAULT.getDefValue());
			    
				boolean locationFound = isAttributeEqual(search.getLocation(), ((Vehicle)v).getLocation(), DefaultValues.LOCATION_DEFAULT.getDef());
			    
				boolean minPriceFound = isAttributeGreater(search.getMinPrice(), ((Vehicle)v).getPrice(), DefaultValues.MIN_PRICE_DEFAULT.getDefValue());
			    
				boolean maxPriceFound = isAttributeLesser(search.getMaxPrice(), ((Vehicle)v).getPrice(), DefaultValues.MAX_PRICE_DEFAULT.getDefValue());
			    
				boolean vehicleTypeFound = isAttributeEqual(search.getVehicleType().getType(), ((Vehicle)v).getVehicleType().getType(), DefaultValues.VEHICLE_TYPE_DEFAULT.getDef());
				
				return finFound && modelFound && fuelTypeFound && minCapacityFound && maxCapacityFound && minYearFound && maxYearFound && locationFound && minPriceFound && maxPriceFound && vehicleTypeFound;
			}

			private boolean isAttributeEqual(String searchValue, String vehicleValue, String defaultValue) {
				boolean isEqual;
				isEqual = searchHelperUtil.isAttributeEqual(defaultValue, searchValue);
				if (!isEqual) {
					isEqual = searchHelperUtil.isAttributeEqual(vehicleValue, searchValue);
				}
				return isEqual;
			}
			
			private boolean isAttributeGreater(int searchValue, int vehicleValue, int defaultValue) {
				boolean isEqual;
				isEqual = searchHelperUtil.isAttributeGreater(searchValue, defaultValue);
				if (!isEqual) {
					isEqual = searchHelperUtil.isAttributeGreater(searchValue, defaultValue);
				}
				return isEqual;
			}
			
			private boolean isAttributeLesser(int searchValue, int vehicleValue, int defaultValue) {
				boolean isEqual;
				isEqual = searchHelperUtil.isAttributeLesser(defaultValue, searchValue);
				if (!isEqual) {
					isEqual = searchHelperUtil.isAttributeLesser(vehicleValue, searchValue);
				}
				return isEqual;
			}
		});

		return vehicles;
	}

	@Override
	public VehicleEnhanced getVehicleByFin(String fin,
			List<VehicleEnhanced> vehicleEnhanceds) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
