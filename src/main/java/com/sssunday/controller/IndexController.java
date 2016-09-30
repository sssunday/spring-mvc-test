package com.sssunday.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sssunday.common.utils.ReadJsonFile;
import com.sssunday.model.Area;
import com.sssunday.model.City;
import com.sssunday.model.Province;
import com.sssunday.model.User;
import com.sssunday.service.IUserService;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	IUserService userService;
	
	Logger log = Logger.getLogger(IndexController.class);
	
	@RequestMapping("/index")
	public String index2(){
		return "/view/index.html";
	}

	@ResponseBody
	@RequestMapping(value="jsonFile",produces = "application/json;charset=utf-8")
    public String jsonFile(HttpServletRequest request) throws Exception {
		String areaJson = new ReadJsonFile().ReadFile(request.getServletContext().getRealPath("\\")+"resources\\json\\area.json");
		String provinceJson = new ReadJsonFile().ReadFile(request.getServletContext().getRealPath("\\")+"resources\\json\\city.json");
		ObjectMapper mapper = new ObjectMapper();
		List<Province> provinces =  mapper.readValue(provinceJson, new TypeReference<List<Province>>() {});
		List<Area> areas =  mapper.readValue(areaJson, new TypeReference<List<Area>>() {});
		for (Province province : provinces) {
			for (City city : province.getCity()) {
				city.setArea(new ArrayList<Area>());
				for (Area area : areas) {
					if(area.getPid().equals(city.getId())){
						city.getArea().add(area);
					}
				}
			}
		}
		System.out.println(provinces);
		return mapper.writeValueAsString(provinces);
    }
	
	@ResponseBody
	@RequestMapping("/queryUser")
	public List<User> queryUser(Integer id){
		List<User> userList = new ArrayList<User>();
		try {
			userList =  userService.queryUser(id);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return userList;
	}
	
	@ResponseBody
	@RequestMapping("/insertUser")
	public String insertUser(Long id,String name,Long age){
		User user = new User();
		try {
			user.setAge(age);
			user.setName(name);
			int i =  userService.insertUser(user);
			System.out.println(i);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return user.toString();
	}
}
