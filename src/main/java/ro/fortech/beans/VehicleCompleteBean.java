package ro.fortech.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "addVehicleBean")
public class VehicleCompleteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fin;
	private String owner;
	private String dealer;
	private String location;
	private String vehicleType;
	private String model;
	private int year;
	private int price;
	private String fuelType;
	private int engineCapacity;
	private String bodyWeight;
	private String bodyHeight;
	private String bodyLenght;
	private String suspensionType;
	private String tireCondition;
	
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDealer() {
		return dealer;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public int getEngineCapacity() {
		return engineCapacity;
	}
	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
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
	public String getSuspensionType() {
		return suspensionType;
	}
	public void setSuspensionType(String suspensionType) {
		this.suspensionType = suspensionType;
	}
	public String getTireCondition() {
		return tireCondition;
	}
	public void setTireCondition(String tireCondition) {
		this.tireCondition = tireCondition;
	}
		
}
