<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="all">
<!--//booststrap end-->
<!-- font-awesome icons -->
<link href="${pageContext.request.contextPath }/css/fontawesome-all.min.css" rel="stylesheet"
	type="text/css" media="all">
<!-- //font-awesome icons -->
<link href="${pageContext.request.contextPath }/css/style.css" rel='stylesheet' type='text/css' media="all">
<!--//stylesheets-->
<link href="${pageContext.request.contextPath }/css/gooleapis.css"
	rel="stylesheet">
</head>
<body>
<script type="text/javascript">
var bbb="${success}";
if(typeof bbb == "undefined" || bbb == null || bbb == ""){
	  
}else{
	  alert(bbb);
}
function tijiao(){
	document.getElementById('dsf').submit();
}
</script>

	<!--headder-->
	<div class="header-outs" id="home">
		<div class="header-w3layouts">
			<!--//navigation section -->
			<div class="headr-title">
				<div class="hedder-up">
					<h1>
						<a class="navbar-brand" href="index.html">${company.companyname }</a>
					</h1>
				</div>
				<div class="header-call">
					<!--  <span class="fas fa-phone-volume brand-icon"></span> -->
					<!--  <h4 style="color: #212529;">广东省汕头市</h4> -->
				</div>
				<div class="clearfix"></div>
			</div>
			<nav class="navbar navbar-expand-lg navbar-light">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse  nav-fill "
				id="navbarSupportedContent">
				<ul class="navbar-nav nav-pills nav-fill">
                     <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath }/company/enterprise">企业信息<span class="sr-only">(current)</span></a>
                     </li>
                     <li class="nav-item">
                        <a href="${pageContext.request.contextPath }/company/drugsadmin" class="nav-link ">药品管理</a>
                     </li>
                     <li class="nav-item">
                        <a href="${pageContext.request.contextPath }/company/orderadmin" class="nav-link ">订单处理</a>
                     </li>
                     <li class="nav-item active">
                        <a href="${pageContext.request.contextPath }/company/updrugs" class="nav-link ">添加良药</a>
                     </li>
                    
                  </ul>
			</div>
			</nav>
		</div>
	</div>
	<!-- //Navigation-->
	<!--//headder-->
	<div class="inner_page-banner"></div>
	<!--contact --> 
      <section class="contact py-lg-4 py-md-3 py-sm-3 py-3">
         <div class="container py-lg-5 py-md-4 py-sm-4 py-3">
            <h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">上传本店药品</h3>
            <div class="agile-info-para">
              
               <div class="row contact-wls-detail">
                  <div class="col-md-12 contact-form pb-lg-3 pb-2">
                     <form action="${pageContext.request.contextPath }/company/updatadrugs" method="post" id="dsf" enctype="multipart/form-data">
                        <div class="row agile-wls-contact-mid mb-lg-4 mb-3">
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                           <label for="">商品名称</label>
                              <input type="text" name="dname" class="form-control" placeholder="请正确无误的录入商品名" >
                           </div>
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                            <label for="">药物功效</label>
                               <input type="text" name="jianshu" class="form-control" placeholder="在25字符以下" > 
                           </div>
                        </div>
                        <div class="row agile-wls-contact-mid mb-lg-4 mb-3">
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                            <label for="">药物描述</label>
                              <input type="text" name="detais" class="form-control" placeholder="在100字符以下" >
                           </div>
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                            <label for="">药品售价</label>
                              <input type="text" name="dprice" class="form-control" placeholder="只支持数字" onkeyup="if(isNaN(value))execCommand('undo')">
                           </div>
                        </div>
                         <div class="row agile-wls-contact-mid mb-lg-4 mb-3">
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                            <label for="">使用方法</label>
                              <input type="text" name="usetype" class="form-control" placeholder="eg：一日三次" >
                           </div>
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                            <label for="">药物类型</label>
                              <input type="text" name="type" class="form-control" placeholder="处方药或者非处方药" >
                           </div>
                        </div>
                           <div class="row agile-wls-contact-mid mb-lg-4 mb-3">
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <!-- <input type="text" class="form-control" placeholder="单位名称" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"> -->
                               <label for="">药品图片</label>
                              <input type="file" name="imageFile" class="form-control">
                              
                           </div>
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <!-- <input type="text" class="form-control" placeholder="单位地点" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"> -->
                             <!--  <label for="">图片预览</label>
                              <img alt="" src=""> -->
                              <label for="">药品存量</label>
                              <input type="text" name="stock" class="form-control" placeholder="只支持数字" onkeyup="if(isNaN(value))execCommand('undo')">
                              
                           </div> 
                        </div>
                       
                        <button type="button" class="btn sent-butnn btn-lg" onclick="tijiao()">提交数据</button> 
                     </form>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <!--//contact  -->
	<footer>
	<p>客户投诉电话:400-650-9988</p>
	</footer>
	<!-- //Footer -->
	<!--js working-->
	<script src='${pageContext.request.contextPath }/js/jquery-2.2.3.min.js'></script>
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