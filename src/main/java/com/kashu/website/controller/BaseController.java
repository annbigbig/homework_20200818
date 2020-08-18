package com.kashu.website.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.json.JSONObject;

public class BaseController {

	public static Log INFO = LogFactory.getLog("info");
	public static Log ERROR = LogFactory.getLog("error");
	
	protected JSONObject reponseBuilder(boolean result,long status,
			String msg){
		JSONObject json =new JSONObject();
		json.put("result", result);
		json.put("status", status);
		json.put("msg", msg);
		return json;
	}
	
	protected JSONObject reponseBuilder(boolean result,long status,
			Object msg){
		JSONObject json =new JSONObject();
		json.put("result", result);
		json.put("status", status);
		json.put("msg", msg);
		return json;
	}
}
