package cn.four.dish.service;

import java.util.List;

import cn.four.dish.dao.DishDao;
import cn.four.dish.domain.Dish;
import cn.four.tools.util.CommonUtils;

public class DishService {
	private DishDao dishdao = new DishDao();

	/**
	 * 添加菜品
	 * 
	 * @param dish
	 */
	public void addDish(Dish dish) {
		/**
		 * 补齐数据
		 */
		dish.setDid(CommonUtils.uuid());
		/**
		 * 添加数据
		 */
		try {
			dishdao.addDish(dish);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询菜品所有信息
	 */
	public List<Dish> findAll(){
		return dishdao.findAll();
	}
	/**
	 * 查询热度亲三名的菜品信息
	 */
	public List<Dish> findByHeat(){
		return dishdao.findByHeat();
	}

	/**
	 * 按类型查询菜品
	 */
	public List<Dish> findByType(String type){
		return dishdao.findByType(type);
		
	}
}
