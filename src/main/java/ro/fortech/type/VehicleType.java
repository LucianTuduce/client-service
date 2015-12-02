package ro.fortech.type;

/**
 * Enum used to define the types of cars that are present in the database
 *
 */
public enum VehicleType {
	TRUCK("Truck"), CAR("Car"), VAN("Van"), DEFAULT(" ");

	private String type;

	VehicleType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
