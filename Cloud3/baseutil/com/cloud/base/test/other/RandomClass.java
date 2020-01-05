package com.cloud.base.test.other;

public class RandomClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] personArr = {"张三","李四","王六","陈六"};
		int index = (int)(Math.random()*personArr.length);
		System.out.println(personArr[index]);
	}

}
