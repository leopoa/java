package com.sw.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.sw.models.PeopleApi;
import com.sw.models.Vehicle;

@Service
public class VehicleService {

	public List<Vehicle> boardingVehicle(List<PeopleApi> peoples){
		if(CollectionUtils.isEmpty(peoples)) return null;
		
		Vehicle vehicle = new Vehicle();
		List<Vehicle> vehicles = Lists.newArrayList(vehicle);
		
		for (PeopleApi people : peoples) {
			if(vehicle.isFullOcupation(people.getMass())) vehicles.add(vehicle = new Vehicle());

			vehicle.addPassenger(people.getName(), people.getMass()); 
		}
		
		return vehicles;
	}
	
	public List<Vehicle> boardingBySpecie(Map<String, List<PeopleApi>> peopleBySpecie){
		List<Vehicle> vehicles = Lists.newArrayList();
		for(String key : peopleBySpecie.keySet()){
			buildVehicles(peopleBySpecie.get(key), vehicles);
		}
		return vehicles;
	}

	private void buildVehicles(List<PeopleApi> peoples, List<Vehicle> vehicles) {
		List<Vehicle> vehiclesBySpecie = boardingVehicle(peoples);
		if(!CollectionUtils.isEmpty(vehiclesBySpecie)) vehicles.addAll(vehiclesBySpecie);
	}
}
