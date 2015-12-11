package ro.fortech.type;

/**
 * Enum used to define the types of fuel that a car can have. 
 *
 */
public enum FuelType {
	DIESEL("Diesel"), GASOLINE("Gasoline"), LPG("LPG"), DEFAULT(" ");
	
	private String fuel;
	
	private FuelType(String fuel) {
		this.setFuel(fuel);
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	
	public static FuelType getEnum(String s){
		
		if(DIESEL.name().equalsIgnoreCase(s)){
			return DIESEL;
		}
		else if(GASOLINE.name().equalsIgnoreCase(s)){
			return GASOLINE;
		}
		else if(LPG.name().equalsIgnoreCase(s)){
			return LPG;
		}
		else if(s == " "){
			return DEFAULT;
		}
		throw new IllegalArgumentException("No Enum specified for this string");
	}
}
