/**
 * 全选方法
 */
function chooseAllPro(e) {
	var proCheckbox = getElesByName("proCheckbox");
	if(e.checked) {					//表示为选中
		for(var i=0;i<proCheckbox.length;i++) {
			proCheckbox[i].checked = true;
		}
	} else {						//表示为非选中
		for(var i=0;i<proCheckbox.length;i++) {
			proCheckbox[i].checked = false;
		}
	}
}

/**
 * 开始导出数据
 */
function beginExportData() {
	getEleById("infoDiv").innerHTML = "&nbsp;";
	var columns = "";
	var proCheckbox = getElesByName("proCheckbox");
	for(var i=0;i<proCheckbox.length;i++) {
		if(proCheckbox[i].checked) {		//将选中的值拼出来
			columns += proCheckbox[i].value + ",";
		}
	}
	if(columns != "") {
		//过滤-start
		var ids = getEleById("ids").value;
		//代码略，可在此处拼写导出的参数条件，可参考demo
		//过滤-end

		columns = columns.substring(0,columns.length-1);
		window.location.href = "${ctx}/schedulemanager/exportData.do?ids=" + ids + "&columns="+encodeURI(encodeURI(columns));		//可将过滤条件参数拼在此处
		getEleById("infoDiv").innerHTML = "<font color='#009966'>正在为您导出数据，稍后请检查下载的文件。</font>";
		getEleById("exportBtn").disabled = "disabeld";
		getEleById("exportBtn").title = "正在导出数据...";
	} else {
		getEleById("infoDiv").innerHTML = "<font color='red'>请选择要导出的列</font>";
	}
}
