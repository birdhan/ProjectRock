package cn.four.order.service;
import java.util.List;

import cn.four.dish.domain.Dish;
import cn.four.order.dao.OrderDao;
import cn.four.order.domain.Order;
import cn.four.tools.util.CommonUtils;

public class OrderService {
	private OrderDao orderdao=new OrderDao();
	public void addOrder(Order order){
		/**
		 * 补齐数据
		 */
		
		order.setOid(CommonUtils.uuid());
		/**
		 * 添加数据
		 */
		try {
			orderdao.addOrder(order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * 查询订单
 */
	public List<Dish> findByOrder(String uid){
		return orderdao.findByOrder(uid);
	}
}
