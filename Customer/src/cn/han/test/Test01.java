package cn.han.test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String ssString=UUID.randomUUID().toString();
		
		
		System.out.println(ssString);
		System.out.println(UUID.randomUUID());
		
		Date date=new Date();
		
	    long times = date.getTime();//时间戳
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(date);
	    System.out.println(dateString);
	    System.out.println();

	}

}
