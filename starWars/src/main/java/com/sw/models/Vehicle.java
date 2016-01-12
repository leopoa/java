package com.sw.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {

	private List<String> namesPassenger;
	private Integer freeSpace;
	
	public Vehicle(){
		namesPassenger = Lists.newArrayList();
		freeSpace = 4;
	}
	
	public Vehicle(Integer passengersTotal){
		namesPassenger = Lists.newArrayList();
		freeSpace = passengersTotal;
	}
	
	public void addPassenger(String name, String mass){
		namesPassenger.add(name + "("+mass+")"); 
		freeSpace -= ruleToOccupateTwoSpaces(mass) ? 2 : 1;
	}
	
	public boolean isFullOcupation(String mass){
		return ruleToOccupateTwoSpaces(mass) ? this.freeSpace - 2 < 0 : this.freeSpace - 1 < 0 ;
	}
	
	public boolean ruleToOccupateTwoSpaces(String mass){
		return "unknown".equalsIgnoreCase(mass) || Float.valueOf(mass) > 100f;
	}
	
	public List<String> getNamesPassenger() {
		return namesPassenger;
	}

	public void setNamesPassenger(List<String> namesPassenger) {
		this.namesPassenger = namesPassenger;
	}
	
}
