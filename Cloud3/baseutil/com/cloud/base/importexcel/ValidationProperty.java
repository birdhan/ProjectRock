package com.cloud.base.importexcel;

import java.lang.reflect.Field;

import net.sf.json.JSONObject;

import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.validatepro.ValidatePro;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.RegUtil;
import com.cloud.base.util.StringUtil;

public class ValidationProperty {

	/**
	 * 验证属性
	 * @param field value
	 * @return Object数据对象，0：表示验证标识，1：验证信息
	 * 原理：验证某属性然后返回验证结果信息，将传入的value值通过RegUtil类的验证方法得到验证的结果。
	 * RegUtil类的方法可随便添加，就是比对正则表达式与传入的值做对比匹配。
	 */
	public static String[] validateColVal(Field field , String value) {
		String[] obj = new String[2];
		obj[0] = "";
		obj[1] = "";																			//验证信息初始值
		
		String descPro = field.getAnnotation(PropertyDesc.class).name();						//得到属性上的注解串
		
		if(field.getAnnotation(ValidatePro.class) != null) {
			String desc = field.getAnnotation(ValidatePro.class).validate();
			JSONObject valObj = JSONUtil.object2JSONObject(desc);								//得到object对象，可以通过get(key)得到需要验证的信息值
			
			
			//验证信息规则--开始
			if(valObj.get("required") != null) {												//表示为必填项
				boolean required = Boolean.valueOf((String)valObj.get("required"));
				if(required) {																	
					if(StringUtil.null2String(value.trim()).equals("")) {						
						obj[0] = "false";
						obj[1] += descPro+"不能为空；";
					} 
				} 
			}	
			
			if(valObj.get("maxlength") != null) {												//内容的最大长度
				int maxLength = Integer.valueOf((String)valObj.get("maxlength"));
				boolean flag = RegUtil.isMoreThan(value, maxLength);
				if(!flag) {
					obj[0] = "false";
					obj[1] += descPro+"中的<font color='red'>" + value + "</font>值超过了最大长度" + maxLength + "；";
				};			
			}
			
			if(valObj.get("date") != null) {													//表示是日期类型
				String isDate = (String)valObj.get("date");
				if(isDate.trim().equals("true")) {												
					boolean flag = RegUtil.isDate(value);
					if(!flag) {															
						obj[0] = "false";
						obj[1] += descPro+"中的<font color='red'>" + value + "</font>值不是日期格式；";
					}
				}
			}
			
			if(valObj.get("isInt") != null) {													//表示为大于0整数
				boolean isInt = Boolean.valueOf((String)valObj.get("isInt"));
				if(isInt) {																		
					boolean flag = RegUtil.isInt(value);
					if(!flag) {
						obj[0] = "false";
						obj[1] += descPro+"中的<font color='red'>" + value + "</font>值不是正整数；";
					}
				}
			}
			
			if(valObj.get("email") != null) {													//表示为邮箱
				boolean email = Boolean.valueOf((String)valObj.get("email"));
				if(email) {
					boolean flag = RegUtil.isEamil(value);
					if(!flag) {
						obj[0] = "false";
						obj[1] += descPro+"中的<font color='red'>" + value + "</font>值不是邮箱格式；";
					}
				}
			}
			
			if(valObj.get("validateIP") != null) {												//表示为IP
				boolean validateIP = Boolean.valueOf((String)valObj.get("validateIP"));
				if(validateIP) {
					boolean flag = RegUtil.isIPNoPort(value);
					if(!flag) {
						obj[0] = "false";
						obj[1] += descPro+"中的<font color='red'>" + value + "</font>值不是IP格式；";
					}
				}
			}
			
			//验证信息规则--结束
		}		
		return obj;
	}
	
}
