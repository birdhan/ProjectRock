<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>快销品</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<link href="${pageContext.request.contextPath }/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<!-- start details -->
<link rel="stylesheet" type="text/css" href="css/productviewgallery.css"
	media="all" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/cloud-zoom.1.0.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.fancybox.pack.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.fancybox-buttons.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.fancybox-thumbs.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/productviewgallery.js"></script>
<!-- start top_js_button -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/move-top.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1200);
		});
	});
</script>
</head>
<body>
<script type="text/javascript">
function addshop(kid){
	var nums=$("#nums").val();
	
	var user={
			id:kid,
			number:nums
	}
	if(confirm("确认将其加入购物车")){
		$.ajax({
			
	        //几个参数需要注意一下
	            type: "POST",//方法类型
	            dataType: "json",//预期服务器返回的数据类型
	            url: "/Fmcg/addshopping" ,//url
	            data: user,
	            success: function (data) {
	          alert("添加购物车成功");
	            },
	            error : function() {
	                alert("添加失败,请重新登录。");
	            }
	        }); 	
		
	}

	
	
}

</script>
	<!-- start header -->
	<jsp:include page="listheader.jsp"></jsp:include>

	<!-- start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<!-- start content -->
				<div class="single">
					<!-- start span1_of_1 -->
					<div class="left_content">
						<div class="span1_of_1">
							<!-- start product_slider -->
							<div class="product-view">
								<div class="product-essential">
									<div class="product-img-box">
										<div class="more-views" style="float: left;"></div>
										<div class="product-image">
										
										<!-- 图片路径 -->
											<a class="cs-fancybox-thumbs cloud-zoom"
												rel="adjustX:30,adjustY:0,position:'right',tint:'#202020',tintOpacity:0.5,smoothMove:2,showTitle:true,titleOpacity:0.5"
												data-fancybox-group="thumb" href="http://localhost:80/pic/${detailss.kpic }"
												title="Women Shorts" alt="Women Shorts"> <img
												src="http://localhost:80/pic/${detailss.kpic }" alt="Women Shorts"
												title="Women Shorts" height="400px"/> 
											</a>
										</div>
										
									</div>
								</div>
							</div>
							<!-- end product_slider -->
						</div>
						<!-- start span1_of_1 -->
						<div class="span1_of_1_des">
							<div class="desc1">
								<h3> ${detailss.kname }</h3>
								<p>${detailss.kpoint }</p>
								<h5>RMB. ${detailss.kprice }</h5>
								<div class="available">
									<h4>类型：${detailss.ktype }</h4>
									
									<ul>
										<li>购买数量:&nbsp;<input type="text" name="number"
											onkeyup="value=value.replace(/[^\d]/g,'') " value="1" id="nums"></li>

									</ul>
									<div class="btn_form">
									
											<input type="button" value="加入购物车" onclick="addshop('${detailss.id}')"/>
										
									</div>

									<div class="clear"></div>
								</div>
								<div class="share-desc">
									<div class="share">
										<h4>简要描述</h4>
										<div
											style="width: 100%; height: 100px;border: 1px solid #C1C1C1;padding: 5px;">
											 
											
											${detailss.kdetails }
											

										</div>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>
						<div class="clear"></div>

					</div>


				</div>
				<!-- end content -->
			</div>
		</div>
	</div>
<div class="footer_bg1">
<div class="wrap">
	<div class="footer">
		<!-- scroll_top_btn -->
	    <script type="text/javascript">
			$(document).ready(function() {
			
				var defaults = {
		  			containerID: 'toTop', // fading element id
					containerHoverID: 'toTopHover', // fading element hover id
					scrollSpeed: 1200,
					easingType: 'linear' 
		 		};
				
				
				$().UItoTop({ easingType: 'easeOutQuart' });
				
			});
		</script>
		 <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>
		<!--end scroll_top_btn -->
		<div class="copy">
			<p class="link">快销品平台，竭尽全力为您服务</p>
		</div>
		<div class="clear"></div>
	</div>
</div>
</div>
</body>
</html>