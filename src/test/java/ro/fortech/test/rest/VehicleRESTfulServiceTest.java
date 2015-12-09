package ro.fortech.test.rest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import ro.fortech.constants.Constants;
import ro.fortech.credentials.LoginCredentials;

public class VehicleRESTfulServiceTest {

	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String INVALID_USER_TOKEN = "QmFzaWMgdXNlcjA6cGFzczA=1";
	private static final String VALID_USER_TOKEN = "QmFzaWMgdXNlcjA6cGFzczA=";
	private static final String AUTHORIZATION = "Authorization";
	
	private static final String URL_POST_INIT_USER_CACHE = "http://localhost:9080/client-service/rest/vehicle/users";
	private static final String URL_POST_CREATE_USER_UNIQUE_TOKEN_ACCOUNT_VALID = "http://localhost:9080/client-service/rest/vehicle/token";
	private static final String URL_GET_FILTERED_VEHICLE_LIST = "http://localhost:9080/client-service/rest/vehicle/filtered";
	private static final String URL_GET_SEARCH_HISTORY = "http://localhost:9080/client-service/rest/vehicle/search/history";
	private static final String URL_POST_SAVE_SEARCH_HISTORY = "http://localhost:9080/client-service/rest/vehicle/search/history/save/demo";
	private static final String URL_GET_SAVE_SEARCH_HISTORY = "http://localhost:9080/client-service/rest/vehicle/search/history/saved";
	private static final String URL_GET_ENHANCED_VEHICLE_BY_FIN = "http://localhost:9080/client-service/rest/vehicle/search/GR3847UC3218345";
	
	private HttpClient client;
	private HttpPost postRequestInitUserCache;
	private HttpPost postUserCredentialsForToken;
	private HttpPost getFilteredVehicles;
	private HttpGet getSearchHistory;
	private HttpPost saveFilteredVehiclesSearch;
	private HttpGet getSearchSavedHistory;
	private HttpGet getEnhancedVehicle;
	
	@Before
	public void init() {
		client = HttpClientBuilder.create().build();
		postRequestInitUserCache = new HttpPost(URL_POST_INIT_USER_CACHE);
		postUserCredentialsForToken = new HttpPost(URL_POST_CREATE_USER_UNIQUE_TOKEN_ACCOUNT_VALID);
		getFilteredVehicles = new HttpPost(URL_GET_FILTERED_VEHICLE_LIST);
		getSearchHistory = new HttpGet(URL_GET_SEARCH_HISTORY);
		saveFilteredVehiclesSearch = new HttpPost(URL_POST_SAVE_SEARCH_HISTORY);
		getSearchSavedHistory = new HttpGet(URL_GET_SAVE_SEARCH_HISTORY);
		getEnhancedVehicle = new HttpGet(URL_GET_ENHANCED_VEHICLE_BY_FIN);
	}
	
	
	@Test
	public void initUserCache_userCacheInintiated_successStatus200() {
		
		postRequestInitUserCache.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		HttpResponse response = null;
		try {
			response = client.execute(postRequestInitUserCache);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(201, response.getStatusLine().getStatusCode());
	}

	@Test
	public void getUserToken_userTokenGenerated_successStatus200() {
		HttpResponse response = null;
		LoginCredentials credentials = new LoginCredentials();
		credentials.setUsername("user0");
		credentials.setPassword("pass0");
		String jsonCredentialsFormat = credentials.toString();
		postUserCredentialsForToken.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		try {
			postUserCredentialsForToken.setEntity(new StringEntity(jsonCredentialsFormat));
			response = client.execute(postUserCredentialsForToken);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(200, response.getStatusLine().getStatusCode());
		assertEquals("Authorization: QmFzaWMgdXNlcjA6cGFzczA=", response.getAllHeaders()[1].toString());
	}
	
	@Test
	public void getUserToken_userTokenNotGenerated_unauthorizedStatus401() {
		HttpResponse response = null;
		LoginCredentials credentials = new LoginCredentials();
		credentials.setUsername("user0");
		credentials.setPassword("pass1");
		String jsonCredentialsFormat = credentials.toString();
		postUserCredentialsForToken.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		try {
			postUserCredentialsForToken.setEntity(new StringEntity(jsonCredentialsFormat));
			response = client.execute(postUserCredentialsForToken);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		assertEquals(401, response.getStatusLine().getStatusCode());
	}
	
	
	@Test
	public void getVehiclesBySearchCriteria_obtainedFilteredList_successStatus200() {
		HttpResponse response = null;
		HttpEntity entity = null;
		String content = null;
		getFilteredVehicles.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getFilteredVehicles.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		try {
			getFilteredVehicles.setEntity(new StringEntity(Constants.VEHICLE_FILTER_CRITERIA));
			response = client.execute(getFilteredVehicles);
			entity = response.getEntity();
			content = new BasicResponseHandler().handleEntity(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(content.length() > 3);
		assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void getVehiclesBySearchCriteria_failToObtainedFilteredList_unauthorizedStatus401() {
		HttpResponse response = null;
		HttpEntity entity = null;
		String content = null;
		getFilteredVehicles.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getFilteredVehicles.addHeader(AUTHORIZATION, INVALID_USER_TOKEN);
		try {
			getFilteredVehicles.setEntity(new StringEntity(Constants.VEHICLE_FILTER_CRITERIA));
			response = client.execute(getFilteredVehicles);
			entity = response.getEntity();
			content = new BasicResponseHandler().handleEntity(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(content.length() < 3);
		assertEquals(401, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void getSearchHistory_obtainedSearchHistory_successStatus200() {
		HttpResponse response = null;
		HttpEntity entity = null;
		String content = null;
		getSearchHistory.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getSearchHistory.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		getFilteredVehicles.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getFilteredVehicles.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		try {
			getFilteredVehicles.setEntity(new StringEntity(Constants.VEHICLE_FILTER_CRITERIA));
			client.execute(getFilteredVehicles);
			response = client.execute(getSearchHistory);
			entity = response.getEntity();
			content = new BasicResponseHandler().handleEntity(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(content.length() > 3);
		assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void getSearchHistory_failToObtainSearchHistory_unauthorizedStatus401() {
		HttpResponse response = null;
		HttpEntity entity = null;
		String content = null;
		getSearchHistory.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getSearchHistory.addHeader(AUTHORIZATION, INVALID_USER_TOKEN);
		getFilteredVehicles.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getFilteredVehicles.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		try {
			getFilteredVehicles.setEntity(new StringEntity(Constants.VEHICLE_FILTER_CRITERIA));
			client.execute(getFilteredVehicles);
			response = client.execute(getSearchHistory);
			entity = response.getEntity();
			content = new BasicResponseHandler().handleEntity(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(content.length() < 3);
		assertEquals(401, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void saveSearchRequest_savedSearch_successStatus200() {
		HttpResponse response = null;
		saveFilteredVehiclesSearch.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		saveFilteredVehiclesSearch.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		try {
			saveFilteredVehiclesSearch.setEntity(new StringEntity(Constants.VEHICLE_FILTER_CRITERIA));
			response = client.execute(saveFilteredVehiclesSearch);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void saveSearchRequest_faileToSaveSearch_unauthorizedStatus401() {
		HttpResponse response = null;
		saveFilteredVehiclesSearch.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		saveFilteredVehiclesSearch.addHeader(AUTHORIZATION, INVALID_USER_TOKEN);
		try {
			saveFilteredVehiclesSearch.setEntity(new StringEntity(Constants.VEHICLE_FILTER_CRITERIA));
			response = client.execute(saveFilteredVehiclesSearch);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(401, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void getSearchSavedHistory_obtainedSearchHistory_successStatus200() {
		HttpResponse response = null;
		HttpEntity entity = null;
		String content = null;
		getSearchSavedHistory.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getSearchSavedHistory.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		saveFilteredVehiclesSearch.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		saveFilteredVehiclesSearch.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		try {
			saveFilteredVehiclesSearch.setEntity(new StringEntity(Constants.VEHICLE_FILTER_CRITERIA));
			client.execute(saveFilteredVehiclesSearch);
			response = client.execute(getSearchSavedHistory);
			entity = response.getEntity();
			content = new BasicResponseHandler().handleEntity(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(content.length() > 3);
		assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void getSearchSavedHistory_failToObtainSearchHistory_unauthorizedStatus401() {
		HttpResponse response = null;
		HttpEntity entity = null;
		String content = null;
		getSearchSavedHistory.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getSearchSavedHistory.addHeader(AUTHORIZATION, INVALID_USER_TOKEN);
		saveFilteredVehiclesSearch.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		saveFilteredVehiclesSearch.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		try {
			saveFilteredVehiclesSearch.setEntity(new StringEntity(Constants.VEHICLE_FILTER_CRITERIA));
			client.execute(saveFilteredVehiclesSearch);
			response = client.execute(getSearchSavedHistory);
			entity = response.getEntity();
			content = new BasicResponseHandler().handleEntity(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(content.length() < 3);
		assertEquals(401, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void getVehicleByFin_obtainedEnhancedVehicle_successStatus200() {
		HttpResponse response = null;
		HttpEntity entity = null;
		String content = null;
		getEnhancedVehicle.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getEnhancedVehicle.addHeader(AUTHORIZATION, VALID_USER_TOKEN);
		try {
			response = client.execute(getEnhancedVehicle);
			entity = response.getEntity();
			content = new BasicResponseHandler().handleEntity(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(content.length() > 3);
		assertEquals(200, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void getVehicleByFin_failToBbtainEnhancedVehicle_unauthorizedStatus401() {
		HttpResponse response = null;
		HttpEntity entity = null;
		String content = null;
		getEnhancedVehicle.addHeader(CONTENT_TYPE, APPLICATION_JSON);
		getEnhancedVehicle.addHeader(AUTHORIZATION, INVALID_USER_TOKEN);
		try {
			response = client.execute(getEnhancedVehicle);
			entity = response.getEntity();
			content = new BasicResponseHandler().handleEntity(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(content.length() < 3);
		assertEquals(401, response.getStatusLine().getStatusCode());
	}
}
