package ro.fortech.history;

import ro.fortech.search.VehicleSearchRequest;

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
