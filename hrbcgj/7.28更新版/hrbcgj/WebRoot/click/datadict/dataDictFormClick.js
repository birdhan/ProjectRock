var ctx = getRootPath();
$().ready(function() {
	$("#dataDictForm").validate({
		rules: {//验证规则
			dictValue:{required:true,maxlength:100,normalText:true},
			dictLabel:{required:true,maxlength:200,normalText:true},
			dictType:{required:true,maxlength:50,normalText:true},
			moduleName:{required:true,maxlength:50,normalText:true},
			property:{required:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			dictValue:{required:'字典值不能为空',maxlength:'最大长度不能超过100'},
			dictLabel:{required:'字典显示值不能为空',maxlength:'最大长度不能超过200'},
			dictType:{required:'字典控件类型不能为空',maxlength:'最大长度不能超过50'},
			moduleName:{required:'模块名称不能为空',maxlength:'最大长度不能超过50'},
			property:{required:'控件属性不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 检查重复数据
 */
function checkRepeatData() {
	getEleById("infodiv").innerHTML = "";
	var dictValue = getEleById("dictValue").value;
	var dictType = getEleById("dictType").value;
	var moduleName = getEleById("moduleName").value;
	var property = getEleById("property").value;
	if(dictValue.Trim() !="" && dictType.Trim() !="" && moduleName.Trim() !="" && property.Trim() !="") {		//四个都不为空的时候
		$.ajax({   
			type:"post",     
			url:ctx+"/datadict/checkRepeatData.do",           
			dataType:"text",             
			data:"dictValue="+dictValue+"&dictType="+dictType+"&moduleName="+moduleName+"&property="+property,        
			success:function(data){
				var id = getEleById("id").value;
				if(data == id || data =="") {			//表示有值
					getEleById("infodiv").innerHTML = "";    					
				} else {
					getEleById("infodiv").innerHTML = "<font color='red'>当前模块下该控件类型中已经存在字典值</font>";
				}
			}
		});
	}	    	
}

function validationForm() {
	var infodiv = getEleById("infodiv").innerHTML.Trim();
	if(infodiv != "") {
		return false;
	} else {
		return true;
	}
}