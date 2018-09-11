package cn.han.action;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.han.pojo.Customer_address;
import cn.han.pojo.Customer_order;
import cn.han.pojo.Customer_regist;
import cn.han.pojo.Customer_state;
import cn.han.pojo.Customer_type;
import cn.han.service.AddressService;
import cn.han.service.OrderService;
import cn.han.service.RegisterService;
import cn.han.service.StateService;
import cn.han.service.TypeService;

@Controller
public class ControllerToorder {

	@Autowired
	private RegisterService RegisterService;
	
	@Autowired
	private OrderService OrderService;
	
	@Autowired
	private TypeService TypeService;
	
	@Autowired
	private StateService StateService;
	
	@Autowired
	private AddressService Address;
	
	
	/**
	 * 列表查询所有订单
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderSelect")
	public String orderselect(Model model) {
		
		List<Customer_order> findAll = OrderService.findAll();
		model.addAttribute("orderAll",findAll);
		System.out.println(findAll);
		
		return "buyRecord";
	}
	
	/**
	 * 删除订单
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteorder")
	public String seleteorder(HttpServletRequest request) {
		
		String tiao="redirect:/orderSelect";
		String id = request.getParameter("id");
		
		OrderService.deleteo(id);
		return tiao;
	}
	
	
	/**
	 * 修改订单
	 * @param cus
	 * @return
	 */
	@RequestMapping("/updataorder")
	public String updataorder(Customer_order cus) {
		
		
		
		OrderService.updateo(cus);
		
		
		return "redirect:/orderSelect";
	}
	/**
	 * 查询已付款订单列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectrecord")
	public String selectrecord(Model model) {
		
		
		List<Customer_order> findByPayment = OrderService.findByPayment();
		model.addAttribute("findpay",findByPayment);
		
		return "tradRecord";
	}
	/**
	 * 参数绑定修改页
	 * @param model
	 * @return
	 */
	@RequestMapping("/parameterset")
	public String parameterset(Model model) {
		
		List<Customer_state> findAll = StateService.findAll();
		model.addAttribute("state", findAll);
		
		List<Customer_type> findAll2 = TypeService.findAll();
		model.addAttribute("type",findAll2);
		
		List<Customer_address> findAll3 = Address.findAll();
		model.addAttribute("address", findAll3);
		
		return "parameterSet";
	}
	
	/**
	 * 删除类型
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletetype")
	public String deletetype(HttpServletRequest request) {
		
		String type=request.getParameter("typeid");
		TypeService.deleteone(type);
		
		
		return "redirect:/parameterset";
	}
	
	/**
	 * 添加类型
	 * @param customer_type
	 * @return
	 */
	@RequestMapping("/addtype")
	public String addtype(Customer_type customer_type) {
		String string = UUID.randomUUID().toString();
		customer_type.setId(string);
		
		TypeService.addType(customer_type);
		return "redirect:/parameterset";
	}
	
	
	/**
	 * 删除状态
	 * @return
	 */
	@RequestMapping("/deletestatus")
	public String deletestatus(HttpServletRequest request) {
		String parameter = request.getParameter("id");
		
		StateService.deletes(parameter);
		
		return "redirect:/parameterset";
	}
	
	/**
	 * 添加客户状态
	 * @param customer_state
	 * @return
	 */
	@RequestMapping("/addstatus")
	public String addstatus(Customer_state customer_state) {
		String string = UUID.randomUUID().toString();
		customer_state.setId(string);
		System.out.println(customer_state.getCustomerstate());
		StateService.addone(customer_state);
		
		return "redirect:/parameterset";
	}
	
	
	/**
	 * 删除地址一条
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteaddress")
	public String deleteaddress(HttpServletRequest request) {
		String parameter = request.getParameter("id");
		Address.deleteone(parameter);
		return "redirect:/parameterset";
	}
	
	/**
	 * 添加地址
	 * @param customer_address
	 * @return
	 */
	@RequestMapping("/addaddress")
	public String addaddress(Customer_address customer_address) {
		String string = UUID.randomUUID().toString();
		customer_address.setId(string);
		Address.addaddress(customer_address);
		
		
		
		return "redirect:/parameterset";
	}
	
	
	/**
	 * 修改管理员信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/adminupdate")
	public String adminupdate(HttpServletRequest request,HttpServletResponse response) {
		boolean ress=false;
		Customer_regist findByOne = RegisterService.findByOne();
		String username=request.getParameter("username");
		String pwd=request.getParameter("password");
		String pwd2=request.getParameter("password2");
		if (pwd.equals(findByOne.getPwd())) {
			RegisterService.updateOne(username, pwd2);
			ress=true;
		}
		try {
			response.getWriter().print(ress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
}


















