package com.sw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw.dao.ApiDAO;
import com.sw.models.PeopleApi;

@Service
public class ApiService {

	@Autowired ApiDAO apiDAO;
	
	public String getRootApi(){
		return apiDAO.getRootApiSampleRestTemplate().toString();
	}
	

	public PeopleApi getPeopleApi(String id){
		return apiDAO.getPeopleApi(id);
	}
	
}
