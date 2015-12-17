package ro.fortech.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import ro.fortech.def.value.DefaultValues;
import ro.fortech.model.Vehicle;
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
	private String year;
	private String price;
	private String fuelType;
	private String engineCapacity;
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
		if(StringUtils.isBlank(fin)){
			vehicle.setFin(DefaultValues.FIN_DEFAULT.getDef());
		}
		else{
			vehicle.setFin(fin);
		}
		
		if(StringUtils.isBlank(fin)){
			vehicle.setLocation(DefaultValues.LOCATION_DEFAULT.getDef());
		}
		else{
			vehicle.setLocation(location);
		}
		if(StringUtils.isNumeric(engineCapacity)){
			vehicle.setEngineCapacity(Integer.parseInt(engineCapacity));
		}
		else{
			vehicle.setEngineCapacity(DefaultValues.MIN_CAPACITY_DEFAULT.getDefValue());
		}
		if(StringUtils.isBlank(fuelType)){
			vehicle.setFuelType(FuelType.getEnum(DefaultValues.FUEL_TYPE_DEFAULT.getDef()));
		}
		else{
			vehicle.setFuelType(FuelType.getEnum(fuelType));
		}
		if(StringUtils.isBlank(model)){
			vehicle.setModel(DefaultValues.MODEL_DEFAULT.getDef());
		}
		else{
			vehicle.setModel(model);
		}
		if(StringUtils.isBlank(price)){
			vehicle.setPrice(DefaultValues.MIN_PRICE_DEFAULT.getDefValue());
		}
		else{
			vehicle.setPrice(Integer.parseInt(price));
		}
		if(StringUtils.isBlank(year)){
			vehicle.setPrice(DefaultValues.MIN_YEAR_DEFAULT.getDefValue());
		}
		else{
			vehicle.setYear(Integer.parseInt(year));
		}
		if(StringUtils.isBlank(vehicleType)){
			vehicle.setVehicleType(VehicleType.getEnum(DefaultValues.VEHICLE_TYPE_DEFAULT.getDef()));
		}
		else{
			vehicle.setVehicleType(VehicleType.getEnum(vehicleType));
		}
			
		vehicleEnhanced.setVehicle(vehicle);
		
		if(StringUtils.isBlank(bodyHeight)){
			vehicleEnhanced.setBodyHeight(DefaultValues.BODY_HEIGHT.getDef());
		}
		else{
			vehicleEnhanced.setBodyHeight(bodyHeight);
		}
		if(StringUtils.isBlank(bodyLenght)){
			vehicleEnhanced.setBodyLenght(DefaultValues.BODY_LENGHT.getDef());
		}
		else{
			vehicleEnhanced.setBodyLenght(bodyLenght);
		}
		if(StringUtils.isBlank(bodyWeight)){
			vehicleEnhanced.setBodyWeight(DefaultValues.BODY_WEIGHT.getDef());
		}
		else{
			vehicleEnhanced.setBodyWeight(bodyWeight);
		}
		if(StringUtils.isBlank(dealer)){
			vehicleEnhanced.setDealer(DefaultValues.DEALER.getDef());
		}
		else{
			vehicleEnhanced.setDealer(dealer);
		}
		if(StringUtils.isBlank(owner)){
			vehicleEnhanced.setOwner(DefaultValues.OWNER.getDef());
		}
		else{
			vehicleEnhanced.setOwner(owner);
		}
		if(StringUtils.isBlank(suspensionType)){
			vehicleEnhanced.setSuspensionType(SuspensionType.SWING_AXLE);
		}
		else{
			vehicleEnhanced.setSuspensionType(SuspensionType.getEnum(suspensionType));
		}
		if(StringUtils.isBlank(suspensionType)){
			vehicleEnhanced.setTireCondition(TireCondition.NEW);
		}
		else{
			vehicleEnhanced.setTireCondition(TireCondition.getEnum(tireCondition));
		}
		
	
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(String engineCapacity) {
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
