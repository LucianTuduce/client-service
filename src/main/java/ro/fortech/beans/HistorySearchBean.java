package ro.fortech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import ro.fortech.cache.HistorySearchCache;
import ro.fortech.constants.Constants;
import ro.fortech.search.VehicleSearchRequest;

@ManagedBean(name = "historySearchBean")
@SessionScoped
public class HistorySearchBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private HistorySearchCache historySearchCache;

	private List<VehicleSearchRequest> searchHistory = new ArrayList<>();
	HistorySearch historySearch;

	public List<VehicleSearchRequest> getSearchHistory() {
		return searchHistory;
	}

	public void setSearchHistory(List<VehicleSearchRequest> searchHistory) {
		this.searchHistory = searchHistory;
	}

	public void getHistory(VehicleSearchRequest searchRequest) {

		historySearchCache.addHistorySearch(Constants.USER, searchRequest);
		searchHistory = historySearchCache.getSearchHistory().get(Constants.USER);
	}

}
