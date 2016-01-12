package com.sw.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rescue {

	private List<Vehicle> vehicles;
	
	public Rescue(List<Vehicle> vehicles){
		this.vehicles = vehicles;
	}
	
	public String getTravels() {
		return "Number of travels: " + vehicles.size();
	}
	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
}
