package ro.fortech.def.value;

import java.util.Calendar;

public enum DefaultValues {
	// @formatter:off
	FIN_DEFAULT(" "), 
	MODEL_DEFAULT(" "), 
	FUEL_TYPE_DEFAULT(" "), 
	MIN_CAPACITY_DEFAULT(0), 
	MAX_CAPACITY_DEFAULT(30000), 
	MIN_YEAR_DEAFULT(1900), 
	MAX_YEAR_DEFAULT(Calendar.getInstance().get(Calendar.YEAR)),
	LOCATION_DEFAULT("Germany"),
	MIN_PRICE_DEFAULT(0),
	MAX_PRICE_DEFAULT(0),
	VEHICLE_TYPE_DEFAULT(" ");
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
