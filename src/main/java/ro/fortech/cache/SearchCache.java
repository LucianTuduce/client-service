package ro.fortech.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import ro.fortech.history.SearchSave;
import ro.fortech.search.VehicleSearchRequest;

@SessionScoped
public class SearchCache implements Serializable {

	private static final long serialVersionUID = -4384690421304399293L;

	@PostConstruct
	public void init() {
		System.out.println("Built: Stateful SearchCache.");
	}

	private Map<Integer, List<SearchSave>> searchSaveCache;
	private List<VehicleSearchRequest> searchRequests;

	public List<VehicleSearchRequest> getSearchRequests() {
		return searchRequests;
	}

	public void setSearchRequests(List<VehicleSearchRequest> searchRequests) {
		this.searchRequests = searchRequests;
	}

	public Map<Integer, List<SearchSave>> getSearchSaveCache() {
		return searchSaveCache;
	}

	public void setSearchSaveCache(
			Map<Integer, List<SearchSave>> searchSaveCache) {
		this.searchSaveCache = searchSaveCache;
	}

}
