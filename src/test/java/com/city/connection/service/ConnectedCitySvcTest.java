package com.city.connection.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import connected.city.Routes;
import connected.city.CityRoutes;
import connected.city.svc.ConnectedCitySvc;

public class ConnectedCitySvcTest {
	
	@InjectMocks
	private ConnectedCitySvc connectedCitySvc;
	
	@Mock
	private Routes routes;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testYes() {
		String origin = "boston";
		String destination = "philadelphia";
		CityRoutes cityRoutes = Mockito.mock(CityRoutes.class);
		Mockito.when(routes.getCityRoutes()).thenReturn(cityRoutes);
		Mockito.when(cityRoutes.isRouteAvailable(origin, destination)).thenReturn(true);
		Assert.assertEquals("yes", connectedCitySvc.checkConnection(origin, destination));
	}
	
	@Test
	public void testNo() {
		String origin = "boston";
		String destination = "philadelphia";
		CityRoutes cityRoutes = Mockito.mock(CityRoutes.class);
		Mockito.when(cityRoutes.isRouteAvailable(origin, destination)).thenReturn(false);
		Mockito.when(routes.getCityRoutes()).thenReturn(cityRoutes);
		Assert.assertEquals("no", connectedCitySvc.checkConnection(origin, destination));
	}

}
