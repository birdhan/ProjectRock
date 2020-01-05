/**
 * 通过id删除记录
 */
function delDepartmentByIdAjax(id) {
	$.ligerDialog.confirm("您确定要删除选择的数据吗？", function (yes) {
		if(yes) {
			window.location.href = getRootPath()+"/department/delDepartmentByIdAjax.do?id="+id;
		}
	});
}

/**
 * 批量删除
 */
function delMoreData() {
	var ids = "";
	var common_del_ids = document.getElementsByName("ids");
	for(var i=0;i<common_del_ids.length;i++) {
		if(common_del_ids[i].checked == true) {
			ids += common_del_ids[i].value + ",";
		}		
	}
	if(ids != "") {
		ids = ids.substring(0, ids.length-1);
		$.ligerDialog.confirm("您确定要删除选择的数据吗？", function (yes) {
			if(yes) {
				window.location.href = getRootPath()+"/department/delDepartmentByIds.do?viewType=tree&ids="+ids
			}
		});
	} else {
		f_alert("请选择要删除的数据","error");
	}
}