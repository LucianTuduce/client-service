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
	
	public static VehicleType getEnum(String s){
		
		if(TRUCK.name().equalsIgnoreCase(s)){
			return TRUCK;
		}
		else if(CAR.name().equalsIgnoreCase(s)){
			return CAR;
		}
		else if(VAN.name().equalsIgnoreCase(s)){
			return VAN;
		}
		else if(DEFAULT.name().equalsIgnoreCase(s)){
			return DEFAULT;
		}
		throw new IllegalArgumentException("No Enum specified for this string");
	}
}
