package com.sw.service;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.sw.models.PeopleApi;
import com.sw.models.Vehicle;

public class VehicleServiceTest {

	@Test
	public void boardingVehicleTest(){
		VehicleService service = new VehicleService();
		List<Vehicle> vehicles = service.boardingVehicle(mockPeopleMass());
		Assert.assertEquals(3, vehicles.size()); 
		Assert.assertNull(service.boardingVehicle(null));
	}
	
	
	private List<PeopleApi> mockPeopleMass(){
		return Lists.newArrayList(
				createPeopleWithMass("Artur", "101"),
				createPeopleWithMass("Tarzan", "70"),
				createPeopleWithMass("Marcos", "66.70"),
				createPeopleWithMass("Ariel", "141.9"),
				createPeopleWithMass("Diego", "unknown"),
				createPeopleWithMass("Sid", "99.9")
		);
	} 
	
	private PeopleApi createPeopleWithMass(String name, String mass){
		PeopleApi people = new PeopleApi();
		people.setName(name);
		people.setMass(mass); 
		return people;
	}

}
