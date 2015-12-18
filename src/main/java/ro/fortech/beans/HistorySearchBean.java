package ro.fortech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ro.fortech.cache.HistorySearchCache;
import ro.fortech.constants.Constants;
import ro.fortech.search.VehicleSearchRequest;

@Stateless
public class HistorySearchBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private HistorySearchCache historySearchCache;

	private List<VehicleSearchRequest> searchHistory = new ArrayList<>();
	
	public List<VehicleSearchRequest> getSearchHistory() {
		return searchHistory;
	}

	public void setSearchHistory(List<VehicleSearchRequest> searchHistory) {
		this.searchHistory = searchHistory;
	}

	public void addToHistorySearch(VehicleSearchRequest searchRequest) {

		historySearchCache.addHistorySearch(Constants.USER, searchRequest);
		searchHistory = historySearchCache.getSearchHistory().get(Constants.USER);
	}

}
