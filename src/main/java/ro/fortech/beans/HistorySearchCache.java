package ro.fortech.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;

import ro.fortech.search.VehicleSearchRequest;

@SessionScoped
public class HistorySearchCache {

	private List<VehicleSearchRequest> searchHistoryRequests = new ArrayList<>();

	public List<VehicleSearchRequest> getSearchHistoryRequests() {
		return searchHistoryRequests;
	}

	public void setSearchHistoryRequests(List<VehicleSearchRequest> searchHistoryRequests) {
		this.searchHistoryRequests = searchHistoryRequests;
	}
	
}
