var ctx = getRootPath();
/**
 * 编辑某菜单下的权限
 * @param menuId
 */
var menuPrivTree;
function editMenuPriv(menuId,menuName) {	
	menuPrivTree = $.ligerDialog.open(
		{title:menuName, url: ctx+'/menupriv/editMenuPrivByMenuId.do?menuId='+menuId+'&menuName='+menuName, height: 310, width: 470, name:'menuPrivTreeIframe',isResize: true,id:'menuPrivTreeDialog'}
	);
}

/**
 * 关闭导出窗口
 */
function colseMenuPrivTreeWin() {
	if(menuPrivTree != null) {
		menuPrivTree.close();
		goToUrl('${ctx}/menupriv/treeMenuPriv.do');				//关闭时刷新窗口重新显示数据
	}
}