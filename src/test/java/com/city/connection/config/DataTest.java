package com.city.connection.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import connected.city.Routes;
import connected.city.CityRoutes;
import connected.city.Data;

public class DataTest {

	private Data data;
	
	@Before
	public void setup() {
		data = new Data();
	}
	
	@Test
	public void testParseFile() {
		Routes cityConnectionStore = data.parseFile();
		CityRoutes cityRoutes = cityConnectionStore.getCityRoutes();
		Assert.assertTrue(cityRoutes.isRouteAvailable("Boston".toLowerCase(), "philadelphia".toLowerCase()));
	}
	
}
