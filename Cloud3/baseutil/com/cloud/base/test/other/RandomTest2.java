package com.cloud.base.test.other;

import java.util.Random;

public class RandomTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] str = {"买","不买"};
		double d = Math.random();
		int i = (int)(d*str.length);
		System.out.println(str[i]);
	}

}
