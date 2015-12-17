package ro.fortech.beans;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ro.fortech.model.Vehicle;
import ro.fortech.search.VehicleEnhanceSearchResponse;
import ro.fortech.search.response.SearchResponseService;

@Named
@ViewScoped
public class EnhancedVehicleBean {

	@EJB
	private SearchResponseService searchResponseService;
	
	private VehicleEnhanceSearchResponse enhancedVehicle;
	private String fin;

	public VehicleEnhanceSearchResponse getEnhancedVehicle() {
		return enhancedVehicle;
	}

	public String searchForEnhancedVehicle(Vehicle vehicle) {
		System.out.println("searchForEnhancedVehicle(): The fin value is "+vehicle.getFin());
		//VehicleEnhanceSearchResponse response = searchResponseService.getVehicleEnhancedByFin("alandala", fin); 
		//VehicleEnhanceSearchResponse response = searchResponseService.getVehicleEnhancedByFin("alandala", "RO5347UK34"); 
		//this.enhancedVehicle = response;
		System.out.println("searchForEnhancedVehicle(): Redirecting");
		return "extraInfo.xhtml";
	}

	public String getFin() {
		return fin;
	}
	
	public void attrListener(ActionEvent event){
		 System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		this.fin = (String)event.getComponent().getAttributes().get("fin");
	 System.out.println(fin);
	  }
	  

	public void setFin(String fin) {
		System.out.println("I am sessting the fin "+fin);
		this.fin = fin;
	}
	
}
