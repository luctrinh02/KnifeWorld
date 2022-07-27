package com.luctt.KnifeWorld.utilities;

import java.util.HashMap;

public class getMap {
	public static HashMap<String, Object> getData(String status,Object value){
		HashMap<String, Object> map=new HashMap<>();
		map.put("statusCode", status);
		map.put("data", value);
		return map;
	}
}
