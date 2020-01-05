<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form action="${pageContext.request.contextPath }/updateuserByback?id=${list[0].id}" class="form-group" id="upform" method="post">
<label for="tel">电话</label>
<input type="text" id="tel" name="tel" class="form-control" value="${list[0].tel}">
<label for="adress">收货地址</label>
<input type="text" id="address" name="address" class="form-control" value="${list[0].address}">
<br>
<input type="submit" class="form-control btn-success" onclick="han()" value="保存修改">
</form>
</div>
</div>
</div>
</body>
</html>