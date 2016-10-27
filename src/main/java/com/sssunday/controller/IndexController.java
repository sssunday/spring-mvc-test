package com.sssunday.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sssunday.common.utils.CaptchaUtils;
import com.sssunday.common.utils.QiNiuUploadUtil;
import com.sssunday.common.utils.ReadJsonFile;
import com.sssunday.model.Area;
import com.sssunday.model.Captcha;
import com.sssunday.model.City;
import com.sssunday.model.Parent;
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
	@RequestMapping(value="listFile",produces = "application/json;charset=utf-8")
    public String listFile(HttpServletRequest request) throws Exception {
		String listJson = new ReadJsonFile().ReadFile(request.getServletContext().getRealPath("/")+"resources/json/list.json");
		ObjectMapper mapper = new ObjectMapper();
		List<String> provinces =  mapper.readValue(listJson, new TypeReference<List<String>>() {});
		for (String string : provinces) {
			System.out.println(string.trim());
		}
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
	
	/*@ResponseBody
	@RequestMapping("/insertTestIndex")
	public String insertTestIndex(Long id,String name,Long age){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		for (int j = 0; j < 100000; j++) {
			for (int i = 0; i < 20; i++) {
				paramMap.put("str"+(i+1), UUID.randomUUID().toString().substring(0, 8));
			}
			userService.insertTestIndex(paramMap);
			System.out.println(j);
		}
		
		
		return paramMap.toString();
	}*/
	
	@ResponseBody
	@RequestMapping("/getUpToken")
	public Map<String,Object> getUpToken(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uptoken", QiNiuUploadUtil.getUpToken());
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/testCollection")
	public Map<String,Object> testCollection(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Parent> parents = userService.testCollection();
		map.put("data", parents);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/getCaptcha")
	public void getCaptcha(HttpServletRequest req,HttpServletResponse resp,String mobileTel) throws IOException{
		
		//Captcha map = userService.getCaptcha(mobileTel);
		
		Captcha captcha = CaptchaUtils.getCaptcha();
		//System.out.print(randomCode);
		// 禁止图像缓存。
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		resp.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(captcha.getBuffImg(), "jpeg", sos);
		sos.close();
	}
}
