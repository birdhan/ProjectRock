package com.cloud.base.test.other;

import java.text.DecimalFormat;

public class ShuXue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a = 789;
		double b = 900;
		System.out.println(a/b);
		DecimalFormat df1 = new DecimalFormat("##");
		System.out.println(df1.format(a/b*100)+"%");
	}

}
