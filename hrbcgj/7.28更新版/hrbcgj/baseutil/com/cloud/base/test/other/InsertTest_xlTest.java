package com.cloud.base.test.other;

import java.util.UUID;

public class InsertTest_xlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=0;i<1000;i++) {
			String newId = UUID.randomUUID().toString();
			
			String sql = "insert into test_xl values('"+newId+"','4124',1,sysdate);";
			System.out.println(sql);
		}
	}

}
