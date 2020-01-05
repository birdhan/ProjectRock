package com.cloud.base.test.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RadomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i=0;i<10;i++) {
			System.out.println(createRandom());
		}
		
	}	
	
	public static String createRandom() {
		int random = 0;
        int[] red = new int[33];
        for(int i=0;i<33;i++) {
        	red[i] = i+1;
        }
        
        List indexList = new ArrayList();
        List<String> redList = new ArrayList();
        
        while(redList.size() <6) {
        	int index=(int) (Math.random()*red.length);        	
        	random = red[index];
        	if(indexList.size() != 0) {
        		boolean hasFlag = true;
        		for(int i=0;i<indexList.size();i++) {
        			if((Integer)indexList.get(i) == index) {
        				hasFlag = false;
        				break;
        			}
        		}
        		if(hasFlag) {        			
        			indexList.add(index);
        			if(random < 10) {
            			redList.add("0"+random+"");
            		} else {
            			redList.add(random+"");
            		}          			
        		}
        	} else {
        		indexList.add(index);
        		if(random < 10) {
        			redList.add("0"+random+"");
        		} else {
        			redList.add(random+"");
        		}        		
        	}
        }
		Collections.sort(redList,new SortNum());		
		
		int[] blue = new int[16];
		String blueValue = "";
		for(int i=0;i<16;i++) {
        	blue[i] = i+1;
        }
		int index=(int) (Math.random()*blue.length);
		if(blue[index] < 10) {
			blueValue = "0"+blue[index]+"";
		} else {
			blueValue = blue[index]+"";
		}
		return redList + "["+blueValue+"]";
	}
}

class SortNum implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return Integer.parseInt(o1) - Integer.parseInt(o2);
	}	
}