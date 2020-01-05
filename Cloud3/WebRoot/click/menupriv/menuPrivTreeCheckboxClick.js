var ctx = getRootPath();

/**
 * 选择某行的记录
 * @param index
 */
function choosePrivNo(e,index) {
	var priv_arr = getElesByName("priv"+index);										//得到该行下的所有checkbox控件
	for(var i=0;i<priv_arr.length;i++) {
		if(e.checked) {
			priv_arr[i].checked = "checked";
		} else {
			priv_arr[i].checked = "";
		}		
	}
}

/**
 * 全选方法
 */
function chooseAll() {
	var chooseAllBtn = getEleById("chooseAllBtn");
	var allInput = document.getElementById("treeTable").getElementsByTagName("input");
	if(chooseAllBtn.value == "全选") {
		for(var i=0;i<allInput.length;i++) {
			if(allInput[i].type == "checkbox") {
				allInput[i].checked = "checked";
			}
		}
		chooseAllBtn.value = "反选";
	} else {
		for(var i=0;i<allInput.length;i++) {
			if(allInput[i].type == "checkbox") {
				allInput[i].checked = "";
			}
		}
		chooseAllBtn.value = "全选";
	}
}

/**
 * 选择菜单权限chk时判断其菜单idchk
 * @param name：每个权限chk的name值是其对应菜单chk的id值
 */
function chooseParentChk(name) {
	getEleById(name).checked = "";
	var privChks = getElesByName(name);												//得到该名字的所有chk
	var flag = false;
	for(var i=0;i<privChks.length;i++) {
		if(privChks[i].checked) {
			flag = true;
			break;
		}
	}
	if(flag) {																		//如果flag为true，那么
		getEleById(name).checked = "checked";
	} else {
		getEleById(name).checked = "";
	}
}

/**
 * 保存角色权限关系
 */
function saveRolePriv() {
	
	var jsonData = new Array();
	var menu_arr = getElesByName("menu");											//得到菜单的chk
	
	var checNum = 0;
	for(var i=0;i<menu_arr.length;i++) {
		if(menu_arr[i].checked) {													//如果菜单的chk选中的话
			var rolePriv = {};
			
			var menuId = menu_arr[i].value;											//得到其菜单value值，既菜单的id
			var privNo = "";
			
			var menuName = "";														//菜单名字
			for(var m=0;m<menuJson.length;m++) {
				if(menuJson[m].id == menuId) {
					menuName = menuJson[m].menuName;
					break;
				}
			}
			
			var privName = "";														//权限名称
			
			var id = menu_arr[i].id;												//菜单chk的id值就是权限chk的name值
			var priv_arr = getElesByName(id);										//再获取当前name下的所有权限值
			for(var j=0;j<priv_arr.length;j++) {									//遍历权限chk
				if(priv_arr[j].checked && priv_arr[j].value != menu_arr[i].value) {
					privNo += priv_arr[j].value+",";								//拼写权限串
					
					for(var n=0;n<menuPrivJson.length;n++) {						//拼写权限名称
						if(menuPrivJson[n].linkMenuId == menuId && menuPrivJson[n].privNo == priv_arr[j].value) {	//当前菜单id和json串相当，并且选择的权限编号和json中相等才取出其名称。
							privName += menuPrivJson[n].privName + ",";
							break;
						}
					}
				}
			}
			
			if(privNo != "") {														//将最后一个逗号截掉
				privNo = privNo.substring(0,privNo.length-1);
				privName = privName.substring(0,privName.length-1);
			}
			
			rolePriv.menuId = menuId;
			rolePriv.privNo = privNo;
			rolePriv.menuName = menuName;
			rolePriv.privName = privName;
			
			jsonData.push(rolePriv);
			
			checNum++;
		}		
	}
	
	if(checNum == 0) {
		f_alert("请至少选择一个菜单权限","error");
	} else {
		var jsonDataStr = JSON.stringify(jsonData);										//将json转成string形式
		var roleId = parent.document.getElementById("id").value;						//需要关联角色id
		if(roleId != "") {
			$.ajax({   											
				type:"post",     
				url:getRootPath()+"/rolemenupriv/saveRolePriv.do",           
				dataType:"text",             
				data:"roleId="+roleId+"&jsonData="+jsonDataStr,        
				success:function(data){
					if(data == "0") {													//表示正确，将关系显示在父窗口页面中
						parent.colseMenuPrivTreeCheckboxWin();
					}
				}
			});
		}	
	}	
}