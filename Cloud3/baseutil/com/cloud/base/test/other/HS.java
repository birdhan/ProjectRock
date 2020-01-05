package com.cloud.base.test.other;

import java.util.Date;

import org.mybatis.spring.SqlSessionFactoryBean;

import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.DateHelper;
import com.cloud.base.util.StringUtil;

public class HS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Date d1 = new Date();
//		for(int i=0;i<1000000;i++) {
//			System.out.println(i);
//		}
//		Date d2 = new Date();
//		float f = Float.valueOf((float)((d2.getTime()-d1.getTime())*0.001));
//		System.out.println("耗时："+String.format("%.2f", f));
//		
//		SqlSessionFactoryBean ssfb = (SqlSessionFactoryBean)ApplicationContextHolder.getInstance().getBean("sqlSessionFactory");
		
//		String os = System.getProperty("os.name");
//		System.out.println(os);
//		
//		System.out.println(System.getProperty("java.endorsed.dirs"));
//		
//		System.out.println(StringUtil.str2URLDecoder("%25E8%25BF%259D%25E7%25AB%25A0%25E5%25AE%25A1%25E6%25A0%25B8%25E5%259B%25BE%25E7%2589%2587%255C20140603%255C0929%255C%25E6%259C%25BA%25E5%258F%25B7001%25E8%25BD%25A6%25E9%2581%2593A12014%25E5%25B9%25B406%25E6%259C%258807%25E6%2597%25A517%25E6%2597%25B617%25E5%2588%258600%25E7%25A7%2592R002D1H3T%25E6%25A0%2587%25E5%2587%2586%25E8%25BD%25A6%25E7%2589%258CC%25E9%25BB%2584%25E5%25BA%2595%25E9%25BB%2591%25E5%25AD%2597P%25E9%25BB%2591A77777%25E9%25A9%25B6%25E5%2590%2591%25E4%25B8%259C%25E8%2587%25B3%25E8%25A5%25BF%25E8%25BF%259D%25E7%25AB%25A0%25E9%2597%25AF%25E7%25BA%25A2%25E7%2581%25AF"));
		System.out.println(HS.class.getResource("").getPath());
		System.out.println(StringUtil.str2URLDecoder("%5B0S1%5D%3A"));
		
		String result = "0_";
		System.out.println(result.length());
		System.out.println(result.lastIndexOf("_") == result.length()-1);
		result += "六合一无返回信息";
		System.out.println(result);
	}

}
