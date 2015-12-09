package ro.fortech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import ro.fortech.search.VehicleSearchRequest;

@SessionScoped
public class HistorySearchCache implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init(){
		System.out.println("HistorySearchCache: is created");
	}

	private List<VehicleSearchRequest> searchHistoryRequests = new ArrayList<>();

	public List<VehicleSearchRequest> getSearchHistoryRequests() {
		return searchHistoryRequests;
	}

	public void setSearchHistoryRequests(List<VehicleSearchRequest> searchHistoryRequests) {
		this.searchHistoryRequests = searchHistoryRequests;
	}
	
}
