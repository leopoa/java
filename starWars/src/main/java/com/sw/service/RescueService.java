package com.sw.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.sw.models.PeopleApi;
import com.sw.models.Rescue;
import com.sw.models.Vehicle;

@Service
public class RescueService {

	@Autowired VehicleService vehicleService;
	@Autowired ApiService apiService;
	
	public Rescue rescue(String ids){
		List<Vehicle> vehicles = Lists.newArrayList();
		if(StringUtils.isEmpty(ids)) return null;
		
		Map<String, List<PeopleApi>> peopleBySpecie =  groupSpecieOrdered(groupByFirstSpecie(getAllPeople(ids)));
		
		vehicles.addAll(vehicleService.boardingVehicle(getFirstPeopleEachSpecie(peopleBySpecie)));
		vehicles.addAll(vehicleService.boardingBySpecie(peopleBySpecie));

		return new Rescue(vehicles);
	}
	
	public List<PeopleApi> getAllPeople(String ids){
		return Arrays.asList(ids.split(",")).stream().map(m -> apiService.getPeopleApi(m)).collect(Collectors.toList());
	}
	
	public Map<String, List<PeopleApi>> groupByFirstSpecie(List<PeopleApi> peoples){
		return peoples.stream().collect(Collectors.groupingBy(PeopleApi::getFirstSpecie));
	}
	
	public Map<String, List<PeopleApi>> groupSpecieOrdered(Map<String, List<PeopleApi>> peoples){
		return peoples.keySet().stream().collect(Collectors.toMap( e -> e, e -> orderAllPeopleByBirthYear( peoples.get(e) ) ));
	}
	
	public List<PeopleApi> orderAllPeopleByBirthYear(List<PeopleApi> peoples){
		Comparator<PeopleApi> byBirthYear = (e1, e2) -> 
				"unknown".equals(e1.getBirthYear()) ?  1 : 
				"unknown".equals(e2.getBirthYear()) ? -1 : 
				e1.getYear().equals(e2.getYear()) && "BBY".equals(e1.getYear()) ?  Double.valueOf(e2.getBirth()).compareTo(Double.valueOf(e1.getBirth())) : 
				e1.getYear().equals(e2.getYear()) && "ABY".equals(e2.getYear()) ?  Double.valueOf(e1.getBirth()).compareTo(Double.valueOf(e2.getBirth())) : 
				"ABY".equals(e2.getYear()) ? -1 : 1 ;
				
		return peoples.stream().sorted(byBirthYear).collect(Collectors.toList());
	}
	
	public List<PeopleApi> getFirstPeopleEachSpecie(Map<String, List<PeopleApi>> peoplesBySpecie){
		List<PeopleApi> elders = new ArrayList<PeopleApi>();
		for (String key : peoplesBySpecie.keySet()) {
			if(!CollectionUtils.isEmpty(peoplesBySpecie.get(key))) elders.add(peoplesBySpecie.get(key).remove(0));
		}
		return elders;
	}
	
	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}
}
