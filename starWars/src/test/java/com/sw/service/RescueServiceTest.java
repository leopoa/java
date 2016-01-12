package com.sw.service;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sw.models.PeopleApi;

public class RescueServiceTest {

	RescueService service;
	
	@Before
	public void setUp(){
		service = new RescueService();
	}
	
	
	@Test
	public void orderPeopleTest(){
		List<PeopleApi> orderned = service.orderAllPeopleByBirthYear(mockPeopleBirthYear());
		
		orderned.forEach( s -> System.out.println(s.getName() + " - " + s.getBirthYear()));
		
		Assert.assertEquals("Tarzan", orderned.get(0).getName()); 
		Assert.assertEquals("Ariel", orderned.get(1).getName()); 
		Assert.assertEquals("Artur", orderned.get(2).getName()); 
		Assert.assertEquals("Sid", orderned.get(3).getName()); 
		Assert.assertEquals("Leo", orderned.get(4).getName()); 
		Assert.assertEquals("Mateus", orderned.get(5).getName()); 
		Assert.assertEquals("Marcos", orderned.get(6).getName()); 
		Assert.assertEquals("Diego", orderned.get(7).getName()); 
	}
	
	
	@Test
	public void getFirstPeopleEachSpecie(){
		List<PeopleApi> elders = service.getFirstPeopleEachSpecie(mockGroupedBySpecie());
		Assert.assertEquals(4, elders.size()); 
	}
	
	private Map<String, List<PeopleApi>> mockGroupedBySpecie(){
		return Maps.newHashMap(ImmutableMap.of(
				"1", Lists.newArrayList(createPeopleWithSpecie("Artur", "1"), createPeopleWithSpecie("Ariel", "1")),
        		"2", Lists.newArrayList(createPeopleWithSpecie("Tarzan", "2"), createPeopleWithSpecie("Diego", "2")),
        		"3", Lists.newArrayList(createPeopleWithSpecie("Marcos", "3"), createPeopleWithSpecie("Sid", "3")),
        		"4", Lists.newArrayList(createPeopleWithSpecie("Mateus", "4")),
        		"5", Lists.newArrayList()));
	}
	
	private List<PeopleApi> mockPeopleBirthYear(){
		return Lists.newArrayList(
				createPeopleWithBirthYear("Artur", "33BBY"),
				createPeopleWithBirthYear("Tarzan", "896BBY"),
				createPeopleWithBirthYear("Marcos", "66.7ABY"),
				createPeopleWithBirthYear("Ariel", "41.9BBY"),
				createPeopleWithBirthYear("Diego", "unknown"),
				createPeopleWithBirthYear("Sid", "31.5BBY"),
				createPeopleWithBirthYear("Mateus", "1.2ABY"),
				createPeopleWithBirthYear("Leo", "19BBY")
		);
	}
	
	private PeopleApi createPeopleWithSpecie(String name, String specie){
		PeopleApi people = new PeopleApi();
		people.setName(name); 
		people.setSpecies(Lists.newArrayList(specie)); 
		return people;
	}
	
	private PeopleApi createPeopleWithBirthYear(String name, String birthYear){
		PeopleApi people = new PeopleApi();
		people.setName(name); 
		people.setBirthYear(birthYear);
		return people;
	}
	
	@Test
	public void getAllPeopleTest(){
		ApiService apiService = Mockito.mock(ApiService.class);
		Mockito.when(apiService.getPeopleApi(Mockito.anyString())).thenReturn(new PeopleApi());
		service.setApiService(apiService); 
		Assert.assertEquals(2, service.getAllPeople("1,2").size());
	}
	
	@Test
	public void groupByFirstSpecieTest(){
		List<PeopleApi> peoples = Lists.newArrayList(createPeopleWithSpecie("Artur", "1"), createPeopleWithSpecie("Tarzan", "2"), createPeopleWithSpecie("Ariel", "1"));
		Map<String, List<PeopleApi>> map = service.groupByFirstSpecie(peoples);
		Assert.assertEquals(2, map.size());
		Assert.assertEquals(2, map.get("1").size());
		Assert.assertEquals(1, map.get("2").size());
	}
	
	
	
}
