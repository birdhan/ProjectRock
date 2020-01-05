package com.cloud.index.util;

import java.awt.Color;
import java.util.Random;

public class IndexUtil {

	/**
	 * 验证码线
	 * @param Low
	 * @param High
	 * @return
	 */
	public static Color interLine(int Low, int High){
        if(Low > 255)
            Low = 255;
        if(High > 255)
            High = 255;
        if(Low < 0)
            Low = 0;
        if(High < 0)
            High = 0;
        int interval = High - Low;
        int r = Low + (int)(Math.random() * interval);
        int g = Low + (int)(Math.random() * interval);
        int b = Low + (int)(Math.random() * interval);
        return new Color(r, g, b);
	}
	
	public static String createCode(){
		String result="";
		Random random = new Random();
		for (int i=0;i<6;i++){
			result+=random.nextInt(10);
		}
		return result;
	}
}
