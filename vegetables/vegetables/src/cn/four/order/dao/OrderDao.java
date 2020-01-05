package cn.four.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.four.dish.domain.Dish;
import cn.four.order.domain.Order;
import cn.four.tool.conn.DBConn;

public class OrderDao {
/**
 * 添加订单
 */
	public void addOrder(Order order){
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DBConn.getConnection();
			String sql="insert into t_order(oid,did,uid,status) values(?,?,?,?) ";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, order.getOid());
			ps.setObject(2, order.getDid());
			ps.setObject(3, order.getUid());
			ps.setObject(4, order.getStatus());
			int row=ps.executeUpdate();
			if(row>0){
				System.out.println("添加订单成功");
			}else {
				System.out.println("添加订单失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConn.release(conn, ps, null);
		}
	}
/**
 * 查询订单信息
// */
	public List<Dish> findByOrder(String uid){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<Dish> olist=new ArrayList<Dish>();
		try {
			conn=DBConn.getConnection();
			String sql="select d.animation,d.details,d.did,d.dishname,d.heat,d.picture,d.price,d.title,d.type from t_order o, t_dish d where o.did=d.did and o.uid=?";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, uid);
			rs=ps.executeQuery();
			while(rs.next()){
				Dish dish=new Dish();
				dish.setAnimation(rs.getString("animation"));
				dish.setDetails(rs.getString("details"));
				dish.setDid(rs.getString("did"));
				dish.setDishname(rs.getString("dishname"));
				dish.setHeat(rs.getInt("heat"));
				dish.setPicture(rs.getString("picture"));
				dish.setPrice(rs.getDouble("price"));
				dish.setTitle(rs.getString("title"));
				dish.setType(rs.getString("type"));
				olist.add(dish);
			}
		
			System.out.println(olist);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConn.release(conn, ps, rs);
		}
		return olist;
		
		
		
		
	}
}
