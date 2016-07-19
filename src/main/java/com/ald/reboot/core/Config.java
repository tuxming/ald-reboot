package com.ald.reboot.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ald.reboot.tool.Tools;


/**
 * 
 * @author tuxianming
 */
public class Config {
	
	private static Config instance = null;
	
	private JSONObject config = null;
	
	private Config(){
		
		//get file
		String content = Tools.readFileAndRemoveLineComment(Config.class.getResource("/config.json").getFile());		
		//remove comment
//		content = content.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/", "").replaceAll("\\/\\/[^\n]*", "");
		
		//to json
		config = new JSONObject(content);
		
	}
	
	public static JSONObject getConfig(){
		if(instance==null)
			return getInstance().config;
		return instance.config;
	}
	
	public static Config getInstance(){
		if(instance==null)
			instance = new Config();
		return instance;
	}
	
	public static List<Map<String, String>> getLoginData(){
		
		JSONArray jsonArray = getConfig().getJSONArray("login");
		List<Map<String, String>> arrays = new ArrayList<Map<String,String>>();
		for(int i=0; i<jsonArray.length(); i++){
			JSONObject login = jsonArray.getJSONObject(i);
			Map<String, String> item = new HashMap<String, String>();
			item.put(login.getString("name"), login.getString("pwd"));
			arrays.add(item);
		}
		return arrays;
	}
	
	public static Object getObject(String key){
		return getConfig().get(key);
	}
	
	public static String get(String key){
		return getConfig().getString(key);
	}
	
	public static boolean getBool(String key){
		return getConfig().getBoolean(key);
	}
	
	public static int getInt(String key){
		return getConfig().getInt(key);
	}
	
	public static double getDouble(String key){
		return getConfig().getDouble(key);
	}
	
}
