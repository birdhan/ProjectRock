package cn.han.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.han.dao.CType;
import cn.han.pojo.Customer_type;
@Service
public class TypeService {

	@Resource
	private CType typec;
	/*查询所有数据*/
	public List<Customer_type> findAll(){
		
		return typec.findlist();
		
		
	}
	/*根据id删除一条数据*/
	public void deleteone(String id) {
		typec.delete01(id);
	}
//	添加一条数据
	public void addType(Customer_type customer_type) {
		typec.addone(customer_type);
	}
	
}
