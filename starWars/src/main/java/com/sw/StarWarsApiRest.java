package com.sw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sw.models.Rescue;
import com.sw.service.ApiService;
import com.sw.service.RescueService;

@RestController
public class StarWarsApiRest {

	@Autowired ApiService apiService;
	@Autowired RescueService rescueService;
	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "tan tan tannnn";
	}

	@RequestMapping("/root")
	@ResponseBody
	public String root()  {
		return apiService.getRootApi();
	}
	
	
	@RequestMapping("/people/{id}")
	@ResponseBody
	public String people(@PathVariable("id")  String id)  {
		return apiService.getPeopleApi(id).toString();
	}
	
	@RequestMapping("/rescue")
	@ResponseBody
	public Rescue rescue(@RequestParam("ids") String ids)  {
		return rescueService.rescue(ids);
	}

}
