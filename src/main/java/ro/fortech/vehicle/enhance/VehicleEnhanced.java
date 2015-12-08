package ro.fortech.vehicle.enhance;

import ro.fortech.model.Vehicle;
import ro.fortech.type.SuspensionType;
import ro.fortech.type.TireCondition;

public class VehicleEnhanced {

	private String dealer;
	private String bodyWeight;
	private String bodyHeight;
	private String bodyLenght;
	private String owner;
	private SuspensionType suspensionType;
	private TireCondition tireCondition;
	private Vehicle vehicle;
	
	public String getDealer() {
		return dealer;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	public String getBodyWeight() {
		return bodyWeight;
	}
	public void setBodyWeight(String bodyWeight) {
		this.bodyWeight = bodyWeight;
	}
	public String getBodyHeight() {
		return bodyHeight;
	}
	public void setBodyHeight(String bodyHeight) {
		this.bodyHeight = bodyHeight;
	}
	public String getBodyLenght() {
		return bodyLenght;
	}
	public void setBodyLenght(String bodyLenght) {
		this.bodyLenght = bodyLenght;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public SuspensionType getSuspensionType() {
		return suspensionType;
	}
	public void setSuspensionType(SuspensionType suspensionType) {
		this.suspensionType = suspensionType;
	}
	public TireCondition getTireCondition() {
		return tireCondition;
	}
	public void setTireCondition(TireCondition tireCondition) {
		this.tireCondition = tireCondition;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
}
