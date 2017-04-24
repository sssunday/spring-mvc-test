package com.sssunday.common.utils;

import com.google.gson.Gson;

public class JsonpUtils {

	public static String jsonpBuild(String callback,Object o){
		Gson gson=new Gson();
		String json=gson.toJson(o);
		return callback+"("+json+")";
	}
}
