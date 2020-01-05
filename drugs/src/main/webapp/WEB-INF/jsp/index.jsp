<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>人民大药房</title>
<!--meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="Trim-Fit Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
         Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script>
	addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);

	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!--//meta tags ends here-->
<!--booststrap-->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all">
<!--//booststrap end-->
<!-- font-awesome icons -->
<link
	href="${pageContext.request.contextPath }/css/fontawesome-all.min.css"
	rel="stylesheet" type="text/css" media="all">
<!-- //font-awesome icons -->
<link href="${pageContext.request.contextPath }/css/style.css"
	rel='stylesheet' type='text/css' media="all">
<!--//stylesheets-->
<link href="${pageContext.request.contextPath }/css/gooleapis.css"
	rel="stylesheet">
</head>
<body>




	<!--//banner -->
	<!-- short -->
	<div class="using-border py-3">
		<div class="inner_breadcrumb  ml-4">
			<ul class="short_ls">
				<li><a href="http://1959264.b2b.tfsb.cn/">人民大药房</a> <span>/
						/</span></li>
				<li>附属第一网上药房 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</li>

				<li id="deng">当前用户：</li>
				<li><a
					href="${pageContext.request.contextPath }/consumer/userhome?id=${consumer.id }">
						${consumer.uname } &nbsp;&nbsp;&nbsp;</a></li>
				<li><a
					href="${pageContext.request.contextPath }/consumer/zhuxiao">登录/注销
						&nbsp;&nbsp;&nbsp;</a></li>
				<li><a href="${pageContext.request.contextPath }/consumer/orderdrugs">我的药箱
						&nbsp;&nbsp;&nbsp;</a></li>
				<li><a
					href="${pageContext.request.contextPath }/consumer/upgroup">资质申请
				</a>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</li>

				<!-- 当前系统时间 -->
				<li>
					<div id="show_time">
						<script>
							//这里代码多了几行，但是不会延迟显示，速度比较好，格式可以自定义，是理想的时间显示
							setInterval("fun(show_time)", 1);
							function fun(timeID) {
								var date = new Date(); //创建对象  
								var y = date.getFullYear(); //获取年份  
								var m = date.getMonth() + 1; //获取月份  返回0-11  
								var d = date.getDate(); // 获取日  
								var w = date.getDay(); //获取星期几  返回0-6   (0=星期天) 
								var ww = ' 星期'
										+ '日一二三四五六'.charAt(new Date().getDay());//星期几
								var h = date.getHours(); //时
								var minute = date.getMinutes() //分
								var s = date.getSeconds(); //秒
								var sss = date.getMilliseconds(); //毫秒
								if (m < 10) {
									m = "0" + m;
								}
								if (d < 10) {
									d = "0" + d;
								}
								if (h < 10) {
									h = "0" + h;
								}

								if (minute < 10) {
									minute = "0" + minute;
								}

								if (s < 10) {
									s = "0" + s;
								}

								if (sss < 10) {
									sss = "00" + sss;
								} else if (sss < 100) {
									sss = "0" + sss;
								}

								document.getElementById(timeID.id).innerHTML = y
										+ "-"
										+ m
										+ "-"
										+ d
										+ "   "
										+ h
										+ ":"
										+ minute + ":" + s
										/* + "."
										+ sss */
										+ "  " + ww;
								//document.write(y+"-"+m+"-"+d+"   "+h+":"+minute+":"+s);  
							}
						</script>
					</div>


				</li>
				<!-- 当前系统时间 -->

			</ul>


		</div>

	</div>
	<!-- //short-->
<script type="text/javascript">

var numberone=8;

function ajaxup(){
	var datas={
			numbers:numberone	
	}
	$.ajax({
		 type: "POST",//方法类型
         dataType: "json",//预期服务器返回的数据类型
         url: "/drugs/consumer/ajaxupdate" ,//url
         data: datas,
         success: function (data) {
        	var selectajax = data["selectajax"]; 
            var biaoqian="";
            for(var i=0;i<selectajax.length;i++){
            	biaoqian+='<div class=\"col-lg-3 col-md-6 col-sm-12 w3layouts-service-list text-center gap-1\" style=\"margin-top: 15px; height: 320px;\">';
            	biaoqian+='<div class=\"white-shadow\">';
            	biaoqian+='<div class=\"text-wls-ser-bake\">';
            	biaoqian+='<img src=\"/picture/'+selectajax[i].pictureurl+'\" style=\"height: 100px; width: 100px;\">';
            	biaoqian+='</div>';
            	biaoqian+='<div class=\"ser-inner-info\">';
            	biaoqian+='<h4>'+selectajax[i].dname+'</h4>';
            	biaoqian+='<p style=\"height: 57px;\">'+selectajax[i].jianshu+'</p>';
            	biaoqian+='</div>';
            	biaoqian+='<div class=\"outs-agile-buttn mt-lg-3 mt-2\">';
            	biaoqian+='<a href=\"${pageContext.request.contextPath}/consumer/second?did='+selectajax[i].id+'\">查看详情</a>';
            	biaoqian+='</div>';
            	biaoqian+='</div>';
            	biaoqian+='</div>';
            }
          
           $("#shunbo").append(biaoqian); 
           if(biaoqian==""){
        	   alert("已经是全部内容了！");
           }else{
        	   numberone=numberone+8;
           }
         },
         error:function(){
        	 alert("到底了");
         }
	})
}
</script>
	<!--subscribe-->
	<section class="subscribe py-lg-4 py-md-3 py-sm-3 py-3">
	<div class="container py-lg-5 py-md-5 py-sm-4 py-4">
		<h3 class="title clr text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">网上购药系统</h3>
		<div class="row subscribe-form text-center">
			<div class="col-md-6 email-sub-agile">
				<form action="${pageContext.request.contextPath }/consumer/selectsearch" method="post">
					<div class="form-group ">
						<input type="text" name="search" class="form-control email-sub-agile"
							placeholder="请输入您需要的药品名称">
					</div>
					<div class="text-center">
						<button type="submit" class="btn subscrib-btnn">全网搜索</button>
					</div>
				</form>
			</div>
			<div class="col-md-6">
				<div class="wthree-form-list">
					<h4 style="color: white;">经营宗旨:诚实经营，道德经商，缔造经典，追求完美</h4>
					<h4 style="color: white;">一流的管理，一流的品质，一流的服务，不断制造经典</h4>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!--//subscribe-->


	<!--service-->
	<section class="about-inner py-lg-4 py-md-3 py-sm-3 py-3">
	<div class="container py-lg-5 py-md-4 py-sm-4 py-3">
		<div class="row agile-service-grid pt-lg-4 pt-md-4 pt-3" id="shunbo">
			<c:forEach items="${drugss}" var="drugs">
				<div
					class="col-lg-3 col-md-6 col-sm-12 w3layouts-service-list text-center gap-1"
					style="margin-top: 15px; height: 320px;">
					<div class="white-shadow">
						<div class="text-wls-ser-bake">
							<img src="/picture/${drugs.pictureurl }"
								style="height: 100px; width: 100px;">
						</div>
						<div class="ser-inner-info">
							<h4>${drugs.dname }</h4>
							<p style="height: 57px;">${drugs.jianshu }</p>
						</div>
						<div class="outs-agile-buttn mt-lg-3 mt-2">
							<a
								href="${pageContext.request.contextPath}/consumer/second?did=${drugs.id }">查看详情</a>
						</div>
					</div>
				</div>
			</c:forEach>
			
		</div>
		<div style="cursor: pointer; text-align: center;" onclick="ajaxup()">
	<img src="${pageContext.request.contextPath }/images/xiaoji.gif" width="100px" height="100px" >
	<p>加载更多</p>
	</div>
	</div>
	</section>
	
	
	<!--//service-->
	<footer>
	<p>客户投诉电话:400-650-9988</p>
	</footer>
	<!-- //Footer -->
	<!--js working-->
	<script
		src='${pageContext.request.contextPath }/js/jquery-2.2.3.min.js'></script>
	<!--//js working-->
	<!-- start-smoth-scrolling -->
	<script src="${pageContext.request.contextPath }/js/move-top.js"></script>
	<script src="${pageContext.request.contextPath }/js/easing.js"></script>
	<script>
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event) {
				event.preventDefault();
				$('html,body').animate({
					scrollTop : $(this.hash).offset().top
				}, 900);
			});
		});
	</script>
	<!-- start-smoth-scrolling -->
	<!-- here stars scrolling icon -->
	<script>
		$(document).ready(function() {

			var defaults = {
				containerID : 'toTop', // fading element id
				containerHoverID : 'toTopHover', // fading element hover id
				scrollSpeed : 1200,
				easingType : 'linear'
			};

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<!-- //here ends scrolling icon -->
	<!--bootstrap working-->
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<!-- //bootstrap working-->
</body>

</html>