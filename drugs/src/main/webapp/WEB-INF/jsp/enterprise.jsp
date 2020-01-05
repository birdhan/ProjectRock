<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>人民大药房</title>
      <!--meta tags -->
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="keywords" content="Trim-Fit Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
         Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
      <script>
         addEventListener("load", function () {
         	setTimeout(hideURLbar, 0);
         }, false);
         
         function hideURLbar() {
         	window.scrollTo(0, 1);
         }
      </script>
      <!--//meta tags ends here-->
      <!--booststrap-->
      <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
      <!--//booststrap end-->
      <!-- font-awesome icons -->
      <link href="${pageContext.request.contextPath }/css/fontawesome-all.min.css" rel="stylesheet" type="text/css" media="all">
      <!-- //font-awesome icons -->
      <link href="${pageContext.request.contextPath }/css/style.css" rel='stylesheet' type='text/css' media="all">
      <!--//stylesheets-->
      <link href="${pageContext.request.contextPath }/css/gooleapis.css" rel="stylesheet">
   </head>
   <body>
      <!--headder-->
      <div class="header-outs" id="home">
         <div class="header-w3layouts">
            <!--//navigation section -->
            <div class="headr-title">
               <div class="hedder-up">
                  <h1><a class="navbar-brand" href="index.html">${enterprise.companyname }</a></h1>
               </div>
               <div class="header-call">
                 <!--  <span class="fas fa-phone-volume brand-icon"></span> -->
                 <!--  <h4>广东省汕头市</h4> -->
               </div>
               <div class="clearfix"></div>
            </div>
            <nav class="navbar navbar-expand-lg navbar-light">
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse  nav-fill " id="navbarSupportedContent">
                  <ul class="navbar-nav nav-pills nav-fill">
                     <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath }/company/enterprise">企业信息<span class="sr-only">(current)</span></a>
                     </li>
                     <li class="nav-item">
                        <a href="${pageContext.request.contextPath }/company/drugsadmin" class="nav-link ">药品管理</a>
                     </li>
                     <li class="nav-item">
                        <a href="${pageContext.request.contextPath }/company/orderadmin" class="nav-link ">订单处理</a>
                     </li>
                     <li class="nav-item">
                        <a href="${pageContext.request.contextPath }/company/updrugs" class="nav-link ">添加良药</a>
                     </li>
                    
                  </ul>
               </div>
            </nav>
         </div>
      </div>
      <!-- //Navigation-->
      <!--//headder-->
      <div class="inner_page-banner">
      </div>
    
      <section class="buttom-footer py-lg-4 py-md-3 py-sm-3 py-3">
         <div class="container py-lg-5 py-md-5 py-sm-4 py-4">
            <div class="row footer-agile-grids text-center">
               <div class="col-lg-1 col-md-1 col-sm-1 wthree-left-right">
                
               </div>
               <div class="col-lg-8 col-md-8 col-sm-8 wthree-left-right">
                  <h4>企业信息展示</h4> 
                  <div class="wls-hours-list">
                     <ul>
                        <li class="d-flex">企业负责人<span class="time ml-auto">${enterprise.personcharge }</span></li>
                        <li class="d-flex">企业地址 <span class="time ml-auto">${enterprise.companyadd }</span></li>
                        <li class="d-flex">营业执照号  <span class="time ml-auto">${enterprise.yingye }</span></li>
                        <li class="d-flex">税务登记号<span class="time ml-auto"> ${enterprise.shuiwu }</span></li>
                        <li class="d-flex">卫生许可证号 <span class="time ml-auto">${enterprise.weisheng }</span></li>
                        <li class="d-flex">健康证号   <span class="time ml-auto">${enterprise.jiankang }</span></li>
                        <li class="d-flex">负责人证件号 <span class="time ml-auto">${enterprise.personnumber }</span></li>
                     </ul>
                  </div>
               </div>
               <div class="col-lg-3 col-md-3 col-sm-3 wthree-left-right" style="text-align: center;">
                 <div style="background: #47c19b; width: 100px; height: 100px;border-radius: 50px;padding-top: 30px;margin-top: 70px;margin-left: 80px;" >累计营收<br>${enterprise.ebalance }</div>
                 <p>本系统目前不支持在线提现，为了您的资金安全，请联系管理员手动提现。</p>
                </div>
            </div>
         </div>
      </section>
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
         jQuery(document).ready(function ($) {
         	$(".scroll").click(function (event) {
         		event.preventDefault();
         		$('html,body').animate({
         			scrollTop: $(this.hash).offset().top
         		}, 900);
         	});
         });
      </script>
      <!-- start-smoth-scrolling -->
      <!-- here stars scrolling icon -->
      <script>
         $(document).ready(function () {
         
         	var defaults = {
         		containerID: 'toTop', // fading element id
         		containerHoverID: 'toTopHover', // fading element hover id
         		scrollSpeed: 1200,
         		easingType: 'linear'
         	};
         
         
         	$().UItoTop({
         		easingType: 'easeOutQuart'
         	});
         
         });
      </script>
      <!-- //here ends scrolling icon -->
      <!--bootstrap working-->
      <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
      <!-- //bootstrap working-->
   </body>
</html>