package ro.fortech.search.response;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ro.fortech.cache.SavedSearchCache;
import ro.fortech.fake.FakeVehicleServiceImpl;
import ro.fortech.search.VehicleSearchRequest;
import ro.fortech.search.response.SearchResponseService;
import ro.fortech.services.VehicleSearchService;
import ro.fortech.services.VehicleService;
import ro.fortech.type.FuelType;
import ro.fortech.type.VehicleType;

@RunWith(MockitoJUnitRunner.class)
public class SearchResponseServiceTest {

	@InjectMocks
	private SearchResponseService searchResponseService;

	@Mock
	private SavedSearchCache searchCache;

	@Mock
	private VehicleSearchService vehicleSerachService;

	@Before
	public void setUp() {
		searchResponseService = new SearchResponseService();
	}

	@Test
	public void getUserSearchHistory_obtainSearchHistory_Success() {
		VehicleSearchRequest request = new VehicleSearchRequest();
		request.setFin(" ");
		request.setFuelType(FuelType.DEFAULT);
		request.setLocation("Germania");
		request.setMaxCapacity(0);
		request.setMinCapacity(0);
		request.setMaxPrice(0);
		request.setMinPrice(0);
		request.setVehicleType(VehicleType.VAN);
		request.setMaxYear(0);
		request.setMinYear(0);
		VehicleService fakeService = Mockito.mock(FakeVehicleServiceImpl.class);
		SavedSearchCache searchCache = Mockito.mock(SavedSearchCache.class);
		searchResponseService.searchCache = searchCache;
		searchResponseService.fakeService = fakeService;
		Mockito.when(searchResponseService.getFilteredVehiclesBySearchCriteria(" ",request)).thenReturn(Response.status(Response.Status.OK).build());
		assertEquals(200,searchResponseService.getFilteredVehiclesBySearchCriteria(" ", new VehicleSearchRequest()));
	}

}
