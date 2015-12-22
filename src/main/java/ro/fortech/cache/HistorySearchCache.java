package ro.fortech.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import ro.fortech.def.value.DefaultValues;
import ro.fortech.search.VehicleSearchRequest;

/**
 * Class used as a fake database, here all the users search requests are
 * stored
 *
 */
@Singleton
public class HistorySearchCache implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Map<String, List<VehicleSearchRequest>> searchHistory = new HashMap<>();
	
	@PostConstruct
	public void init(){
		System.out.println("HistorySearchCache: singleton");
	}

	public Map<String, List<VehicleSearchRequest>> getSearchHistory() {
		return searchHistory;
	}

	public void setSearchHistory(Map<String, List<VehicleSearchRequest>> searchHistory) {
		this.searchHistory = searchHistory;
	}

	public void addHistorySearch(String user, VehicleSearchRequest searchRequest) {	
		
		if(searchHistory.get(user) == null){
			List<VehicleSearchRequest> vehicleSearchRequests = new ArrayList<>();
			vehicleSearchRequests.add(0, searchRequest);
			searchHistory.put(user, vehicleSearchRequests);
		}else if(searchHistory.get(user).size() < DefaultValues.HISTORY_SIZE.getDefValue()){
			searchHistory.get(user).add(0, searchRequest);
		}else if (searchHistory.get(user).size() == DefaultValues.HISTORY_SIZE.getDefValue() ){
			addToHistoryListStart(searchRequest, user);
		}
	}
	
	private void addToHistoryListStart(VehicleSearchRequest searchRequest, String user){
		searchHistory.get(user).add(0,searchRequest);
		searchHistory.get(user).remove(searchHistory.get(user).size()-1);		
	}
	
}
