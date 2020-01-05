<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<script type="text/javascript">
function han(){
	/* document.getElementById("upform").submit(); */
	
	
	 window.parent.location.reload();
	 
}
</script>
<div class="container">
<div class="row">
<div class="col-md-12">
<form action="${pageContext.request.contextPath }/updatekuaixiaoById?id=${shop[0].id}" class="form-group" id="upform" method="post">
<label for="kname">商品名称</label>
<input type="text" id="kname" name="kname"  class="form-control" value="${shop[0].kname}">
<label for="kprice">售价</label>
<input type="text" id="kprice" name="kprice"  class="form-control" value="${shop[0].kprice}">
<label for="kpoint">特点</label>
<input type="text" id="kpoint" name="kpoint"  class="form-control" value="${shop[0].kpoint}">
<label for="kdetails">详情</label>
<input type="text" id="kdetails" name="kdetails"  class="form-control" value="${shop[0].kdetails}">
<label for="kcheck">点击量</label>
<input type="text" id="kcheck" name="kcheck" class="form-control" value="${shop[0].kcheck}">
<br>
<input type="submit" class="form-control btn-success" onclick="han()" value="保存修改">
</form>
</div>
</div>
</div>
</body>
</html>