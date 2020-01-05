package com.cloud.coder.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.cloud.base.action.BaseAction;
import com.cloud.base.annotation.security.Security;
import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.AxisWebServiceUtil;
import com.cloud.base.util.CXFWebServiceUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.callwebservice.util.CallWSUtil;
import com.cloud.coder.model.BaseType;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.JspInputList;
import com.cloud.coder.model.Pro2Col;
import com.cloud.coder.model.Pro2ColList;
import com.cloud.coder.model.ProMappingCol;
import com.cloud.coder.model.ValidationList;
import com.cloud.coder.writefile.CreateFolderFiles;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("finally")
@Controller
public class CoderAction extends BaseAction implements ModelDriven<Object> {

	/**
	 * 将form转成model
	 */
	private Coder coder;
	public Coder getModel() {
		if(coder == null) {
			coder = new Coder();
		}
		return coder;
	}
	
	/**
	 * 跳转到编写页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="toIndex",privName="生成器首页",roleType="PRIV")
	public String toIndex() throws Exception {
		JspInputList jspInputConfig = (JspInputList)ApplicationContextHolder.getInstance().getBean("jspInputConfig");		//得到jsp控件集合
		List jicList = jspInputConfig.getList();
		request.setAttribute("jicList", jicList);
		request.setAttribute("jicListJson", JSONUtil.list2JSONArray(jicList).toString());
		
		ValidationList validationList = (ValidationList)ApplicationContextHolder.getInstance().getBean("validationList");	//表单验证集合
		List vlList = validationList.getList();
		request.setAttribute("vlList", vlList);
		request.setAttribute("vlListJson", JSONUtil.list2JSONArray(vlList).toString());
		
		return "index";
	}
	
	/**
	 * 映射属性与字段
	 * @return
	 * @throws Exception
	 */
	@Security
	public String mappingPro2Col() throws Exception {
		Pro2ColList p2cList = (Pro2ColList)ApplicationContextHolder.getInstance().getBean("p2cList");				//得到映射关系
		String col = "";																							//返回的字段
		List<Pro2Col> list = p2cList.getList();																		//得到映射集合 
		String proType = StringUtil.null2String(request.getParameter("proType"));									//属性类型
		if(!proType.equals("")) {
			for(Pro2Col p2c : list) {
				String[] values_arr = p2c.getPro().replaceAll("，", ",").split(",");
				for(String value : values_arr) {
					if(value.toLowerCase().equals(proType.toLowerCase())) {											//全转小写再做比较
						col = p2c.getCol();
						break;
					}
				}				
			}
		}
		response.getWriter().print(col);
		return null;
	}
	
	/**
	 * 创建代码
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="createCode",privName="创建代码",roleType="PRIV")
	public String createCode() throws Exception {
		String index = coder.getIndex();																			//得到索引 1,2,3,4,5,6.......n
		List<ProMappingCol> pmcList =  packageModel(index);															//获取参数封装对象
		
		Map paramMap = new HashMap();																				//封装参数
		paramMap.put("model", coder);
		paramMap.put("pmcList", pmcList);
		
		String[] result = CreateFolderFiles.createFolder(paramMap);													//创建文件
		if(result[0].equals("0")) {																					//表示成功
			String flPojo = StringUtil.replaceFirstStr2LowerCase(coder.getPojo());
			request.setAttribute("flPojo", flPojo);
			request.setAttribute("coder", coder);
		}
		return "success";
	}
	
	/**
	 * 获取参数封装对象
	 * @param indexStr
	 * @return
	 */
	@Security
	public List<ProMappingCol> packageModel(String indexStr) {
		List<ProMappingCol> pmcList = new ArrayList<ProMappingCol>();
		String[] index_arr = indexStr.split(",");																	//分割成数组
		for(String index : index_arr) {																				//遍历索引数组
			String pro = request.getParameter("pro"+index);															//属性值
			String prodesc = request.getParameter("prodesc"+index);													//属性描述
			String proType = request.getParameter("proType"+index);													//属性类型
			String jspInput = request.getParameter("jspInput"+index);												//jsp控件
			String proValidation = request.getParameter("proValidation"+index);										//jsp验证串
			String isQuery = request.getParameter("isQuery"+index);													//是否参与查询
			String isImport = request.getParameter("isImport"+index);												//是否参与导入
			String listShow = request.getParameter("listShow"+index);												//列表显示
			String isSort = request.getParameter("isSort"+index);													//是否排序
			String maxLength = request.getParameter("maxLength"+index);												//最大长度
			
			String col = request.getParameter("col"+index);															//字段值
			String coldesc = request.getParameter("coldesc"+index);													//字段描述
			String colType = request.getParameter("colType"+index);													//字段类型
			String colTypeLength = request.getParameter("colTypeLength"+index);										//字段长度
			String colValidation = request.getParameter("colValidation"+index);										//字段验证串
			String createIndex = request.getParameter("createIndex"+index);											//创建索引
			
			ProMappingCol pmc = new ProMappingCol();																//创建映射对象
			pmc.setPro(pro);
			pmc.setProdesc(prodesc);
			pmc.setProType(proType);
			pmc.setJspInput(jspInput);
			pmc.setProValidation(proValidation);
			pmc.setIsQuery(isQuery);
			pmc.setIsImport(isImport);
			pmc.setListShow(listShow);
			pmc.setMaxLength(maxLength);
			
			pmc.setIsSort(isSort);
			pmc.setCol(col);
			pmc.setColdesc(coldesc);
			pmc.setColType(colType);
			pmc.setColTypeLength(colTypeLength);
			pmc.setColValidation(colValidation);
			pmc.setCreateIndex(createIndex);
			
			pmcList.add(pmc);																						//将对象添加到集合中去
		}
		return pmcList;
	}
	
	/**
	 * 保存代码
	 * @return
	 * @throws Exception
	 */
	@Security
	public String saveCode() throws Exception {
		System.out.println("保存代码");
		return "success";
	}
	
	/**
	 * 检查录入的属性类型是否正确
	 * @return
	 * @throws Exception
	 */
	@Security
	public String checkProType() throws Exception {
		String proType = request.getParameter("proType");
		BaseType modelType = (BaseType)ApplicationContextHolder.getInstance().getBean("modelType");
		Map map = modelType.getTypeMap();
		boolean flag = map.containsKey(proType);																	//检查配置的map中是否存在配置的model属性类型
		response.getWriter().print(flag);
		return null;
	}
	
}
