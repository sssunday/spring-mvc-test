package com.sssunday.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sssunday.annotation.TokenAccess;

@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String username,String password){
		
		return null;
	}
	
	@RequestMapping("check3.txt")
    public String check() {
        return "/view/check.txt";
    }
	
	/**
	 * 登录之后的操作
	 * @param username
	 * @param password
	 * @return
	 */
	@TokenAccess
	@ResponseBody
	@RequestMapping("/operate")
	public String userOperate(String username,String token){
		System.out.println("验证通过，执行方法");
		return "check.txt";
	}
	
}
