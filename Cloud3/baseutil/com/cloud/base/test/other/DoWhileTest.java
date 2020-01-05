package com.cloud.base.test.other;



public class DoWhileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String aa = "99.8";
//		System.out.println(Double.valueOf(aa)-1);
		
//		long finalNum = getTest(2,31,0);
//		System.out.println("finalNum:"+finalNum);
		
		String aa = "99.8";
		String bb = "99.1";
		
		System.out.println(String.format("%.2f", Double.valueOf(aa)-Double.valueOf(bb)));
	}
	
	public static long getTest(long di,long zhi,long curValue) {
		long result = 0;
		if(curValue == 0) {				//表示初始值
			curValue = 1;
		}
		curValue = di*curValue;			//底数X当前值默认从1开始 例：2*1=2 执行一次后当前值就变成了2. 2-> 2X2=4 3-> 4X2=8 ...
		if(zhi == 0) {
			return 1;
		} else {
			if(zhi != 1) {
				curValue = getTest(di,--zhi,curValue);
			}
			result = curValue;	
		}			
		return result;
	}

}
