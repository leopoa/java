package com.sw.models;

import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleApi {

	private String name;
	private String gender;
	private String mass;
	private List<String> species;
	@JsonProperty("birth_year") private String birthYear;
	
	public String getYear() {
		return StringUtils.isEmpty(birthYear) ? "unknow" : birthYear.replaceAll("[0-9.,]+", "");
	}
	
	public Integer getBirth() {
		return StringUtils.isEmpty(birthYear) ? null : getNumber(getDigits(birthYear));
	}
	
	private Integer getNumber(String number){
		return isNumber(number) ? null : isInteger(number) ? Double.valueOf(number).intValue() : Double.valueOf(number).intValue()+1;
	}
	
	private String getDigits(String birthYear){
		return birthYear.replaceAll("[^\\d.]", "");
	}
	
	private boolean isNumber(String number){
		return number.length() < 1;
	}
	
	private boolean isInteger(String number){
		return Double.valueOf(number)%1==0.0f;
	}
	
	public String getFirstSpecie(){
		return species.get(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public List<String> getSpecies() {
		return species;
	}

	public void setSpecies(List<String> species) {
		this.species = species;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
}
