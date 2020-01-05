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
<form  class="form-group" id="upform" >
<label for="kname">商品名称</label>
<span id="kname">${list[0].kname}</span><br>
<label for="kprice">数量</label>
<span id="kprice">${list[0].snumber}</span><br>
<label for="kpoint">单价</label>
<span id="kpoint">${list[0].kpic}</span><br>
<label for="kdetails">总价</label>
<span id="kdetails">${list[0].spay}</span><br>
<label for="kcheck">订单状态</label>
<span id="kcheck">${list[0].status}</span><br>
<label for="kcheck">日期</label>
<span id="kcheck">${list[0].sdate}</span><br>
<label for="kcheck">订单类型</label>
<span id="kcheck">${list[0].ktype}</span><br>
<label for="kcheck">用户名</label>
<span id="kcheck">${list[0].uname}</span><br>
<label for="kcheck">用户电话</label>
<span id="kcheck">${list[0].utel}</span><br>
<label for="kcheck">用户地址</label>
<span id="">${list[0].uaddress}</span>
<br>

</form>
</div>
</div>
</div>
</body>
</html>