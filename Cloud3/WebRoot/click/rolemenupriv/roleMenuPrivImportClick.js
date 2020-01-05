/**
 * 将选择的文件上传
 */
function uploadFile() {
	var importFile = $("#importExcelFileAll").val().Trim();
	if(importFile.length != 0) {																	//表示文件不空
		var extendName = importFile.substring(importFile.indexOf(".")+1);							//得到文件扩展名
		if(extendName.toLowerCase() != "xls") {														//表示上传的不是xls文件
			getEleById("fileDivInfo").innerHTML = "<font color='red'>请上传正确的模板文件</font>";
		} else {
			getEleById("fileDivInfo").innerHTML = "<font color='#009966'>数据上传中，请您耐心等候...</font>";
			getEleById("importForm").submit();
			getEleById("importBtn").disabled = "disabeld";
			getEleById("importBtn").title = "正在导入数据...";
		}
	} else {																						//表示文件为空
		getEleById("fileDivInfo").innerHTML = "<font color='red'>请选择要上传的文件</font>";
	}
}
