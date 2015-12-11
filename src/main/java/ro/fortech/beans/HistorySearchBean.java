package ro.fortech.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import ro.fortech.constants.*;
import ro.fortech.cache.HistorySearchCache;
import ro.fortech.search.VehicleSearchRequest;

@ManagedBean(name = "historySearchBean")
@SessionScoped
public class HistorySearchBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HistorySearchCache historySearchCache;

	private List<VehicleSearchRequest> searchHistory = new ArrayList<>();
	HistorySearch historySearch;

	public void getHistory(VehicleSearchRequest searchRequest) {

		historySearchCache.addHistorySearch(Constants.USER, searchRequest);

	}

	public List<VehicleSearchRequest> getSearchHistory() {
		return searchHistory;
	}

	public void setSearchHistory(List<VehicleSearchRequest> searchHistory) {
		this.searchHistory = searchHistory;
	}

}
