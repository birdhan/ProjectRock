$().ready(function() {
	$("#menuPrivForm").validate({
		rules: {//验证规则
			privNo:{required:true,maxlength:10,normalText:true},
			privName:{required:true,maxlength:50,normalText:true},
			linkMenuId:{required:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			privNo:{required:'权限编号不能为空',maxlength:'最大长度不能超过10'},
			privName:{required:'权限名称不能为空',maxlength:'最大长度不能超过50'},
			linkMenuId:{required:'关联菜单不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 自动识别基本权限值，并检查当前值是否重复
 * @param value
 */
function autoNameAndCheck() {	
	var privNo = getEleById("privNo").value;
	var privName = getEleById("privName");	
	switch (privNo) {
		case "add":	
			privName.value = "增加";
			break;			
		case "del":
			privName.value = "删除";
			break;			
		case "update":
			privName.value = "修改";
			break;			
		case "view":
			privName.value = "查看";
			break;			
		default:
			if(privName.value == "") {
				privName.value = "";
			}			
			break;
	}
	
	var linkMenuId = getEleById("linkMenuId").value;				//关联的菜单ID
	if(privNo.Trim() != "" && linkMenuId.Trim() != "") {
		getEleById("privNoDiv").innerHTML = "";
		var id = getEleById("id").value;
		$.ajax({   
			type:"post",     
			url:getRootPath()+"/menupriv/checkPrivNoIsExist.do",           
			dataType:"text",             
			data:"privNo="+privNo+"&id="+id+"&linkMenuId="+linkMenuId,        
			success:function(data){
				if(data == "0") {									//表示正常情况												
					getEleById("privNoDiv").innerHTML = "";
				} else {											//表示非正常情况		
					getEleById("privNoDiv").innerHTML = "<font color='red'>当前菜单下编号值已经存在</font>";
				}
			}
		});
	}
}

function autoPrivNo(key) {
	var privNo = getEleById("privNo");
	var privName = getEleById("privName");	
	switch (key) {
		case "add":	
			privName.value = "增加";
			break;			
		case "del":
			privName.value = "删除";
			break;			
		case "update":
			privName.value = "修改";
			break;			
		case "view":
			privName.value = "查看";
			break;			
		default:
			if(privName.value == "") {
				privName.value = "";
			}			
			break;
	}
	privNo.value = key;
}

/**
 * 验证表单
 */
function validateForm() {
	var privNoDiv = getEleById("privNoDiv").innerHTML.Trim();
	if(privNoDiv != "") {
		return false;
	} else {
		return true;
	}
}