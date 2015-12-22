package ro.fortech.constants;

/**
 * Class used to define the constants that our application uses.
 *
 */
public class Constants {

	public static final String VEHICLE_FILTER_CRITERIA = "{ \"location\": \"Germany\",\n" + "\"vehicleType\": \"VAN\","+ 
															"\"minCapacity\": 1200,\n" + "\"fuelType\": \"DIESEL\"," +
															"\"pagination\": {"+ "\"pageNumber\":"+ 1 +"," + "\"elemetsPerPage\":" + 20 + "},"  +
															"\"minYear\": 2002,\n" + "\"maxYear\": 2009,"+
															"\"maxPrice\": 0,\n" + "\"minPrice\": 0,"+
															"\"maxCapacity\": 7500,\n" + "\"model\": \" \","+
															"\"fin\": \" \"\n"+"}";
	public static final String BASIC_SECURITY_PREFIX = "Basic ";
	public static final String DEFAULT_VALUE = " ";
	public static final String AUTHORIZATION = "Authorization";
	public static final String Y_NO_HAVE_ACCOUNT = "Y NO have ACCOUNT";
	public static final String USER_CONFIRMED = "User confirmed";
	public static final String INVALID_LOCATION_SEARCH_OR_VEHICLE_TYPE = "Invalid location search or vehicle type. They are not provided! Check again after you provided them!";
	public static final String ALL_GOOD = "All good !";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong, list is null";	
	
}
