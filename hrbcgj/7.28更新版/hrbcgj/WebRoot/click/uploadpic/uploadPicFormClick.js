
/**
 * 表单验证
 */
function validateForm() {
	getEleById("valFormDiv").innerHTML = "";
	var picFile = getEleById("picFile").value;
	if(picFile.length == 0) {
		getEleById("valFormDiv").innerHTML = "请选择上传的文件";
		return false;
	} else {
		var exetendName = picFile.substring(picFile.lastIndexOf(".")+1).toLowerCase();
		if(exetendName != "jpg" && exetendName != "jpeg" && exetendName != "png") {
			getEleById("valFormDiv").innerHTML = "只能上传jpg、jpeg、png三种格式图片文件";
			return false;
		}
	}	
	var oriName = picFile.substring(picFile.lastIndexOf("\\")+1);
	getEleById("oriName").value = oriName;
}