package com.sssunday.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sssunday.annotation.TokenAccess;

import net.sf.json.JSONObject;

/**
 * 拦截器，验证token
 * @author sssunday
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//拦截所有请求，默认不需要验证。返回true
		boolean flag = true;
		//判断handler是否为方法的处理器，或者说该请求是否执行某个方法
		if(handler instanceof HandlerMethod){
			//获取方法信息，反射
			HandlerMethod hm = (HandlerMethod)handler;
			Method method = hm.getMethod();
			if(method.isAnnotationPresent(TokenAccess.class)){
				// 当方法上存在TokenAccess注解时，表示此方法需要被校验，默认值改为false，校验成功才允许通过拦截(true)
				flag = false;
				//校验过程：根据id或者手机号从redis获取token，和参数中的的token对比
				
				// do checkToken;
				flag = true;
				
				//需要被拦截的方法在没有通过验证时，通过response传递提示信息
				if(!flag){
					Map<String,Object> checkMap = new HashMap<String,Object>();
					checkMap.put("msg", "提示信息");
					JSONObject responseJSONObject = JSONObject.fromObject(checkMap);  
				    response.setCharacterEncoding("UTF-8");  
				    response.setContentType("application/json; charset=utf-8"); 
				    response.getWriter().write(responseJSONObject.toString());
				}
			}
		}
		return flag;
	}
}
