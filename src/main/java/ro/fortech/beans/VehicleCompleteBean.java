package ro.fortech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import ro.fortech.def.value.DefaultValues;
import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.SuspensionType;
import ro.fortech.type.TireCondition;
import ro.fortech.type.VehicleType;
import ro.fortech.vehicle.enhance.VehicleEnhanced;

@ManagedBean(name = "addVehicleBean")
public class VehicleCompleteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB(beanName = "fakeVehicleServiceImpl")
	private VehicleService fakeCarService;

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
	private VehicleEnhanced vehicleEnhanced = new VehicleEnhanced();

	public String addVehicle() {
		
		createVehicleEnhanced();
		fakeCarService.saveVehicle(vehicleEnhanced.getVehicle());
		fakeCarService.saveVehicleEnhanced(vehicleEnhanced);

		return "success";
	}
	
	public void createVehicleEnhanced(){
		
		Vehicle vehicle = new Vehicle();
		vehicle.setFin(fin);
		vehicle.setLocation(location);
		vehicle.setEngineCapacity(engineCapacity);
		vehicle.setFuelType(FuelType.getEnum(fuelType));
		vehicle.setModel(model);
		vehicle.setPrice(price);
		vehicle.setYear(year);
		vehicle.setVehicleType(VehicleType.getEnum(vehicleType));
		
		vehicleEnhanced.setVehicle(vehicle);
		vehicleEnhanced.setBodyHeight(bodyHeight);
		vehicleEnhanced.setBodyLenght(bodyLenght);
		vehicleEnhanced.setBodyWeight(bodyWeight);
		vehicleEnhanced.setDealer(dealer);
		vehicleEnhanced.setOwner(owner);
		vehicleEnhanced.setSuspensionType(SuspensionType.getEnum(suspensionType));
		vehicleEnhanced.setTireCondition(TireCondition.getEnum(tireCondition));
	
	}

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
