<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理系统</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<!-- 背景 -->
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>




<script type="text/javascript">

window.location.href="page/register.jsp";


	/* $(function(){
	 $('#onmou').mouseover(function(){
	 $('#onmou').animate({left:"+=15px"},100); 
	
	
	
	 })
	 $('#onmou').mousemove(function(){
	
	 $('#onmou').animate({left:"-=30px"},100);
	 $('#onmou').animate({left:"+=30px"},100);
	
	
	 })
	
	
	 }) */

	/* $(function(){
	 $("#onmou").mouseover(function(){
	 $(this).animate({right:"15px"},100);
	 $(this).animate({left:"15px"},100);
	
	 /* 500是动画过渡时间 */

	/*     });
	 }); 
	 /* $(document).ready(function(){
	 $("onmou").hover(function(){
	 $("onmou").css("background-color","yellow");
	 },function(){
	 $("onmou").css("background-color","pink");
	 });
	 }); */

	/* var listone = document.getElementsByClassName('uli'); */

	/* listone.onmouseover = function() {
		alert(listone);
	}
	 */
	function unfold(li) {
		/* $(li).next().after(li); */
		/*  $(li).animate({right:'50px'});
		  $(li).animate({left:'50px'});  */
		$(li).animate({
			left : "+=30px"
		}, 100);
		$(li).css("background", "#0066CC");
		$(li).css("color", "#FFFFFF");
		

	}

	function fold(li) {
		$(li).animate({
			left : "-=60px"
		}, 200);
		$(li).animate({
			left : "+=30px"
		}, 100);
		$(li).css("background", "");
		$(li).css("color", "#333366");
		/* $(li).prev().before(li); */
		/* $(li).animate({left:'50px'}); */
		/* $(li).animate({right:'50px'}); */
	}

	/* $("#onmou").hover(function(){

		$("#onmou").animate({right:'50px'});
		  $("#onmou").animate({left:'50px'}); 

	},function(){

		$(li).animate({right:'50px'});
		  $(li).animate({left:'50px'}); 

	}); */
</script>
</head>
<body>


<!-- 盒子 -->
		<div class="container hezi01">
			<div class="row">
				<div class="col-md-9">
					<div class="title01">客户关系管理系统</div>

				</div>
				<div class="col-md-3">
					<div>客户关系管理系统</div>

				</div>
				
				
				
			</div>

		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<ul class="list-inline">
						<a href="#"><li class="uli listone"
							onmouseover="unfold(this)" onmouseout="fold(this)">客户资料</li></a>
						<a href="#"><li class="uli" onmouseover="unfold(this)"
							onmouseout="fold(this)">客户类型</li></a>
						<a href="#"><li class="uli" onmouseover="unfold(this)"
							onmouseout="fold(this)">客户状态</li></a>
						<a href="#"><li class="uli" onmouseover="unfold(this)"
							onmouseout="fold(this)">购买记录</li></a>
						<a href="#"><li class="uli" onmouseover="unfold(this)"
							onmouseout="fold(this)">合同管理</li></a>
						<a href="#"><li class="uli" onmouseover="unfold(this)"
							onmouseout="fold(this)">地区管理</li></a>
						<!-- 				<li class="uli" onmouseover="unfold(this)" onmouseout="fold(this)" ><a href="#">客户类型</a></li>
					<li class="uli" onmouseover="unfold(this)" onmouseout="fold(this)"><a href="#">客户状态</a></li>
					<li class="uli" onmouseover="unfold(this)" onmouseout="fold(this)"><a href="#">购买记录</a></li>
					<li class="uli" onmouseover="unfold(this)" onmouseout="fold(this)"><a href="#">合同管理</a></li>
					<li class="uli" onmouseover="unfold(this)" onmouseout="fold(this)"><a href="#">地区管理</a></li> -->
					</ul>

				</div>
				<div class="col-md-9">sdf</div>
			</div>
		</div>
	
</body>
</html>