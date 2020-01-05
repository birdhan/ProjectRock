var ctx = getRootPath();

$().ready(function() {
    $("#demoForm").validate({ 
    	rules: {//验证规则	        	 
    		name:{
    			required:false,
    			maxlength:30,normalText:true
    		},
    		createTime:{
    			required:false
    		},
    		age:{
    			required:false,
    			isInt:true,normalText:true
    		},
    		depart: {
    			required:false,normalText:true
    		},
    		direction: {
    			required:false,normalText:true
    		}
    	},
    	messages:{//验证消息内容
    		name:{
    			maxlength:"最大长度不能超过30"
    		},
    		createTime:{
    			required:"创建时间不能为空"
    		},
    		age:{
    			isInt:"年龄只能输入大于0的数"
    		},
    		depart: {
    			required:"请选择部门"
    		},
    		direction: {
    			required:"方向不能为空"
    		}
    	}
    })
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
    		{title:'demo数据导入', url: ctx+'/demo/openDemoImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
    );
}

/**
 * 关闭导入窗口
 */
function closeImportWin() {
	if(importWin != null) {
		importWin.close();
	}
}

/**
 * 数据导出
 */
var exportWin;
function exportData() {
	var ids = "";
	var objs = document.getElementsByName("ids");
	for(var i=0; i<objs.length; i++) {
		 if(objs[i].type.toLowerCase() == "checkbox") {
             if(objs[i].checked == true) {
            	 ids += objs[i].value+",";
             }
         }         
	}
	if(ids.Trim() != "") {
		ids = ids.substring(0,ids.length-1);
	}
	
	exportWin = $.ligerDialog.open(
			{title:'demo数据导出', url: ctx+'/demo/openDemoExport.do?ids='+ids, height: 400, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
	);
}

/**
 * 关闭导出窗口
 */
function colseExportWin() {
	if(exportWin != null) {
		exportWin.close();
	}
}

/**
 * 测试http
 */
function testHttpDemo(id) {
	$.ajax({   
		type:"post",     
		url:getRootPath()+"/demo/testHttpDemo.do",           
		dataType:"text",             
		data:"&id="+id,        
		success:function(data){
			if(data != "0") {																					
				alert(data);
			} 
		}
	});
}