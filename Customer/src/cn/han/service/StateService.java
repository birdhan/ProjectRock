package cn.han.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.han.dao.CState;
import cn.han.pojo.Customer_state;

@Service
public class StateService {

	@Resource
	private CState cstate;
	
	
	public List<Customer_state> findAll(){
		
		return cstate.findlist();
	}
	
	public void deletes(String id) {
		cstate.delete01(id);
	}
	
	public void addone(Customer_state customer_state) {
		
		cstate.addone(customer_state);
	}
}
