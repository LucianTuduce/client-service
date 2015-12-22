package ro.fortech.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import ro.fortech.history.SearchSave;

/**
 * Class used as a fake database, here all the users saved search requests are
 * stored
 *
 */
@Singleton
public class SavedSearchCache implements Serializable {
	
	private static final long serialVersionUID = -4384690421304399293L;

	@PostConstruct
	public void init() {
		System.out.println("SavedSearchCache: Singleton");
	}

	private Map<String, List<SearchSave>> searchSaveCache = new HashMap<>();

	public Map<String, List<SearchSave>> getSearchSaveCache() {
		return searchSaveCache;
	}

	public void setSearchSaveCache(Map<String, List<SearchSave>> searchSaveCache) {
		this.searchSaveCache = searchSaveCache;
	}
}
