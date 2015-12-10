package ro.fortech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import ro.fortech.def.value.DefaultValues;
import ro.fortech.search.VehicleSearchRequest;

@SessionScoped
public class HistorySearchCache implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<VehicleSearchRequest> searchHistory = new ArrayList<>();
	
	@PostConstruct
	public void init(){
		System.out.println("HistorySearchCache: is created");
	}
	
	public List<VehicleSearchRequest> getSearchHistory() {
		return searchHistory;
	}

	public void setSearchHistory(List<VehicleSearchRequest> searchHistory) {
		this.searchHistory = searchHistory;
	}

	public List<VehicleSearchRequest> getHistory(VehicleSearchRequest searchRequest) {
		
		if(searchHistory.size() == DefaultValues.HISTORY_SIZE.getDefValue() ){
			searchHistory = addToHistoryListToStart(searchHistory, searchRequest);
		}
		else{
			searchHistory.add(0,searchRequest);
		}
		return searchHistory;
	}
	
	public List<VehicleSearchRequest> addToHistoryListToStart(List<VehicleSearchRequest> list, VehicleSearchRequest searchRequest){
		list.add(0,searchRequest);
		list.remove(list.size()-1);
		return list;
	}
	
}
