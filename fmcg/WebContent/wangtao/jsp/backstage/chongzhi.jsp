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
	

	
	window.parent.location.reload();
	
		
}


</script>
<div class="container"> 
<div class="row">
<div class="col-md-12">
<form action="${pageContext.request.contextPath }/recharge" class="form-group" id="upform" method="post">
<label for="tel">账户余额</label>
<input type="text" id="tel" class="form-control"  value="${list[0].balance}" disabled>
<label for="balance">充值金额</label>
<input type="hidden" name="id" value="${list[0].id }">
<input type="hidden" name="balanceold" value="${list[0].balance }">
<input type="text" id="balance" name="balancenew" class="form-control" onkeyup="value=value.replace(/[^\d]/g,'') ">
<br>

<input type="submit" class="form-control btn-success" onclick="han()" value="确认充值">
</form>
</div>
</div>
</div>
</body>
</html>