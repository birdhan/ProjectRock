package cn.han.test;



import java.util.List;

import org.junit.Test;

import cn.han.pojo.Customer_address;
import cn.han.service.AddressService;




public class Test02 {

	@Test
	public void findtest() {
		
		AddressService addressService=new AddressService();
		
		List<Customer_address> findAll;
		try {
			findAll = addressService.findAll();
			System.out.println(findAll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
