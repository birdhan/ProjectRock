package com.cloud.menupriv.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cloud.menupriv.model.MenuPriv;

/**
 * mybatis接口层，每个方法就是其相应的sqlmap中的id对应的sql语句。
 * 在此接口中，有两种写法：1.映射其sqlmap写法;2.注解写法，sqlmap的映射文件中不需要有此方法的id映射。
 * 其映射文件在该模块的config包下的User_sqlmap.xml
 * @author cloudwork
 *
 */
public interface IMenuPrivDaoMybatis {

	public List selectAllMenuPriv();

	@Select("SELECT * FROM SYSTEM_MENU_PRIV WHERE ID = #{id}")
	public MenuPriv getMenuPrivById(@Param("id") String id);

}
