package ro.fortech.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
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
	private String bodyLength;
	private String suspensionType;
	private String tireCondition;
	private VehicleEnhanced vehicleEnhanced = new VehicleEnhanced();

	public String addVehicle() {
		
		boolean verifyInfoFormUI = createVehicleEnhanced();
		
		if(verifyInfoFormUI){
			fakeCarService.saveVehicle(vehicleEnhanced.getVehicle());
			fakeCarService.saveVehicleEnhanced(vehicleEnhanced);
		}
		else{
			return "fail";
		}
		
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "resources/js/windowclose.xhtml");
		return "success";

	}
	
	public boolean createVehicleEnhanced(){
		
		Vehicle vehicle = new Vehicle();
		boolean verifyInfoFormUI= true;
		
		if(StringUtils.isBlank(fin)){
			//vehicle.setFin(DefaultValues.FIN_DEFAULT.getDef());
			this.fin = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicle.setFin(fin);
		}
		
		if(StringUtils.isBlank(location)){
			//vehicle.setLocation(DefaultValues.LOCATION_DEFAULT.getDef());
			this.location = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicle.setLocation(location);
		}
		
		if(StringUtils.isNumeric(engineCapacity)){
			vehicle.setEngineCapacity(Integer.parseInt(engineCapacity));
		}
		else{
			this.engineCapacity = "Must be a number";
			verifyInfoFormUI = false;
			//vehicle.setEngineCapacity(DefaultValues.MIN_CAPACITY_DEFAULT.getDefValue());
		}
		
		if(StringUtils.isBlank(fuelType)){
			//vehicle.setFuelType(FuelType.getEnum(DefaultValues.FUEL_TYPE_DEFAULT.getDef()));
			this.fuelType = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicle.setFuelType(FuelType.getEnum(fuelType));
		}
		
		if(StringUtils.isBlank(model)){
			//vehicle.setModel(DefaultValues.MODEL_DEFAULT.getDef());
			this.model = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicle.setModel(model);
		}
		
		if(!StringUtils.isNumeric(price)){
			//vehicle.setPrice(DefaultValues.MIN_PRICE_DEFAULT.getDefValue());
			this.price = "Must be a number";
			verifyInfoFormUI = false;
		}
		else{
			vehicle.setPrice(Integer.parseInt(price));
		}
		
		if(!StringUtils.isNumeric(year)){
			//vehicle.setPrice(DefaultValues.MIN_YEAR_DEFAULT.getDefValue());
			this.year = "Must be a number";
			verifyInfoFormUI = false;
		}
		else{
			vehicle.setYear(Integer.parseInt(year));
		}
		
		if(StringUtils.isBlank(vehicleType)){
			//vehicle.setVehicleType(VehicleType.getEnum(DefaultValues.VEHICLE_TYPE_DEFAULT.getDef()));
			this.vehicleType = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicle.setVehicleType(VehicleType.getEnum(vehicleType));
		}
			
		vehicleEnhanced.setVehicle(vehicle);
		
		if(!StringUtils.isNumeric(bodyHeight)){
			//vehicleEnhanced.setBodyHeight(DefaultValues.BODY_HEIGHT.getDef());
			this.bodyHeight = "Must be a number";
			verifyInfoFormUI = false;
		}
		else{
			vehicleEnhanced.setBodyHeight(bodyHeight);
		}
		if(!StringUtils.isNumeric(bodyLength)){
			//vehicleEnhanced.setBodyLenght(DefaultValues.BODY_LENGHT.getDef());
			this.bodyLength = "Must be a number";
			verifyInfoFormUI = false;
		}
		else{
			vehicleEnhanced.setBodyLength(bodyLength);
		}
		if(!StringUtils.isNumeric(bodyWeight)){
			//vehicleEnhanced.setBodyWeight(DefaultValues.BODY_WEIGHT.getDef());
			this.bodyWeight = "Must be a number";
			verifyInfoFormUI = false;
		}
		else{
			vehicleEnhanced.setBodyWeight(bodyWeight);
		}
		if(StringUtils.isBlank(dealer)){
			//vehicleEnhanced.setDealer(DefaultValues.DEALER.getDef());
			this.dealer = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicleEnhanced.setDealer(dealer);
		}
		if(StringUtils.isBlank(owner)){
			//vehicleEnhanced.setOwner(DefaultValues.OWNER.getDef());
			this.owner = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicleEnhanced.setOwner(owner);
		}
		if(StringUtils.isBlank(suspensionType)){
			//vehicleEnhanced.setSuspensionType(SuspensionType.SWING_AXLE);
			this.suspensionType = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicleEnhanced.setSuspensionType(SuspensionType.getEnum(suspensionType));
		}
		if(StringUtils.isBlank(tireCondition)){
			//vehicleEnhanced.setTireCondition(TireCondition.NEW);
			this.tireCondition = "Must be filled";
			verifyInfoFormUI = false;
		}
		else{
			vehicleEnhanced.setTireCondition(TireCondition.getEnum(tireCondition));
		}
		return verifyInfoFormUI;
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

	public String getBodyLength() {
		return bodyLength;
	}

	public void setBodyLength(String bodyLength) {
		this.bodyLength = bodyLength;
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
