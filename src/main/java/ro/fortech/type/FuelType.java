package ro.fortech.type;

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
}
