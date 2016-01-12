package com.sw.models;

import org.junit.Assert;
import org.junit.Test;

public class VehicleTest {

	@Test
	public void vehicleFiveSpacesTest() {
		Vehicle vehicle = new Vehicle(5);
		Assert.assertFalse(vehicle.isFullOcupation("10")); 
		vehicle.addPassenger("Tarzan", "60");
		Assert.assertFalse(vehicle.isFullOcupation("100.9")); 
		vehicle.addPassenger("Velasques", "100.9");
		Assert.assertFalse(vehicle.isFullOcupation("unknown")); 
		vehicle.addPassenger("Sid", "unknown");
		Assert.assertTrue(vehicle.isFullOcupation("99")); 
	}

}
