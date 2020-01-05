package com.cloud.base.test.other;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalFormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();
		df.setMaximumFractionDigits(2);
		System.out.println(df.format(12.3456789));

	}

}
