package ro.fortech.def.value;

import java.util.Calendar;

import ro.fortech.type.VehicleType;

/**
 * This enum is used to define the default values for the search criteria if
 * none are provided by the user. Default value means that in filtering, the
 * cars that have default values will be obtained from the database if the
 * search criteria is ok.
 *
 */
public enum DefaultValues {
	// @formatter:off
	FIN_DEFAULT(" "), 
	MODEL_DEFAULT(" "), 
	FUEL_TYPE_DEFAULT(" "), 
	MIN_CAPACITY_DEFAULT(0), 
	MAX_CAPACITY_DEFAULT(30000), 
	MIN_YEAR_DEFAULT(1900), 
	MAX_YEAR_DEFAULT(Calendar.getInstance().get(Calendar.YEAR)),
	LOCATION_DEFAULT("Germany"),
	MIN_PRICE_DEFAULT(0),
	MAX_PRICE_DEFAULT(0),
	VEHICLE_TYPE_DEFAULT(VehicleType.CAR.getType());
	// @formatter:on

	private String def;
	private int defValue;

	private DefaultValues(String def) {
		this.setDef(def);
	}

	private DefaultValues(int def) {
		this.setDefValue(def);
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public int getDefValue() {
		return defValue;
	}

	public void setDefValue(int defValue) {
		this.defValue = defValue;
	}
}
