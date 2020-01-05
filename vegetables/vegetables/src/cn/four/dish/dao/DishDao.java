package cn.four.dish.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.four.dish.domain.Dish;
import cn.four.tool.conn.DBConn;

public class DishDao {
	/**
	 * 添加菜品
	 */
	public void addDish(Dish dish) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConn.getConnection();
			String sql = "insert into t_dish(did,dishname,title,price,type,heat,details,picture,animation) values(?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, dish.getDid());
			ps.setObject(2, dish.getDishname());
			ps.setObject(3, dish.getTitle());
			ps.setObject(4, dish.getPrice());
			ps.setObject(5, dish.getType());
			ps.setObject(6, dish.getHeat());
			ps.setObject(7, dish.getDetails());
			ps.setObject(8, dish.getPicture());
			ps.setObject(9, dish.getAnimation());
			int row = ps.executeUpdate();
			if (row > 0) {
				System.out.println("添加菜品成功！");
			} else {
				System.out.println("添加菜品失败！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.release(conn, ps, null);
		}
	}

	/**
	 * 查询菜品
	 */
	public List<Dish> findAll() {
		List<Dish> list = new ArrayList<Dish>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBConn.getConnection();
			String sql = "select * from t_dish";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setDid(rs.getString("did"));
				dish.setDishname(rs.getString("dishname"));
				dish.setTitle(rs.getString("title"));
				dish.setPrice(rs.getDouble("price"));
				dish.setType(rs.getString("type"));
				dish.setHeat(rs.getInt("heat"));
				dish.setDetails(rs.getString("details"));
				dish.setPicture(rs.getString("picture"));
				dish.setAnimation(rs.getString("animation"));
				list.add(dish);

			}
			System.out.println("成功显示菜品");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.release(conn, ps, rs);
		}
		return list;
	}

	/**
	 * 通过菜品热度进行查询
	 */
	public List<Dish> findByHeat() {
		List<Dish> hlist = new ArrayList<Dish>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			String sql = "select * from t_dish order by heat desc limit 3";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setDid(rs.getString("did"));
				dish.setDishname(rs.getString("dishname"));
				dish.setTitle(rs.getString("title"));
				dish.setPrice(rs.getDouble("price"));
				dish.setType(rs.getString("type"));
				dish.setHeat(rs.getInt("heat"));
				dish.setDetails(rs.getString("details"));
				dish.setPicture(rs.getString("picture"));
				dish.setAnimation(rs.getString("animation"));
				hlist.add(dish);
			}
			System.out.println("成功显示排名前三的菜品");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConn.release(conn, ps, rs);
		}
		return hlist;

	}
/**
 * 通过分类进行查询菜品信息
 */
	public List<Dish> findByType(String type){
		List<Dish> tlist=new ArrayList<Dish>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=DBConn.getConnection();
			String sql="select * from t_dish where type=?";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, type);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				Dish dish = new Dish();
				dish.setDid(rs.getString("did"));
				dish.setDishname(rs.getString("dishname"));
				dish.setTitle(rs.getString("title"));
				dish.setPrice(rs.getDouble("price"));
				dish.setType(rs.getString("type"));
				dish.setHeat(rs.getInt("heat"));
				dish.setDetails(rs.getString("details"));
				dish.setPicture(rs.getString("picture"));
				dish.setAnimation(rs.getString("animation"));
				tlist.add(dish);
			}
			System.out.println("成功显示分类菜品");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConn.release(conn, ps, rs);
		}
		return tlist;
		
		
	}
	
}
