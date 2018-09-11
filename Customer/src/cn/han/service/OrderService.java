package cn.han.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.han.dao.Order;
import cn.han.pojo.Customer_order;
@Service
public class OrderService {

	@Autowired
	private Order orders;
	/*查询所有*/
	public List<Customer_order> findAll(){
		
		return orders.findAll();
	}
	/*条件查询*/
	public List<Customer_order> findBySome(Customer_order cuss){
		
		return orders.findBySome(cuss);
	}
	/*查询已付款*/
	public List<Customer_order> findByPayment(){
		return orders.findByPayment();
	}
	
	/*删除*/
	public void deleteo(String id) {
		
		orders.delete(id);
	}
	/*修改订单信息*/
	public void updateo(Customer_order cus) {
		orders.updata(cus);
		
	}
	/*新建订单*/
	public void inserto(Customer_order cusos) {
		orders.addOrder(cusos);
		
	}
	
	/**
	 * 已付款
	 * @return
	 */
	public Integer count1() {
		return orders.paycount1();
	}
	
	/**
	 * 未付款
	 * @return
	 */
	
	public Integer count2() {
		return orders.paycount2();
	}
}
