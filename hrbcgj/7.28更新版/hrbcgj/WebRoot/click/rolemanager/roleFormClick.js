$().ready(function() {
	$("#roleForm").validate({
		rules: {//验证规则
			name:{required:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			name:{required:'名称不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 编辑某菜单下的权限
 * @param menuId
 */
var menuPrivTreeCheckbox;
function openMenuPrivTreeCheckbox() {	
	var userId = getEleById("userId").value.Trim();
	if(userId == "") {
		f_alert("亲！请您先选择人员，只有选择人员后才可以进一步对权限进行编辑。如有不便之处，还请谅解，谢谢！","error");
	} else {
		var id = getEleById("id").value.Trim();									//当前角色id
		menuPrivTreeCheckbox = $.ligerDialog.open(
			{title:"菜单权限", url: getRootPath()+'/menupriv/treeMenuPrivWithCheckbox.do?roleId='+id, height: document.documentElement.clientHeight, width: document.documentElement.clientWidth, name:'menuPrivTreeCheckboxIframe',isResize: false,isDrag:false,id:'privTreeCheckboxDialog'}
		);
	}	
}

/**
 * 关闭导出窗口
 */
function colseMenuPrivTreeCheckboxWin() {
	var id = getEleById("id").value;
	if(menuPrivTreeCheckbox != null) {
		menuPrivTreeCheckbox.close();
		goToUrl(getRootPath()+'/rolemanager/editRole.do?id='+id);				//关闭时刷新窗口重新显示数据
	}
}