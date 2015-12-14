package ro.fortech.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import ro.fortech.history.SearchSave;

@Singleton
public class SavedSearchCache implements Serializable {
	
	@PostConstruct
	public void init() {
		System.out.println("SavedSearchCache: Singleton");
	}
	
	private static final long serialVersionUID = -4384690421304399293L;

	private Map<String, List<SearchSave>> searchSaveCache = new HashMap<>();

	public Map<String, List<SearchSave>> getSearchSaveCache() {
		return searchSaveCache;
	}

	public void setSearchSaveCache(Map<String, List<SearchSave>> searchSaveCache) {
		this.searchSaveCache = searchSaveCache;
	}
}
