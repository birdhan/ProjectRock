<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>快销品</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<link href="${pageContext.request.contextPath }/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<!-- start gallery_sale -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.easing.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.mixitup.min.js"></script>
<script type="text/javascript">
	$(function() {

		var filterList = {

			init : function() {

				// MixItUp plugin
				// http://mixitup.io
				$('#portfoliolist').mixitup({
					targetSelector : '.portfolio',
					filterSelector : '.filter',
					effects : [ 'fade' ],
					easing : 'snap',
					// call the hover effect
					onMixEnd : filterList.hoverEffect()
				});

			},

			hoverEffect : function() {

				// Simple parallax effect
				$('#portfoliolist .portfolio').hover(function() {
					$(this).find('.label').stop().animate({
						bottom : 0
					}, 200, 'easeOutQuad');
					$(this).find('img').stop().animate({
						top : -30
					}, 500, 'easeOutQuad');
				}, function() {
					$(this).find('.label').stop().animate({
						bottom : -40
					}, 200, 'easeInQuad');
					$(this).find('img').stop().animate({
						top : 0
					}, 300, 'easeOutQuad');
				});

			}

		};

		// Run the show!
		filterList.init();

	});
</script>
<!-- start top_js_button -->
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
var numbers=8;
function loada(types){
	
	 gengxin(numbers,types); 
}


function gengxin(numa,typea){
	var user={
			number:numa,
			typec:typea
	}
	$.ajax({
		
        //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/Fmcg/ajaxindex" ,//url
            data: user,
            success: function (data) {
            	 var aa = data["findall"]; 
            	 var bb = data["number"]; 
            	 numbers=bb;
                var inner="";
               
                for(var list11=0;list11<aa.length;list11++){
                	
                	inner+='<div class=\"portfolio col-md-3\" style=\"margin-top: 10px;\">';
                	inner+='<div class=\"portfolio-wrapper\">';
                		inner+='<a href=\"${pageContext.request.contextPath }/details?id='+aa[list11].id+'\"> <img src=\"http://localhost:80/pic/'+aa[list11].kpic+'\" alt=\"loading\" height=\"270px\"/>';
                			inner+='</a>';
						inner+='<div>';
							inner+='<div class=\"label-text\">';
								inner+='<a class=\"text-title\">'+aa[list11].kname+'</a>';
								inner+='<span class=\"text-category\">热度： '+aa[list11].kcheck+' &nbsp;↑</span>';
							inner+='</div>';
							
						inner+='</div>';
					inner+='</div>';
				inner+='</div>';
               
                }
                /* alert(inner); */
               /*  documet.getElementById("indexalla").append(inner); */
                 $("#indexalla").append(inner); 
               if(inner==""){
            	   alert("已经到底了，没有更多了");
               }
            },
            error : function() {
                alert("添加失败,请重新登录。");
            }
        }); 
	
	
	
}



</script>
	<jsp:include page="listheader.jsp"></jsp:include>


	<!-- start main -->
	<div class="main_bg">
		<div class="wrap">
			<div class="main">
				<!-- start gallery_sale  -->
				<div class="gallery1">
					<div class="container">
<div class="row" id="indexalla">


						<!-- <div id="portfoliolist" class="indexalla">   -->
						
						<c:forEach items="${findlist }" var="list0">
							<div class="portfolio col-md-3" style="margin-top: 10px;">
								<div class="portfolio-wrapper">
									<a href="${pageContext.request.contextPath }/details?id=${list0.id}"> <img src="http://localhost:80/pic/${list0.kpic }"
										alt="loading" height="270px"/>
									</a>
									<div>
										<div class="label-text">
											<a class="text-title">${list0.kname }</a> <span
												class="text-category">热度： ${list0.kcheck } &nbsp;↑</span>
										</div>
										<!-- <div class="label-bg"></div> -->
									</div>
								</div>
							</div>
							</c:forEach>
							
</div>
						 </div>
					</div>
					<!-- container -->
					<script type="text/javascript" src="js/fliplightbox.min.js"></script>
					<script type="text/javascript">
						$('body').flipLightBox()
					</script>
					<div class="clear"></div>
				</div>
				<!---End-gallery----->
			</div>
		</div>
	</div>
	<!-- start footer -->
	
	<!-- start footer -->
	<div class="footer_bg1">
		<div class="wrap">
			<div class="footer">
				<!-- scroll_top_btn -->
				<script type="text/javascript">
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
					 <!-- <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a> -->
		<!--end scroll_top_btn -->
		<div class="">
			<p class="link" style="width: 100%;height:35px;border: 2px solid #EE9A00;"> 
			<button style="width: 100%;" onclick="loada('${findlist[0].ktype}')" class="btn btn-success btn-sm">加载更多</button>
			
			</p>
		</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>

</body>
</html>