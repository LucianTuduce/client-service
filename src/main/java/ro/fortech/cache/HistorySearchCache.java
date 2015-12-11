package ro.fortech.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import ro.fortech.def.value.DefaultValues;
import ro.fortech.search.VehicleSearchRequest;

@Singleton
public class HistorySearchCache implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, List<VehicleSearchRequest>> searchHistory = new HashMap<>();
	
	@PostConstruct
	public void init(){
		System.out.println("HistorySearchCache: is created");
	}

	public Map<String, List<VehicleSearchRequest>> getSearchHistory() {
		return searchHistory;
	}

	public void setSearchHistory(
			Map<String, List<VehicleSearchRequest>> searchHistory) {
		this.searchHistory = searchHistory;
	}


	void addHistorySearch(String user, VehicleSearchRequest searchRequest) {
		
		if(searchHistory.get(user).size() == DefaultValues.HISTORY_SIZE.getDefValue() ){
				addToHistoryListToStart(searchRequest, user);
		}
		else{
			searchHistory.get(user).add(0,searchRequest);
		}
	}
	
	void  addToHistoryListToStart( VehicleSearchRequest searchRequest, String user){
		searchHistory.get(user).add(0,searchRequest);
		searchHistory.get(user).remove(searchHistory.get(user).size()-1);
		
	}
	
}
