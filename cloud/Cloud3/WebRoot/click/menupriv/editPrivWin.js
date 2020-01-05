var ctx = getRootPath();

/**
 * 编辑某行记录
 * @param id
 */
function editMenuPrivByPrivId(rowIndex) {
	var noTextDiv = getEleById("noTextDiv"+rowIndex);				//权限文本编号div
	var noInputDiv = getEleById("noInputDiv"+rowIndex);				//权限控件编号div
	var nameTextDiv = getEleById("nameTextDiv"+rowIndex);			//权限文本名称div
	var nameInputDiv = getEleById("nameInputDiv"+rowIndex);			//权限控件名称div
		
	if(noInputDiv.style.display == "none") {				//表示控件未显示状态,即查看		
		noTextDiv.style.display = "none";
		nameTextDiv.style.display = "none";
		
		noInputDiv.style.display = "block";
		nameInputDiv.style.display = "block";
	} else {												//表示控件是显示状态，即保存
		var menuId = getEleById("menuId").value;			//得到菜单id，即要将此权限保存在哪个菜单下
		var id = getEleById("id"+rowIndex).value;					//得到id
		var privNo = getEleById("privNo"+rowIndex).value;			//权限编号
		var privName = getEleById("privName"+rowIndex).value;		//权限名称
		var menuName = getEleById("menuName").value;				//菜单名称
		if(menuId.Trim() != "") {
			var validateFlag = validateData(rowIndex);
			if(validateFlag) {
				$.ajax({   											//检验重复值
					type:"post",     
					url:getRootPath()+"/menupriv/checkPrivNoIsExist.do",           
					dataType:"text",             
					data:"privNo="+privNo+"&id="+id+"&linkMenuId="+menuId,        
					success:function(data){
						if(data == "0") {									//表示正常情况												
							$.ajax({   								//保存数据
								type:"post",     
								url:getRootPath()+"/menupriv/savePrivAjax.do",           
								dataType:"text",             
								data:"menuId="+menuId+"&id="+id+"&privNo="+privNo+"&privName="+privName,        
								success:function(data){
									if(data == "1") {
										window.location.href = getRootPath()+"/menupriv/editMenuPrivByMenuId.do?menuId="+menuId+"&menuName="+menuName;
									}
								}
							});
						} else {											//表示非正常情况		
							alert("当前菜单下编号值已经存在");
						}
					}
				});
			}			
		}		
	}
}

/**
 * 验证数据
 */
function validateData(rowIndex) {
	var privNo = getEleById("privNo"+rowIndex).value;			//权限编号
	if(privNo.Trim() == "") {									//如果为空
		alert("编号不能为空");
		getEleById("privNo"+rowIndex).select();
		return false;
	} 
	
	var privName = getEleById("privName"+rowIndex).value;		//权限名称
	if(privName.Trim() == "") {									//如果为空
		alert("名称不能为空");
		getEleById("privName"+rowIndex).select();
		return false;
	}
	return true;
}

/**
 * 删除某权限
 * @param id
 */
function delMenuPrivByPrivId(rowIndex) {
	var id = getEleById("id"+rowIndex).value;
	if(id.Trim() != "") {
		$.ajax({   
			type:"post",     
			url:getRootPath()+"/menupriv/delMenuPrivByPrivId.do",           
			dataType:"text",             
			data:"id="+id,        
			success:function(data){
				if(data == "1") {
					var menuId = getEleById("menuId").value;			//得到菜单id，即要将此权限保存在哪个菜单下
					var menuName = getEleById("menuName").value;
					window.location.href = getRootPath()+"/menupriv/editMenuPrivByMenuId.do?menuId="+menuId+"&menuName="+menuName;
				}
			}
		});
	} else {
		var privBody = getEleById("privBody");
		var delTr = getEleById("tr"+rowIndex);
		privBody.removeChild(delTr);
	}	
}

/**
 * 添加基本权限
 */
function addBasePriv() {
	var menuId = getEleById("menuId").value;						//得到菜单id，即要将此权限保存在哪个菜单下
	$.ajax({   
		type:"post",     
		url:getRootPath()+"/menupriv/addBasePriv.do",           
		dataType:"text",             
		data:"menuId="+menuId,        
		success:function(data){
			if(data == "1") {
				var menuId = getEleById("menuId").value;			//得到菜单id，即要将此权限保存在哪个菜单下
				var menuName = getEleById("menuName").value;
				window.location.href = getRootPath()+"/menupriv/editMenuPrivByMenuId.do?menuId="+menuId+"&menuName="+menuName;
			}
		}
	});
}

function addRow() {
	var privBody = getEleById("privBody");
	var newIndex = privBody.rows.length+1;
	
	var tr = document.createElement("tr");							//创建一行
	tr.setAttribute("id", "tr"+newIndex);							//设置行的ID，以便删除该行时使用
	
	var privNoTd = document.createElement("td");					//创建一列
	privNoTd.style.textAlign = "center";							//文字居中
	var privNoTd_HTML = "<input type=\"hidden\" id=\"id"+newIndex+"\" value=\"\"/><div id=\"noTextDiv"+newIndex+"\" style=\"display: none;\"></div><div id=\"noInputDiv"+newIndex+"\"><input type=\"text\" id=\"privNo"+newIndex+"\" value=\"\" style=\"width:80%;\"/></div>";
	privNoTd.innerHTML = privNoTd_HTML;
	tr.appendChild(privNoTd);
	
	var privNameTd = document.createElement("td");					//创建一列
	privNameTd.style.textAlign = "center";							//文字居中
	var privNameTd_HTML = "<div id=\"nameTextDiv"+newIndex+"\"></div><div id=\"nameInputDiv"+newIndex+"\"><input type=\"text\" id=\"privName"+newIndex+"\" style=\"width:80%;\"/></div>";
	privNameTd.innerHTML = privNameTd_HTML;
	tr.appendChild(privNameTd);
	
	var privEditTd = document.createElement("td");					//创建一列
	privEditTd.style.textAlign = "center";							//文字居中
	var privEditTd_HTML = "<img src=\""+getRootPath()+"/images/cog_edit.png\" title=\"编辑\" style=\"cursor: pointer;\" onclick=\"editMenuPrivByPrivId('"+newIndex+"')\"/>";
	privEditTd.innerHTML = privEditTd_HTML;
	tr.appendChild(privEditTd);
	
	var privDelTd = document.createElement("td");					//创建一列
	privDelTd.style.textAlign = "center";							//文字居中
	var privDelTd_HTML = "<img src=\""+getRootPath()+"/images/delete.png\" title=\"删除\" style=\"cursor: pointer;\" onclick=\"delMenuPrivByPrivId('"+newIndex+"')\"/>";
	privDelTd.innerHTML = privDelTd_HTML;
	tr.appendChild(privDelTd);
	
	privBody.appendChild(tr);
}