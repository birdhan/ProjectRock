package cn.han.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.han.dao.Address;
import cn.han.pojo.Customer_address;
@Service
public class AddressService {

	@Resource
	private Address address;
	/*查询所有数据*/
	public List<Customer_address> findAll(){
		
		return address.findlist();
		
		
	}
	/*根据id删除一条数据*/
	public void deleteone(String id) {
		address.delete01(id);
	}
//	添加一条数据
	public void addaddress(Customer_address customer_address) {
		address.addone(customer_address);
	}
	
	public Integer countAddress() {
		return address.countAddress();
	}
	
}
