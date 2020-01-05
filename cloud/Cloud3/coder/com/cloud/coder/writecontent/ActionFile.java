package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cloud.base.annotation.security.Security;
import com.cloud.base.util.FileUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class ActionFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("package " + coder.getPackageName() + ".action;" + enter + enter);
        
        content.append("import java.io.IOException;"+enter);
        content.append("import java.lang.annotation.Annotation;"+enter);
        content.append("import java.lang.reflect.Field;"+enter);
        content.append("import java.lang.reflect.Method;"+enter);
        content.append("import java.text.SimpleDateFormat;"+enter);
        content.append("import java.util.ArrayList;"+enter);
        content.append("import java.util.List;"+enter);
        content.append("import java.util.Map;"+enter);
        content.append("import java.util.TreeMap;"+enter+enter);
        
        content.append("import javax.annotation.Resource;"+enter);
        content.append("import javax.persistence.Transient;"+enter+enter);
        
        content.append("import net.sf.json.JSONArray;"+enter);
        content.append("import net.sf.json.JSONObject;"+enter+enter);
        
        content.append("import org.apache.commons.validator.GenericValidator;"+enter);
        content.append("import org.springframework.stereotype.Controller;"+enter+enter);
        
        content.append("import com.cloud.base.action.BaseAction;"+enter);
        content.append("import com.cloud.base.annotation.propertydesc.PropertyDesc;"+enter);
        content.append("import com.cloud.base.annotation.security.Security;"+enter);
        content.append("import com.cloud.base.importexcel.ExcelMap2Model;"+enter);
        content.append("import com.cloud.base.util.DBFM;"+enter);
        content.append("import com.cloud.base.util.ExcelUtil;"+enter);
        content.append("import com.cloud.base.util.LoggerUtil;"+enter);
        content.append("import com.cloud.base.util.JSONUtil;"+enter);
        content.append("import com.cloud.base.util.StringUtil;"+enter);
        content.append("import "+coder.getPackageName()+".model."+coder.getModelName()+";"+enter);
        content.append("import "+coder.getPackageName()+".service.I"+coder.getModelName()+"Service;"+enter);
        content.append("import com.opensymphony.xwork2.ModelDriven;"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * @author "+coder.getAuthor()+enter);
        content.append(" * 创建时间："+sdf.format(new Date())+enter);
        content.append(" */"+enter);
        content.append("@SuppressWarnings(\"finally\")"+enter);
        content.append("@Controller"+enter);
        content.append("public class "+coder.getModelName()+"Action extends BaseAction implements ModelDriven<Object>{"+enter+enter);
        
        content.append("	private Long PAGESIZE_CONSTANT = 10l;//显示记录数"+enter+enter);
        
        content.append("	@Resource"+enter);
        content.append("	private I"+coder.getModelName()+"Service "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service;"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 将form转成model"+enter);
        content.append("	 */"+enter);
        content.append("	private "+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+";"+enter);
        content.append("	public "+coder.getModelName()+" getModel() {"+enter);
        content.append("		if("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" == null) {"+enter);
        content.append("			"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" = new "+coder.getModelName()+"();"+enter);
        content.append("		}"+enter);
        content.append("		return "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 添加"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"add\",privName=\"添加\",roleType=\"PRIV\")"+enter);
        content.append("	public String add"+coder.getModelName()+"() throws Exception {"+enter);
        content.append("		return \"edit\";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 查看详细"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"detail\",privName=\"详细\")"+enter);
        content.append("	public String detail"+coder.getModelName()+"() throws Exception {"+enter);
        content.append("		String id = request.getParameter(\"id\");"+enter);
        content.append("		"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.get"+coder.getModelName()+"ById(id);"+enter);
        content.append("		request.setAttribute(\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"\", "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("		return \"detail\";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 编辑"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"edit\",privName=\"编辑\",roleType=\"PRIV\")"+enter);
        content.append("	public String edit"+coder.getModelName()+"() throws Exception {"+enter);
        content.append("		String id = request.getParameter(\"id\");"+enter);
        content.append("		"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.get"+coder.getModelName()+"ById(id);"+enter);
        content.append("		request.setAttribute(\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"\", "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("		return \"edit\";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 删除"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"del\",privName=\"删除\",roleType=\"PRIV\")"+enter);
        content.append("	public String del"+coder.getModelName()+"ById() throws IOException {"+enter);
        content.append("		String id = request.getParameter(\"id\");"+enter);
        content.append("		try {"+enter);
        content.append("			"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".setId(id);"+enter);
        content.append("			"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.del"+coder.getModelName()+"("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("		} catch (Exception e) {"+enter);
        content.append("			String mesg = \"删除数据错误，消息为：\"+e.getMessage();"+enter);
        content.append("			LoggerUtil.error(getClass(), mesg);"+enter);
        content.append("		}"+enter);
        content.append("		return \"redirectSuccess\";"+enter);
        content.append("	}"+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 批量删除"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"delBatch\",privName=\"批量删除\",roleType=\"PRIV\")"+enter);
        content.append("	public String del"+coder.getModelName()+"ByIds() throws IOException {"+enter);
        content.append("		String ids = request.getParameter(\"ids\");"+enter);
        content.append("		try {"+enter);
        content.append("			String[] ids_arr = ids.split(\",\");"+enter);
        content.append("			List idList = new ArrayList();"+enter);
        content.append("			for(String id : ids_arr) {"+enter);
        content.append("				idList.add(id);"+enter);
        content.append("			}"+enter);
        content.append("			"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.del"+coder.getModelName()+"Batch(idList);"+enter);
        content.append("		} catch (Exception e) {"+enter);
        content.append("			String mesg = \"批量删除数据错误，消息为：\"+e.getMessage();"+enter);
        content.append("			LoggerUtil.error(getClass(), mesg);"+enter);
        content.append("		}"+enter);
        content.append("		return \"redirectSuccess\";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 保存"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);   
        content.append("	@Security(privKey=\"save\",privName=\"保存\",roleType=\"PRIV\")"+enter);
        content.append("	public String save"+coder.getModelName()+"() throws Exception {"+enter);
        content.append("		"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.save"+coder.getModelName()+"("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("		return \"redirectSuccess\";														//重定向至列表，避免页面再次刷新弹出页面对话框"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 跳转成功页面"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"success\",privName=\"跳转成功\")"+enter);
        content.append("	public String success() throws Exception {"+enter);
        content.append("		request.setAttribute(\"listUrl\", request.getContextPath() + \"/"+coder.getNameSpace()+"/search"+coder.getModelName()+".do\");"+enter);
        content.append("		return \"success\";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 获取列表中的数据"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"search\",privName=\"列表查询\",roleType=\"PRIV\")"+enter);
        content.append("	public String search"+coder.getModelName()+"() throws Exception {"+enter);
        content.append("		StringBuffer sqlWhere = new StringBuffer(\"\");										//查询条件where"+enter);
        content.append("		String pageIndexName = new org.displaytag.util.ParamEncoder(\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List\").		//"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List为jsp页面中<display:table> 的id值"+enter);
        content.append("				encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);"+enter+enter);
        
        content.append("		//查询过滤-start"+enter);
        content.append("		String isSearch = StringUtil.null2String(request.getParameter(\"isSearch\"));		//查询标志"+enter);
        content.append("		if(isSearch.equals(\"yes\")) {													//表示是查询"+enter);
        for(ProMappingCol pmc : pmcList) {													//遍历所有的属性，查看需要参与查询的属性值
        	if(!pmc.getJspInput().equals("hidden")) {										//hidden的控件是不参与查询的
        		if(pmc.getIsQuery().equals("yes")) {
        			if(pmc.getProType().equals("String") || pmc.getProType().equals("java.lang.String")) {			//表示是string类型		
        				content.append("			if(!StringUtil.null2String("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".get"+StringUtil.replaceFirstStr2UpperCase(pmc.getPro())+"()).equals(\"\")) {"+enter);
        				content.append("				sqlWhere.append(\" and "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"='\" + "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".get"+StringUtil.replaceFirstStr2UpperCase(pmc.getPro())+"() + \"'\");"+enter);
        		        
        		        content.append("			}"+enter);        		        
        			} else if(pmc.getProType().equals("int") || pmc.getProType().equals("java.lang.Integer") || pmc.getProType().equals("Integer") ||
         				   pmc.getProType().equals("float") || pmc.getProType().equals("Float") || pmc.getProType().equals("java.lang.Float") ||
         				   pmc.getProType().equals("double") || pmc.getProType().equals("Double") || pmc.getProType().equals("java.lang.Double")){		//数字类型
        				content.append("				sqlWhere.append(\" and "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"=\" + "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".get"+StringUtil.replaceFirstStr2UpperCase(pmc.getPro())+"());"+enter);
        				
        			} else if(pmc.getProType().equals("java.util.Date")) {
        				
        				String dateFormat = "yyyy-MM-dd HH24:mi:ss";
            			if(pmc.getJspInput().equals("yyyy-MM-dd HH:mm")) {
            				dateFormat = "yyyy-mm-dd hh24:mi";
            			} else if(pmc.getJspInput().equals("yyyy-MM-dd HH")) {
            				dateFormat = "yyyy-mm-dd hh24";
            			} else if(pmc.getJspInput().equals("yyyy-MM-dd")) {
            				dateFormat = "yyyy-mm-dd";
            			}  
            			content.append("			if("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".get"+StringUtil.replaceFirstStr2UpperCase(pmc.getPro())+"() != null) {"+enter);
            			content.append("				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(\""+dateFormat+"\")"+enter);
            			content.append("				sqlWhere.append(\" and "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"=\"+DBFM.TO_DATE()+\"('\"+sdf.format("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".get"+StringUtil.replaceFirstStr2UpperCase(pmc.getPro())+"())+\"','"+dateFormat+"')\");"+enter);
            			content.append("			}"+enter);
        			}
        		}
        	}
        }
        content.append("			request.setAttribute(\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"\", "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("		} else {"+enter);
        content.append("			request.setAttribute(\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"\", null);"+enter);
        content.append("		}"+enter+enter);
        
        content.append("		//查询过滤-end"+enter+enter);
        
        content.append("		//当前索引页"+enter);
        content.append("		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 1 : (Long.parseLong(request.getParameter(pageIndexName))));"+enter);
        content.append("		Map map = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.search"+coder.getModelName()+"(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());"+enter+enter);
        
        content.append("		request.setAttribute(\"list\", map.get(\"result\"));								//显示结果list"+enter);
        content.append("		request.setAttribute(\"pageSize\", PAGESIZE_CONSTANT);							//每页显示的记录数，默认10条"+enter);
        
        content.append("		request.setAttribute(\"resultSize\", map.get(\"total\"));							//所有结果总个数"+enter);
        content.append("		return \"list\";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 弹出导出数据的页面"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"openExport\",privName=\"弹出导出\",roleType=\"PRIV\")"+enter);
        content.append("	public String open"+coder.getModelName()+"Export() throws Exception {"+enter);
        content.append("		String ids = request.getParameter(\"ids\");"+enter);
        content.append("		Map fieldMap = new TreeMap();"+enter);
        content.append("		Field[] fieldArr = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".getClass().getDeclaredFields();							//得到定义的属性"+enter);
        content.append("		for(Field field : fieldArr) {													//field.getName()可得到属性值"+enter);
        content.append("			Annotation annotation = field.getAnnotation(PropertyDesc.class);			//得到属性上的注解串"+enter);
        content.append("			Annotation transientDesc = field.getAnnotation(Transient.class);"+enter);
        content.append("			if(annotation != null && transientDesc == null) {							//表示有描述信息且没有透明注解（透明注解不参与导出功能）"+enter);
        content.append("				String desc = annotation.toString();"+enter);
        content.append("				if(!StringUtil.null2String(desc).equals(\"\")) {"+enter);
        content.append("					desc = desc.substring(desc.indexOf(\"name=\")+5,desc.length()-1);		//得到属性描述"+enter);
        content.append("					fieldMap.put(desc, field.getName());"+enter);
        content.append("				} else {"+enter);
        content.append("					LoggerUtil.info(getClass(), \"没有找到描述值：\"+field.getName());"+enter);
        content.append("				}"+enter);
        content.append("			}"+enter);
        content.append("		}"+enter);
        content.append("		request.setAttribute(\"ids\", ids);"+enter);
        content.append("		request.setAttribute(\"idsSize\", ids.split(\",\").length);"+enter);
        content.append("		request.setAttribute(\"fieldMap\", fieldMap);										//将属性map传给页面"+enter);
        content.append("		return \"export\";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 导出数据"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"exportData\",privName=\"导出数据\",roleType=\"PRIV\")"+enter);
        content.append("	public String exportData() throws Exception {"+enter);
        content.append("		SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");				//日期类型格式转换默认是这个格式"+enter);
        content.append("		String columns = request.getParameter(\"columns\");		//得到导出所选的列"+enter);
        content.append("		StringBuffer where = new StringBuffer(\"\");"+enter+enter);
        
        content.append("		//过滤数据条件开始"+enter);
        content.append("		String ids = request.getParameter(\"ids\");"+enter);
        content.append("		if(!ids.equals(\"\")) {"+enter);
        content.append("			String inIds = \"\";"+enter);
        content.append("			for(String id : ids.split(\",\")) {"+enter);
        content.append("				inIds += \"'\" + id + \"',\";"+enter);
        content.append("			}"+enter);
        content.append("			if(!inIds.equals(\"\")) {"+enter);
        content.append("				inIds = inIds.substring(0, inIds.length()-1);"+enter);
        content.append("				where.append(\" and "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".id in (\" + inIds + \")\");"+enter);
        content.append("			}"+enter);
        content.append("		}"+enter+enter);
        content.append("		where.append(\"\");																	//排序方式 order by user.createTime asc"+enter);
        content.append("		//过滤条件结束"+enter+enter);
        
        content.append("		if(!StringUtil.null2String(columns).equals(\"\")) {"+enter);
        content.append("			JSONArray ja = JSONArray.fromObject(\"[\" + columns + \"]\");					//得到json数组"+enter);
        content.append("			List<"+coder.getModelName()+"> list = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.getAllDataByWhere(where.toString());			//得到要导出的数据"+enter);
        content.append("			List dataList = new ArrayList();												//封装成dataList数据"+enter+enter);
        
        content.append("			Object[] tempTitle = new Object[ja.size()];										//表头"+enter);
        content.append("			for(int i=0;i<ja.size();i++) {"+enter);
        content.append("				JSONObject jo = (JSONObject)ja.get(i);"+enter);
        content.append("				tempTitle[i] = String.valueOf(jo.get(\"name\"));"+enter);
        content.append("			}"+enter);
        content.append("			dataList.add(tempTitle);														//添加表头"+enter+enter);
        
        content.append("			for("+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" : list) {"+enter);
        content.append("				Object[] tempModel = new Object[ja.size()];									//临时的model"+enter);
        content.append("				for(int i=0;i<ja.size();i++) {												//遍历所有选中的列内容"+enter);
        content.append("					JSONObject jo = (JSONObject)ja.get(i);"+enter);
        content.append("					Method m = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".getClass().getMethod(\"get\"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(jo.get(\"value\"))), null);		//得到方法"+enter);
        content.append("					Object obj = m.invoke("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+", null);								//调用方法后的返回值"+enter);
        content.append("					if(obj != null) {"+enter);
        content.append("						if(obj.getClass().getName().equals(\"java.sql.Timestamp\")) {			//类型表示是时间"+enter);
        content.append("							tempModel[i] = sdf.format(sdf.parse(String.valueOf(obj)));"+enter);
        content.append("						} else {"+enter);
        content.append("							tempModel[i] = String.valueOf(obj);									//默认情况都是文本"+enter);
        content.append("						}"+enter);
        content.append("					} else {"+enter);
        content.append("						tempModel[i] = String.valueOf(\"\");"+enter);
        content.append("					}"+enter);
        content.append("				}"+enter);
        content.append("				dataList.add(tempModel);														//添加数据"+enter);
        content.append("			}"+enter);
        content.append("			response.setHeader(\"Content-Disposition\", \"attachment; filename=exportData.xls\");"+enter);
        content.append("			ExcelUtil.WriteExcel(request.getSession().getServletContext().getRealPath(\"/template/commonxls/common.xls\"),0,dataList,response.getOutputStream());"+enter);
        content.append("			System.gc();																		//启动jvm垃圾回收"+enter);
        content.append("		}"+enter);
        content.append("		return null;"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 弹出导入数据的页面"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"openImport\",privName=\"弹出导入\",roleType=\"PRIV\")"+enter);
        content.append("	public String open"+coder.getModelName()+"Import() throws Exception {"+enter);
        content.append("		return \"import\";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 数据导入"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security(privKey=\"importData\",privName=\"数据导入\",roleType=\"PRIV\")"+enter);
        content.append("	public String importData() throws Exception {"+enter);
        content.append("		Map excelMap = (Map)request.getSession().getAttribute(\"excelMap\");					//获取excel文件中的所有数据"+enter);
        content.append("		Object[] obj = ExcelMap2Model.excleMap2Model(excelMap, "+coder.getModelName()+".class);"+enter);
        content.append("		if(obj != null) {"+enter);
        content.append("			boolean flag = (Boolean)obj[0];														//验证标志：true为正确"+enter);
        content.append("			if(flag) {																			//表示flag值为true"+enter);
        content.append("				List<"+coder.getModelName()+"> saveList = (List)obj[1];									//取出保存对象"+enter);
        content.append("				if(saveList.size() != 0) {"+enter);
        content.append("					"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.saveDataBatch(saveList);										//批量保存"+enter);
        content.append("					request.getSession().setAttribute(\"saveListSize\", saveList.size());"+enter);
        content.append("				}"+enter);
        content.append("			} else {																			//表示flag值为false"+enter);
        content.append("				if(!StringUtil.null2String(((String)obj[3])).equals(\"\") && obj[2] == null) {	//表示不是模板文件"+enter);
        content.append("					request.getSession().setAttribute(\"isNotTemplate\", (String)obj[3]);"+enter);
        content.append("				} else {																//表示数据错误"+enter);
        content.append("					List failedList = (List)obj[2];											//错误消息集合"+enter);
        content.append("					if(failedList != null) {"+enter);
        content.append("						request.getSession().setAttribute(\"failedList\",failedList);										//设置导入失败的消息集合"+enter);
        content.append("					}"+enter);
        content.append("				}"+enter);
        content.append("			}"+enter);
        content.append("			request.getSession().setAttribute(\"flag\", flag);"+enter);
        content.append("		}"+enter+enter);
        
        content.append("		request.getSession().setAttribute(\"totalNum\", excelMap.size()-1);				//导入的总记录数"+enter);
        content.append("		request.getSession().setAttribute(\"returnUrl\", request.getContextPath()+\"/"+coder.getNameSpace()+"/search"+coder.getModelName()+".do\");"+enter);
        content.append("		request.getSession().removeAttribute(\"excelMap\");								//移除excelMap数据"+enter);
        content.append("		response.getWriter().print(\"<script language='javascript'>parent.location.href='\" + request.getContextPath() + \"/commonjsp/excelImportResult.jsp'</script>\");		//让父窗口刷新"+enter);
        content.append("		return null;"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 通过JSON串保存数据"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security"+enter);
        content.append("	public String save"+coder.getModelName()+"4Json() throws Exception {"+enter);
        content.append("	 	JSONObject root = new JSONObject();"+enter);
        content.append("	 	int code = 0;																		//默认失败"+enter);
        content.append("	 	String jsonData = \"\";"+enter);
        content.append("	 	try {"+enter);
        //content.append("	 		jsonData = StringUtil.null2String(request.getParameter(\"jsonData\"));"+enter);
        //content.append("	 		"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" = ("+coder.getModelName()+")JSONUtil.json2Object(jsonData, "+coder.getModelName()+".class.getName());"+enter);
        content.append("	 		"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.save"+coder.getModelName()+"("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("	 		code = 1;"+enter);
        content.append("	 		jsonData = JSONUtil.object2JSONObject("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+").toString();"+enter);
        content.append("	 	} catch (Exception e) {"+enter);
        content.append("	 		e.printStackTrace();"+enter);
        content.append("	 		String mess = \"json保存数据方式出错:\"+e.getMessage();"+enter);
        content.append("	 		LoggerUtil.info(getClass(), mess);"+enter);
        content.append("	 		code = 0;"+enter);
        content.append("	 		jsonData = mess;"+enter);
        content.append("	 	} finally {"+enter);
        content.append("	 		root.put(\"code\", code);"+enter);
        content.append("	 		root.put(\"jsonData\", jsonData);"+enter);
        content.append("	 		response.getWriter().print(root.toString());"+enter);
        content.append("	 		return null;"+enter);
        content.append("	 	}"+enter);
        content.append("	 }"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 通过id编辑某数据"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security"+enter);
        content.append("	public String edit"+coder.getModelName()+"4Json() throws Exception {"+enter);
        content.append("	 	JSONObject root = new JSONObject();"+enter);
        content.append("	 	int code = 0;																		//默认失败"+enter);
        content.append("	 	String jsonData = \"\";"+enter);
        content.append("	 	try {"+enter);
        content.append("	 		String id = request.getParameter(\"id\");"+enter);
        content.append("	 		"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.get"+coder.getModelName()+"ById(id);"+enter);
        content.append("	 		if("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" != null) {"+enter);
        content.append("	 			jsonData = JSONUtil.object2JSONObject("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+").toString();"+enter);
        content.append("	 			code = 1;"+enter);
        content.append("	 		} else {"+enter);
        content.append("	 			jsonData = \"没有查询到id值为\"+id+\"的记录\";"+enter);
        content.append("	 			code = 0;"+enter);
        content.append("	 		}"+enter);
        content.append("	 	} catch (Exception e) {"+enter);
        content.append("	 		e.printStackTrace();"+enter);
        content.append("	 		LoggerUtil.info(getClass(), \"json获取某记录方式出错:\"+e.getMessage());"+enter);
        content.append("	 		jsonData = \"json获取某记录方式出错:\"+e.getMessage();"+enter);
        content.append("	 		code = 0;"+enter);
        content.append("	 	} finally {"+enter);
        content.append("	 		root.put(\"code\", code);"+enter);
        content.append("	 		root.put(\"jsonData\", jsonData);"+enter);
        content.append("	 		response.getWriter().print(root.toString());"+enter);
        content.append("	 		return null;"+enter);
        content.append("	 	}"+enter);
        content.append("	 }"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 通过ids批量删除数据"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security"+enter);
        content.append("	public String del"+coder.getModelName()+"ByIds4Json() throws Exception {"+enter);
        content.append("	 	JSONObject root = new JSONObject();"+enter);
        content.append("	 	int code = 0;																		//默认失败"+enter);
        content.append("	 	String jsonData = \"\";"+enter);
        content.append("	 	String ids = request.getParameter(\"ids\");"+enter);
        content.append("	 	try {"+enter);
        content.append("	 		String[] ids_arr = ids.split(\",\");"+enter);
        content.append("	 		List idList = new ArrayList();"+enter);
        content.append("	 		for(String id : ids_arr) {"+enter);
        content.append("	 			idList.add(id);	"+enter);
        content.append("	 		}"+enter);
        content.append("	 		"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.del"+coder.getModelName()+"Batch(idList);												//批量删除"+enter);
        content.append("	 		code = 1;																		//返回代码"+enter);
        content.append("	 		jsonData = \"删除成功\";															//返回信息"+enter);
        content.append("	 	} catch (Exception e) {"+enter);
        content.append("	 		String mesg = \"批量删除数据错误，消息为：\"+e.getMessage();"+enter);
        content.append("	 		code = 99;"+enter);
        content.append("	 		LoggerUtil.error(getClass(), mesg);"+enter);
        content.append("	 		jsonData = mesg;"+enter);
        content.append("	 	} finally {"+enter);
        content.append("	 		root.put(\"code\", code);"+enter);
        content.append("	 		root.put(\"jsonData\", jsonData);"+enter);
        content.append("	 		response.getWriter().print(root.toString());"+enter);
        content.append("	 		return null;"+enter);
        content.append("	 	}"+enter);
        content.append("	 }"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 列表数据查询展现"+enter);
        content.append("	 * @return"+enter);
        content.append("	 * @throws Exception"+enter);
        content.append("	 */"+enter);
        content.append("	@Security"+enter);
        content.append("	public String search"+coder.getModelName()+"4Json() throws Exception {"+enter);
        content.append("	 	JSONObject root = new JSONObject();"+enter);
        content.append("	 	int code = 0;																		//默认失败"+enter);
        content.append("	 	String jsonData = \"\";"+enter);
        content.append("	 	String ids = request.getParameter(\"ids\");"+enter);
        content.append("	 	try {"+enter);
        content.append("	 		StringBuffer sqlWhere = new StringBuffer(\"\");"+enter);
        content.append("	 		String p = request.getParameter(\"p\");											//分页参数页码参数名"+enter+enter);
        
        content.append("	 		//查询过滤-start"+enter);
        content.append("	 		String isSearch = StringUtil.null2String(request.getParameter(\"isSearch\"));		//查询标志"+enter);
        content.append("	 		if(isSearch.equals(\"yes\")) {													//表示是查询"+enter);
        content.append("	 		} else {"+enter);
        content.append("	 		}"+enter);
        content.append("	 		//查询过滤-end"+enter+enter);
        
        content.append("	 		//当前索引页"+enter);
        content.append("	 		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(p) ? 1 : (Long.parseLong(p)));"+enter);
        content.append("	 		Map map = "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Service.search"+coder.getModelName()+"(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());"+enter);
        content.append("	 		List list = (List)map.get(\"result\");"+enter);
        content.append("	 		if(list.size() != 0) {"+enter);
        content.append("	 			jsonData = JSONUtil.list2JSONArray(list).toString();"+enter);
        content.append("	 			code = 1;"+enter);
        content.append("	 		} else {"+enter);
        content.append("	 			jsonData = \"没有查到数据\";"+enter);
        content.append("	 			code = 0;"+enter);
        content.append("	 		}"+enter);
        content.append("	 	} catch (Exception e) {"+enter);
        content.append("			e.printStackTrace();"+enter);
        content.append("	 		LoggerUtil.error(getClass(), \"获取列表数据错误:\" + e.getMessage());"+enter);
        content.append("	 		jsonData = \"获取列表数据错误:\" + e.getMessage();"+enter);
        content.append("	 		code = 99;"+enter);
        content.append("	 	} finally {"+enter);
        content.append("	 		root.put(\"code\", code);"+enter);
        content.append("	 		root.put(\"jsonData\", jsonData);"+enter);
        content.append("	 		response.getWriter().print(root.toString());"+enter);
        content.append("	 		return null;"+enter);
        content.append("	 	}"+enter);
        content.append("	}"+enter+enter);
        
        content.append("}"+enter);
    	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
