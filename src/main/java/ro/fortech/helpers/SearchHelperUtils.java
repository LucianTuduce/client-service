package ro.fortech.helpers;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 * Class used to check if some conditions are true in the filtering process
 * using the CollectionUtils filter method.
 *
 */
@Stateless
public class SearchHelperUtils {

	@PostConstruct
	public void init() {
		System.out.println("SearchHelperUtils: Stateless");
	}
	
	
	/**
	 * Method used to check if the string values provided by the user and the
	 * one that the car has are equal.
	 * 
	 * @param value
	 *            - the value obtained from the database
	 * @param valuedCompared
	 *            - the value given as filter criteria by the user
	 * @return - true if the strings are equal or false if not
	 */
	public boolean isAttributeEqual(String value, String valuedCompared) {
		return valuedCompared.equalsIgnoreCase(value);
	}

	/**
	 * Method used to check if the value provided by the user is greater or
	 * equal to the one that the vehicle has.
	 * 
	 * @param value
	 *            - value that the user provided for comparison
	 * @param minValuedCompared
	 *            - value that the vehicles has from database
	 * @return - true if the value is greater or equal or false otherwise
	 */
	public boolean isAttributeGreater(int value, int minValuedCompared) {
		return value >= minValuedCompared;
	}

	/**
	 * Method used to check if the value provided by the user is lower or
	 * equal to the one that the vehicle has.
	 * 
	 * @param value
	 *            - value that the user provided for comparison
	 * @param minValuedCompared
	 *            - value that the vehicles has from database
	 * @return - true if the value is lower or equal or false otherwise
	 */
	public boolean isAttributeLesser(int value, int maxValuedCompared) {
		return value <= maxValuedCompared;
	}

}
