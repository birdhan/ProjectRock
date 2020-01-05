package com.cloud.base.test.other;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map map = new HashMap();
		for(int i=0;i<5;i++) {
			map.put("key"+(i+1), "value"+(i+1));
		}
		
		for(Iterator i = map.keySet().iterator(); i.hasNext();) {
			String key = (String)i.next();
			String value = (String)map.get(key);
			if(key.equals("key3")) {
				i.remove();
				map.remove(key);
			}
			System.out.println("key:"+key);
			System.out.println("value:"+value);
		}
		
		System.out.println("===========================================");
	}

}
