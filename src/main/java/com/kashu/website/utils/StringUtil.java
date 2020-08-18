package com.kashu.website.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StringUtil {

	/*
	 * https://stackoverflow.com/questions/6873020/gson-date-format
	 */
	public static String beanToJSONString(Object bean) {
		//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    return gson.toJson(bean);
	}
}
