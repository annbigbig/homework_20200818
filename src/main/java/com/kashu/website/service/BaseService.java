package com.kashu.website.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kashu.website.model.Employee;
import com.kashu.website.utils.StringUtil;

public class BaseService {
	public static Log INFO = LogFactory.getLog("info");
	public static Log ERROR = LogFactory.getLog("error");
	
	protected JSONObject reponseBuilder(boolean result,long status,
			Object obj){
		JSONObject json =new JSONObject();
		json.put("result", result);
		json.put("status", status);
		//不要直接json.put("object",obj); 日期格式會變成iso8601格式而不是預期的yyyy-MM-dd讓你很困擾
		json.put("object", StringUtil.beanToJSONString(obj));
		INFO.info("JSONObject :");
		/*
		if(obj instanceof Employee) {
			INFO.info("--------------------------------------");
			Employee e = (Employee) obj; 
			INFO.info("取出的birthday屬性值" + e.getBirthday());
			INFO.info("--------------------------------------");
			// 可以這樣解，但很Low，而且只能給getOne使用，getAll或是search都不能用
			Map<String,Object> map = new HashMap<>();
			map.put("id", e.getId());
			map.put("name", e.getName());
			map.put("address", e.getAddress());
			SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
			map.put("birthday", dmyFormat.format(e.getBirthday()));
			map.put("luckyNumber", e.getLuckyNumber());
			map.put("online", e.isOnline());
			json.put("object", map);
			
		} else if(obj instanceof List<?>) {
			// should do something here for resolving multiple Employees
		}
		*/
		INFO.info(json.toString());
		return json;
	}
	
	protected JSONObject reponseBuilder(boolean result,long status,
			String msg){
		JSONObject json =new JSONObject();
		json.put("result", result);
		json.put("status", status);
		json.put("msg", msg);
		INFO.info("JSONObject :");
		INFO.info(json.toString());
		return json;
	}
	
	protected JSONObject reponseBuilder(boolean result,long status,
			Map<String,String> messages){
		JSONObject json =new JSONObject();
		json.put("result", result);
		json.put("status", status);
		if(messages!=null && !messages.isEmpty()){
			for(String key:messages.keySet()){
				json.put(key, messages.get(key));
			}
		}
		INFO.info("JSONObject :");
		INFO.info(json.toString());
		return json;
	}
	
}
