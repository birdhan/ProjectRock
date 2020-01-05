<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Word</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/zidingyi.css">
	<script type="text/javascript" src='${pageContext.request.contextPath}/js/jquery-2.2.3.min.js'></script>
</head>
<body class="bodybg">
<script type="text/javascript">
function addcontrast(id,wname){
	 var datas={
				wid:id,
				wname:wname	
		}
		$.ajax({
		    type: "POST",
		    dataType: "json",
		    url: "/wordedit/word/addcontrast" ,
		    data: datas,
		    success: function (result) {
		    	alert("添加成功!");
		    },
		    error:function(){
		   	 alert("error");
		    }
		})
	 
}
</script>
<div class="container">
<div class="row">
<div class="col-md-12" style="height: 50px; border-bottom: 2px solid #A52A2A;">
<button class="btn returns" onClick="javascript:history.back(-1);">返回</button>

<b class="rightc" onclick="addcontrast('${findbyid.id }','${findbyid.wname }')">添加查重</b>
</div>
</div>
<div class="row">
<div class="col-md-12 imga">
<img alt="" src="${findbyid.serveraddress }${findbyid.pngurl }">
</div>
</div>
</div>
</body>
</html>