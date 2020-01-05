package com.drugs.tools;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Tools {

	
	// 生成6位int类型
	public int typereplacement() {
		
		Random r = new Random();
		// 如生成的随机位数不足6位则自动加零补充
		DecimalFormat g = new DecimalFormat("1");
		// 返回时间增量+随机数的序列
		String format = String.format("%s%s", System.currentTimeMillis(), g.format(r.nextInt(1)));
		int parseInt = Integer.parseInt(format);
		return parseInt;
		
	}
	
	// 生成时间+随机数主键id
	public int randomdate() {
		  SimpleDateFormat simpleDateFormat;  
		  
	        simpleDateFormat = new SimpleDateFormat("hhmmss");  
	  
	        Date date = new Date();  
	  
	        String str = simpleDateFormat.format(date);  
	  
	        Random random = new Random();  
	  
	        int rannum = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数  
	        
	        String valueOf = String.valueOf(rannum);
	  
	        String aa=str+valueOf;
	        
	        int parseInt = Integer.parseInt(aa);
	        
	        return  parseInt;
		
		
	}
	
}
