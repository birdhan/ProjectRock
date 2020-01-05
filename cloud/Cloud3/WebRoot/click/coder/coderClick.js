$().ready(function() {
	$("#coderForm").validate({
		rules : {// 验证规则
			author : {
				required : true,normalText:true
			},
			pojo : {
				required : true,normalText:true
			},
			packageName : {
				required : true,normalText:true
			},
			nameSpace : {
				required : true,normalText:true
			},
			filePath : {
				required : true,normalText:true
			},
			modelName : {
				required : true,normalText:true
			},
			tableName : {
				required : true,normalText:true
			},
			modelDesc : {
				required : true,normalText:true
			},
			pro1 : {
				required : true,normalText:true
			},
			prodesc1 : {
				required : true,normalText:true
			},
			col1 : {
				required : true,normalText:true
			},
			coldesc1 : {
				required : true,normalText:true
			},
			proType1 : {
				required : true,normalText:true
			},
			colType1 : {
				required : true,normalText:true
			},
			colTypeLength1 : {
				range : [ 36, 36 ]
			}
		},
		messages : {
			colTypeLength1 : "只能输入36"
		}
	})
});

/**
 * 作者默认
 */
function defaultAuthor() {
	getEleById("author").value = "cloudwork";
}

/**
 * 通过包名获取到命名空间
 */
function changeNameSpace(packageName) {
	var nameSpace = ""; // 命名空间
	if (packageName.Trim() != "") {
		if (packageName.lastIndexOf(".") != -1
				&& packageName.lastIndexOf(".") != packageName.length - 1) {
			nameSpace = packageName.substring(packageName.lastIndexOf(".") + 1,
					packageName.length);
		}
	}
	getEleById("nameSpace").value = nameSpace.Trim().toLowerCase();
}

/**
 * 保存或生成代码
 */
function changeURL(e) {
	var index = getEleById("index").value;																	//得到索引
	index = index.split(",");
	for(var i=0;i<index.length;i++) {
		packageValidationStr(index[i]);
	}
	
	if (e.id == "sumbit2") {
		getEleById("coderForm").action = getRootPath() + "/coder/saveCode.do";
	} else {
		getEleById("coderForm").action = getRootPath() + "/coder/createCode.do";
	}
	return true;
}

/**
 * 录入POJO的时候更改实体类名和数据表名，再追加代码生成路径 默认情况下，实体类名与POJO名是一致，表名全是大写字母
 */
function changeModelTable(e) {
	getEleById("modelName").value = e.value;
	getEleById("tableName").value = e.value.toUpperCase();
	getEleById("filePath").value = "E:\\codepath3.0\\" + e.value;
}

/**
 * 将包名转成小写
 */
function packageName2LowerCase(e) {
	if (e.value.Trim() != "") {
		e.value = e.value.Trim().toLowerCase();
	}
}

/**
 * 刷新
 */
function refreshPage() {
	$.ligerDialog.confirm("您确定要重新刷新页面吗？<br/><br/><font color='red'>警告：若点击是，则页面录入的信息全部置空。</font>",
		function(yes) {
			if (yes) {
				window.location.reload();
			}
		}
	);
}

/**
 * 属性映射字段
 */
function pro2col(value, index) {
	getEleById("col" + index).value = value.Trim().toUpperCase();
}

/**
 * 属性描述映射字段注释
 */
function proDesc2colDesc(value, index) {
	getEleById("coldesc" + index).value = value.Trim();
}

/**
 * 录入的属性得到相应的数据库映射的字段，若没有找到对应关系，则返回空
 */
function mappingPro2Col(proType, index) {
	if (proType.Trim() != "") {
		$.ajax({																//先检查是否存在该属性类型，如果存在那么自动匹配数据库字段类型
			type : "post",
			url : getRootPath() + "/coder/checkProType.do",
			dataType : "text",
			data : "proType=" + proType,
			success : function(flag) {
				if(flag == "true") {
					$.ajax({													//自动匹配属性类型
						type : "post",
						url : getRootPath() + "/coder/mappingPro2Col.do",
						dataType : "text",
						data : "proType=" + proType,
						success : function(data) {
							getEleById("colType" + index).value = data;
							getEleById("proTypeDiv"+index).innerHTML = "";
						}
					});
				} else {
					getEleById("proTypeDiv"+index).innerHTML = "<font color='red'>格式不对</font>";
				}				
			}
		});
	}
}

/**
 * 判断是否为字符
 */
function checkIsChar(colType, index) {
	if (colType == "VARCHAR2" || colType == "VARCHAR" || colType == "NVARCHAR" || colType == "CHAR"
			|| colType == "NCHAR") {
		if (colType.indexOf("(") == -1 && colType.indexOf(")") == -1) {
			var colTypeLength = getEleById("colTypeLength" + index).value;
			if (colTypeLength.Trim() != "") {
				colType = colType + "(" + colTypeLength + ")";
				getEleById("colType" + index).value = colType;
			} else {
				getEleById("colType" + index).value = colType + "(50)"; // 默认长度为50
			}
		}
	}
}

/**
 * 录入长度时检查字段类型是否为char
 */
function checkIsChar2(index) {
	var colType = getEleById("colType" + index).value;
	var k1 = colType.indexOf("(");
	var k2 = colType.indexOf(")");
	if (k1 != -1 && k2 != -1) {
		colType = colType.substring(0, k1);
	}
	checkIsChar(colType, index);
}

/**
 * 根据选中jsp控件，自动识别其应该验证的类型
 */
function autoValidation(value, index) {
	if (value.toLowerCase().indexOf("yyyy-mm-dd") != -1) { // 如果是日期控件，那么自动校验应为日期
		var validation = getElesByName("validation" + index); // 验证控件组
		for ( var i = 0; i < validation.length; i++) {
			if (validation[i].value == "date:true") { // 将其选中
				validation[i].checked = true;
				break;
			}
		}
	} else { // 如果没有选中日期控件
		var validation = getElesByName("validation" + index); // 验证控件组
		for ( var i = 0; i < validation.length; i++) {
			if (validation[i].value == "date:true") { // 勾掉
				validation[i].checked = false;
				break;
			}
		}
	}
	packageValidationStr(index);
}

/**
 * 封装验证串 index 行索引
 */
function packageValidationStr(index) {
	var proValidation = "";
	var colValidation = "";
	var validation = getElesByName("validation" + index); // 验证控件组
	for ( var i = 0; i < validation.length; i++) {
		if (validation[i].checked) {
			if (proValidation.Trim() == "") {
				proValidation = validation[i].value;
			} else {
				proValidation += "," + validation[i].value;
			}
			
			var q = validation[i].value.substring(0,validation[i].value.indexOf(":"));
			var h = validation[i].value.substring(validation[i].value.indexOf(":")+1);
			var finalval = q + ":'" + h +"'";
			if (colValidation.Trim() == "") {				
				colValidation = finalval;
			} else {
				colValidation += "," + finalval;
			}
		}
	}
	getEleById("proValidation" + index).value = proValidation;
	getEleById("colValidation" + index).value = colValidation;
}

/**
 * 重置验证串
 */
function restValidation(index) {
	var validation = getElesByName("validation" + index); // 验证控件组
	for ( var i = 0; i < validation.length; i++) {
		validation[i].checked = false;
	}
	getEleById("proValidation" + index).value = "";
	getEleById("colValidation" + index).value = "";
}

/**
 * 添加属性字段
 */
function addProCol() {
	var newIndex = getNewIndex();							//得到最新索引值
	addPro(newIndex);
	addCol(newIndex);
	
}

/**
 * 添加属性信息
 * @param index
 */
function addPro(index) {
	//创建头描述信息--start
	var row = document.createElement("tr");					//创建一行
	var rowId = "pro_titledesc_"+index;						//行id
	row.setAttribute("id", rowId);							//设置行的ID，以便删除该行时使用
	row.style.height = "28px";								// 行的高度
	var prowz = document.createElement("td");				//创建一列
	prowz.innerHTML = "<font style=\"font-size: 15px;font-weight: bold;\">属性"+index+"信息</font>";
	prowz.style.textAlign = "center";						//文字居中
	prowz.style.backgroundColor = "#FFF";					//背景颜色
	prowz.colSpan = 2;										//跨列
	row.appendChild(prowz);
	//创建头描述信息--end
	
	//创建属性index--start
	var row_sx = document.createElement("tr");
	row_sx.setAttribute("id", "pro_sx"+index);				//行id
	var pro_tr_th1 = document.createElement("th");			//属性index：
	pro_tr_th1.innerHTML = "属性"+index+"：";
	row_sx.appendChild(pro_tr_th1);
	var pro_tr_td2 = document.createElement("td");			//对应控件
	pro_tr_td2.innerHTML = "<input type=\"text\" id=\"pro"+index+"\" name=\"pro"+index+"\" onkeyup=\"pro2col(this.value,"+index+")\" value=\"\" autocomplete=\"off\"/>";
	row_sx.appendChild(pro_tr_td2);
	//创建属性index--end
	
	//创建属性描述--start
	var row_sxms = document.createElement("tr");
	row_sxms.setAttribute("id", "pro_sxms"+index);			//行id
	var pro_tr2_th1 = document.createElement("th");			//属性index：
	pro_tr2_th1.innerHTML = "属性描述：";
	row_sxms.appendChild(pro_tr2_th1);
	var pro_tr2_td2 = document.createElement("td");			//对应控件
	pro_tr2_td2.innerHTML = "<input type=\"text\" id=\"prodesc"+index+"\" name=\"prodesc"+index+"\" onkeyup=\"proDesc2colDesc(this.value,"+index+")\" value=\"\" autocomplete=\"off\"/>";
	row_sxms.appendChild(pro_tr2_td2);
	//创建属性描述--end
	
	//创建属性类型--start
	var row_sxlx = document.createElement("tr");
	row_sxlx.setAttribute("id", "pro_sxlx"+index);			//行id
	var pro_tr3_th1 = document.createElement("th");			//属性index：
	pro_tr3_th1.innerHTML = "属性类型：";
	row_sxlx.appendChild(pro_tr3_th1);
	var pro_tr3_td2 = document.createElement("td");			//对应控件
	pro_tr3_td2.innerHTML = "<input type=\"text\" id=\"proType"+index+"\" name=\"proType"+index+"\" onkeyup=\"mappingPro2Col(this.value,"+index+")\" value=\"String\" autocomplete=\"off\"/><div id=\"proTypeDiv"+index+"\" style=\"display: inline;\"></div>";
	row_sxlx.appendChild(pro_tr3_td2);
	//创建属性类型--end
	
	//创建jsp控件--start
	var row_jsp = document.createElement("tr");
	row_jsp.setAttribute("id", "pro_jsp"+index);			//行id
	var pro_tr4_th1 = document.createElement("th");			//属性index：
	pro_tr4_th1.innerHTML = "JSP控件：";
	row_jsp.appendChild(pro_tr4_th1);
	var pro_tr4_td2 = document.createElement("td");			//对应控件
	var jicListJson = getEleById("jicListJson").value;
	jicListJson = eval("("+jicListJson+")");
	var selectInput = "<select id=\"jspInput"+index+"\" name=\"jspInput"+index+"\" style=\"width:210px;\" onchange=\"autoValidation(this.value,"+index+")\">";
	for(var i=0;i<jicListJson.length;i++) {
		selectInput += "<option value='"+jicListJson[i].value+"'>"+jicListJson[i].showValue+"</option>";
	}
	selectInput += "</select>";
	pro_tr4_td2.innerHTML = selectInput;
	row_jsp.appendChild(pro_tr4_td2);
	//创建jsp控件--end
	
	//创建属性验证串--start
	var row_yzc = document.createElement("tr");
	row_yzc.setAttribute("id", "pro_yzc"+index);			//行id
	var pro_tr5_th1 = document.createElement("th");			//属性index：
	pro_tr5_th1.innerHTML = "属性验证串：<br/><font color=\"red\" style=\"font-weight: normal;\">(无须手动录入)</font>";
	row_yzc.appendChild(pro_tr5_th1);
	var pro_tr5_td2 = document.createElement("td");			//对应控件
	pro_tr5_td2.innerHTML = "<textarea rows=\"10\" cols=\"10\" id=\"proValidation"+index+"\" name=\"proValidation"+index+"\"></textarea>";
	row_yzc.appendChild(pro_tr5_td2);
	//创建属性验证串--end
	
	//参与列表显示--start
	var row_listShow = document.createElement("tr");
	row_listShow.setAttribute("id", "pro_listShow"+index);	//行id
	var pro_tr6_th1 = document.createElement("th");			//属性index：
	pro_tr6_th1.innerHTML = "列表显示：";
	row_listShow.appendChild(pro_tr6_th1);
	var pro_tr6_td2 = document.createElement("td");			//对应控件
	pro_tr6_td2.innerHTML = "<input type=\"radio\" name=\"listShow"+index+"\" value=\"yes\" checked=\"checked\"/>是<input type=\"radio\" name=\"listShow"+index+"\" value=\"no\"/>否";
	row_listShow.appendChild(pro_tr6_td2);
	//参与列表显示--end
	
	//列表内容显示长度--start
	var row_maxLength = document.createElement("tr");
	row_maxLength.setAttribute("id", "pro_maxLength"+index);	//行id
	var pro_tr7_th1 = document.createElement("th");			//属性index：
	pro_tr7_th1.innerHTML = "列内容示长度：";
	row_maxLength.appendChild(pro_tr7_th1);
	var pro_tr7_td2 = document.createElement("td");			//对应控件
	pro_tr7_td2.innerHTML = "<input type=\"text\" name=\"maxLength"+index+"\" value=\"0\"/>(0表示任意长度)";
	row_maxLength.appendChild(pro_tr7_td2);
	//列表内容显示长度--end
	
	//参与列表查询--start
	var row_isquery = document.createElement("tr");
	row_isquery.setAttribute("id", "pro_isquery"+index);	//行id
	var pro_tr8_th1 = document.createElement("th");			//属性index：
	pro_tr8_th1.innerHTML = "参与列表查询：";
	row_isquery.appendChild(pro_tr8_th1);
	var pro_tr8_td2 = document.createElement("td");			//对应控件
	pro_tr8_td2.innerHTML = "<input type=\"radio\" name=\"isQuery"+index+"\" value=\"yes\" onclick=\"autoChangeCreateIndex(this.value,"+index+")\"/>是<input type=\"radio\" name=\"isQuery"+index+"\" value=\"no\" checked=\"checked\" onclick=\"autoChangeCreateIndex(this.value,"+index+")\"/>否";
	row_isquery.appendChild(pro_tr8_td2);
	//参与列表查询--end
	
	getEleById("pro_tbody").appendChild(row);				//添加属性头
	getEleById("pro_tbody").appendChild(row_sx);			//添加属性index行
	getEleById("pro_tbody").appendChild(row_sxms);			//属性描述
	getEleById("pro_tbody").appendChild(row_sxlx);			//属性类型
	getEleById("pro_tbody").appendChild(row_jsp);			//JSP控件
	getEleById("pro_tbody").appendChild(row_yzc);			//验证串
	getEleById("pro_tbody").appendChild(row_listShow);		//参与列表显示
	getEleById("pro_tbody").appendChild(row_maxLength);		//列表内容显示长度
	getEleById("pro_tbody").appendChild(row_isquery);		//参与查询
}

/**
 * 添加字段信息
 * @param index
 */
function addCol(index) {
	
	//创建头描述信息--start
	var row = document.createElement("tr");					//创建一行
	var rowId = "col_titledesc_"+index;						//行id
	row.setAttribute("id", rowId);							//设置行的ID，以便删除该行时使用
	row.style.height = "28px";								// 行的高度
	var prowz = document.createElement("td");				//创建一列
	prowz.innerHTML = "<font style=\"font-size: 15px;font-weight: bold;\">字段"+index+"信息</font>";
	prowz.style.textAlign = "center";						//文字居中
	prowz.style.backgroundColor = "#FFF";					//背景颜色
	prowz.colSpan = 2;										//跨列
	row.appendChild(prowz);
	//创建头描述信息--end
	
	//创建字段index--start
	var row_zd = document.createElement("tr");
	row_zd.setAttribute("id", "col_zd"+index);				//行id
	var pro_tr_th1 = document.createElement("th");			//属性index：
	pro_tr_th1.innerHTML = "字段"+index+"：";
	row_zd.appendChild(pro_tr_th1);
	var pro_tr_td2 = document.createElement("td");			//对应控件
	pro_tr_td2.innerHTML = "<input type=\"text\" id=\"col"+index+"\" name=\"col"+index+"\" value=\"\" autocomplete=\"off\"/>";
	row_zd.appendChild(pro_tr_td2);
	//创建字段index--end
	
	//创建字段注释--start
	var col_zdms = document.createElement("tr");
	col_zdms.setAttribute("id", "col_zdms"+index);			//行id
	var col_tr2_th1 = document.createElement("th");			//属性index：
	col_tr2_th1.innerHTML = "字段注释：";
	col_zdms.appendChild(col_tr2_th1);
	var col_tr2_td2 = document.createElement("td");			//对应控件
	col_tr2_td2.innerHTML = "<input type=\"text\" id=\"coldesc"+index+"\" name=\"coldesc"+index+"\" value=\"\" autocomplete=\"off\"/>";
	col_zdms.appendChild(col_tr2_td2);
	//创建字段注释--end
	
	//创建字段类型--start
	var row_zdlx = document.createElement("tr");
	row_zdlx.setAttribute("id", "col_zdlx"+index);			//行id
	var col_tr3_th1 = document.createElement("th");			//属性index：
	col_tr3_th1.innerHTML = "字段类型：";
	row_zdlx.appendChild(col_tr3_th1);
	var col_tr3_td2 = document.createElement("td");			//对应控件
	col_tr3_td2.innerHTML = "<input type=\"text\" id=\"colType"+index+"\" name=\"colType"+index+"\" value=\"VARCHAR2(50)\" onpropertychange=\"checkIsChar(this.value,"+index+")\" autocomplete=\"off\"/>" +
			"&nbsp;&nbsp;长度：<input type=\"text\" id=\"colTypeLength"+index+"\" name=\"colTypeLength"+index+"\" value=\"50\" onkeyup=\"checkIsChar2("+index+")\" style=\"width:40px;\"/>";
	row_zdlx.appendChild(col_tr3_td2);
	//创建字段类型--end
	
	//创建数据验证--start
	var row_sjyz = document.createElement("tr");
	row_sjyz.setAttribute("id", "col_sjyz"+index);			//行id
	var col_tr4_th1 = document.createElement("th");			//属性index：
	col_tr4_th1.innerHTML = "数据验证：<a href=\"javascript:restValidation("+index+")\" style=\"font-weight: normal;\">重置</a>&nbsp;";
	row_sjyz.appendChild(col_tr4_th1);
	var col_tr4_td2 = document.createElement("td");			//对应控件
	var vlListJson = getEleById("vlListJson").value;
	vlListJson = eval("("+vlListJson+")");
	var checkboxInput = "";
	for(var i=0;i<vlListJson.length;i++) {
		checkboxInput += "<input type=\"checkbox\" id=\"validation"+index+"_"+(i+1)+"\" onclick=\"packageValidationStr("+index+")\" name=\"validation"+index+"\" class=\"chx\" value=\""+vlListJson[i].value+"\"/>"+vlListJson[i].name+"&nbsp;&nbsp;&nbsp;";
	}
	
	col_tr4_td2.innerHTML = checkboxInput;
	row_sjyz.appendChild(col_tr4_td2);
	//创建数据验证--end
	
	//创建属性验证串--start
	var row_yzc = document.createElement("tr");
	row_yzc.setAttribute("id", "col_yzc"+index);			//行id
	var col_tr5_th1 = document.createElement("th");			//属性index：
	col_tr5_th1.innerHTML = "字段验证串：<br/><font color=\"red\" style=\"font-weight: normal;\">(无须手动录入)</font>";
	row_yzc.appendChild(col_tr5_th1);
	var col_tr5_td2 = document.createElement("td");			//对应控件
	col_tr5_td2.innerHTML = "<textarea rows=\"10\" cols=\"10\" id=\"colValidation"+index+"\" name=\"colValidation"+index+"\"></textarea>";
	row_yzc.appendChild(col_tr5_td2);
	//创建属性验证串--end
	
	//参与排序--start
	var row_isSort = document.createElement("tr");
	row_isSort.setAttribute("id", "col_isSort"+index);	//行id
	var col_tr7_th1 = document.createElement("th");			//属性index：
	col_tr7_th1.innerHTML = "参与列表排序：";
	row_isSort.appendChild(col_tr7_th1);
	var col_tr7_td2 = document.createElement("td");			//对应控件
	col_tr7_td2.innerHTML = "<input type=\"radio\" name=\"isSort"+index+"\" value=\"yes\" checked=\"checked\"/>是<input type=\"radio\" name=\"isSort"+index+"\" value=\"no\"/>否";
	row_isSort.appendChild(col_tr7_td2);
	//参与排序--end
	
	//参与数据导入--start
	var row_isImport = document.createElement("tr");
	row_isImport.setAttribute("id", "col_isImport"+index);	//行id
	var col_tr8_th1 = document.createElement("th");			//属性index：
	col_tr8_th1.innerHTML = "参与数据导入：";
	row_isImport.appendChild(col_tr8_th1);
	var col_tr8_td2 = document.createElement("td");			//对应控件
	col_tr8_td2.innerHTML = "<input type=\"radio\" name=\"isImport"+index+"\" value=\"yes\" checked=\"checked\"/>是<input type=\"radio\" name=\"isImport"+index+"\" value=\"no\"/>否";
	row_isImport.appendChild(col_tr8_td2);
	//参与数据导入--end
	
	//删除字段属性--start
	var row_delete = document.createElement("tr");
	row_delete.setAttribute("id", "col_delete"+index);			//行id
	var col_tr7_th1 = document.createElement("th");			//属性index：
	col_tr7_th1.innerHTML = "删除字段属性：";
	row_delete.appendChild(col_tr7_th1);
	var col_tr7_td2 = document.createElement("td");			//对应控件
	col_tr7_td2.innerHTML = "<input type=\"button\" value=\"删除字段\" class=\"btn_list\" onclick=\"removeRow("+index+")\"/>&nbsp;&nbsp;<input type=\"button\" value=\"追加字段\" class=\"btn_list\" onclick=\"addProCol()\"/>" +
			"&nbsp;&nbsp;&nbsp;&nbsp;创建索引：<input type=\"radio\" name=\"createIndex"+index+"\" value=\"yes\"/>是 <input type=\"radio\" name=\"createIndex"+index+"\" checked=\"checked\" value=\"no\"/>否";
	row_delete.appendChild(col_tr7_td2);
	//删除字段属性--end
	
	getEleById("col_tbody").appendChild(row);				//添加字段头
	getEleById("col_tbody").appendChild(row_zd);			//添加字段index行
	getEleById("col_tbody").appendChild(col_zdms);			//添加字段注释
	getEleById("col_tbody").appendChild(row_zdlx);			//添加字段类型
	getEleById("col_tbody").appendChild(row_sjyz);			//添加字段验证
	getEleById("col_tbody").appendChild(row_yzc);			//添加字段类型
	getEleById("col_tbody").appendChild(row_isImport);		//参与数据导入
	getEleById("col_tbody").appendChild(row_isSort);		//参与排序
	getEleById("col_tbody").appendChild(row_delete);		//删除字段属性
}

/**
 * 获取最新的索引值
 */
function getNewIndex() {
	var index = getEleById("index").value;	//得到所有索引值
	index = index.substring(index.lastIndexOf(",")+1);
	index = parseInt(index)+1;
	getEleById("index").value += ","+index;
	return index;
}

/**
 * 删除索引
 * @param index
 */
function removeIndex(index) {
	getEleById("index").value = getEleById("index").value.replace(","+index,"");
}

/**
 * 删除属性行
 * @param index
 */
function removeRow(index) {
	
	$.ligerDialog.confirm("您确定要删除属性"+index+"的信息吗?",
		function(yes) {
			if (yes) {
				var pro_tbody = getEleById("pro_tbody");
				var del_pro_titledesc = getEleById("pro_titledesc_"+index);				//待删除的表头行
				pro_tbody.removeChild(del_pro_titledesc);
				var del_pro_sx = getEleById("pro_sx"+index);				
				pro_tbody.removeChild(del_pro_sx);
				var del_pro_sxms = getEleById("pro_sxms"+index);				
				pro_tbody.removeChild(del_pro_sxms);
				var del_pro_sxlx = getEleById("pro_sxlx"+index);				
				pro_tbody.removeChild(del_pro_sxlx);
				var del_pro_jsp = getEleById("pro_jsp"+index);				
				pro_tbody.removeChild(del_pro_jsp);
				var del_pro_yzc = getEleById("pro_yzc"+index);				
				pro_tbody.removeChild(del_pro_yzc);
				var del_pro_showList = getEleById("pro_listShow"+index);				
				pro_tbody.removeChild(del_pro_showList);
				var del_pro_isquery = getEleById("pro_isquery"+index);				
				pro_tbody.removeChild(del_pro_isquery);
				var del_pro_maxLength = getEleById("pro_maxLength"+index);				
				pro_tbody.removeChild(del_pro_maxLength);
				
				var col_tbody = getEleById("col_tbody");
				var del_col_titledesc = getEleById("col_titledesc_"+index);				//待删除的表头行
				col_tbody.removeChild(del_col_titledesc);
				var del_col_zd = getEleById("col_zd"+index);				
				col_tbody.removeChild(del_col_zd);
				var del_col_zdms = getEleById("col_zdms"+index);				
				col_tbody.removeChild(del_col_zdms);
				var del_col_zdlx = getEleById("col_zdlx"+index);				
				col_tbody.removeChild(del_col_zdlx);
				var del_col_sjyz = getEleById("col_sjyz"+index);				
				col_tbody.removeChild(del_col_sjyz);
				var del_col_yzc = getEleById("col_yzc"+index);				
				col_tbody.removeChild(del_col_yzc);
				var del_col_isImport = getEleById("col_isImport"+index);				
				col_tbody.removeChild(del_col_isImport);
				var del_col_delete = getEleById("col_delete"+index);				
				col_tbody.removeChild(del_col_delete);
				var del_col_isSort = getEleById("col_isSort"+index);				
				col_tbody.removeChild(del_col_isSort);
				
				removeIndex(index);
			}
		}
	);	
}

/**
 * 选中是否参与查询时自动关联创建索引
 * @param value
 */
function autoChangeCreateIndex(value,index) {
	var createIndex = getElesByName("createIndex"+index);
	if(value == "yes") {																	//表示选中的是，即参与查询
		createIndex[0].checked = "checked";
	} else {
		createIndex[1].checked = "checked";
	}
}