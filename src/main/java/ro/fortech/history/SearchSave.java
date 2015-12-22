package ro.fortech.history;

import ro.fortech.search.VehicleSearchRequest;

/**
 * Class used as model for the vehicle search save
 *
 */
public class SearchSave {

	private String name;
	private VehicleSearchRequest request;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public VehicleSearchRequest getRequest() {
		return request;
	}
	public void setRequest(VehicleSearchRequest request) {
		this.request = request;
	}
	
}
