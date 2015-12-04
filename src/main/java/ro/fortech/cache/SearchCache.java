package ro.fortech.cache;

import java.io.Serializable;
import java.util.HashMap;
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

	private Map<String, List<SearchSave>> searchSaveCache = new HashMap<>();
	private Map<String, List<VehicleSearchRequest>> searchRequests = new HashMap<>();

	public Map<String, List<SearchSave>> getSearchSaveCache() {
		return searchSaveCache;
	}

	public void setSearchSaveCache(Map<String, List<SearchSave>> searchSaveCache) {
		this.searchSaveCache = searchSaveCache;
	}

	public Map<String, List<VehicleSearchRequest>> getSearchRequests() {
		return searchRequests;
	}

	public void setSearchRequests(Map<String, List<VehicleSearchRequest>> searchRequests) {
		this.searchRequests = searchRequests;
	}

}
