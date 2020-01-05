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
      <meta name="keywords" content="Trim-Fit Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
         Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
      <script>
         addEventListener("load", function () {
         	setTimeout(hideURLbar, 0);
         }, false);
         
         function hideURLbar() {
         	window.scrollTo(0, 1);
         }
         function xiugai(id){
        	 document.getElementById(id).disabled=false;
         }
         function xiugai1(id){
        	 document.getElementById(id).disabled=false;
         }
         function queding(id){
        	 var price=document.getElementById(id).value;
        	 
        	 document.getElementById(id).disabled=true;
        	/*  window.location.href="${pageContext.request.contextPath}/company/uppricebyid"; */
        	 var datas={
        		 eid:id,
        		 dprice:price
        	 }
        	 $.ajax({
        		 type: "POST",//方法类型
                 dataType: "json",//预期服务器返回的数据类型
                 url: "/drugs/company/uppricebyid" ,//url
                 data: datas,
                 success: function (data) {
                	 
                 },
                 error:function(){
                	 
                 }
        		 
        	 })
         }
         function queding1(id,eid){
        	 var price=document.getElementById(id).value;
        	 
        	 document.getElementById(id).disabled=true;
        	/*  window.location.href="${pageContext.request.contextPath}/company/uppricebyid"; */
        	 var datas={
        		 eid:eid,
        		 stock:price
        	 }
        	 $.ajax({
        		 type: "POST",//方法类型
                 dataType: "json",//预期服务器返回的数据类型
                 url: "/drugs/company/upstockbyid" ,//url
                 data: datas,
                 success: function (data) {
                	 
                 },
                 error:function(){
                	 
                 }
        		 
        	 })
         }
         function shanchudrugs(id){
        	 window.location.href="${pageContext.request.contextPath}/company/deletedrugs?did="+id;
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
      <link href="//fonts.googleapis.com/css?family=Arimo:400,400i,700,700i" rel="stylesheet">
   </head>
   <body>
      <!--headder-->
      <div class="header-outs" id="home">
         <div class="header-w3layouts">
            <!--//navigation section -->
            <div class="headr-title">
               <div class="hedder-up">
                  <h1><a class="navbar-brand" href="index.html">${company.companyname }</a></h1>
               </div>
               <div class="header-call">
                 <!--  <span class="fas fa-phone-volume brand-icon"></span> -->
                 <!--  <h4 style="color: #212529;">广东省汕头市</h4> -->
               </div>
               <div class="clearfix"></div>
            </div>
            <nav class="navbar navbar-expand-lg navbar-light">
               <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
               </button>
               <div class="collapse navbar-collapse  nav-fill " id="navbarSupportedContent">
                  <ul class="navbar-nav nav-pills nav-fill">
                     <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath }/company/enterprise">企业信息<span class="sr-only">(current)</span></a>
                     </li>
                     <li class="nav-item active">
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
    
          <!--Typography-->
      <section class="about-inner py-lg-4 py-md-3 py-sm-3 py-3">
         <div class="container py-lg-5 py-md-4 py-sm-4 py-3">
            <!-- <h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">Typography</h3> -->
            <div class="typo-grids">
               <div class="typo-grids pt-md-5 pt-sm-4 pt-4">
                  <div class="sub-title">
                     <div class="sub-head mb-3 ">
                        <h4>本店所有药品</h4> 
                     </div>
                     <div class="bd-example mb-4">
                        <table class="table">
                           <thead class="thead-dark">
                              <tr>
                                 <th scope="col">药品名称</th>
                                 <th scope="col">药品分类</th>
                                 <th scope="col">药品价格</th>
                                 <th scope="col">药品库存</th>
                                 <th scope="col">操作</th>
                              </tr>
                           </thead>
                           <tbody>
                           <c:forEach items="${drugseid }" var="drugseid">
                              <tr>
                                 <td>${drugseid.dname }</td>
                                 <td>${drugseid.type }</td>
                                 <td><input type="text" value="${drugseid.dprice }" style="width: 60px;" disabled="true" id="${drugseid.id }">&nbsp;<button onclick="xiugai('${drugseid.id }')">修改</button><button onclick="queding('${drugseid.id }')">确定</button></td> 
                                 <td><input type="text" value="${drugseid.stock }" style="width: 70px;" disabled="true" id="${drugseid.id }001">&nbsp;<button onclick="xiugai1('${drugseid.id }001')">修改</button><button onclick="queding1('${drugseid.id }001','${drugseid.id }')">确定</button></td>
                                 <td><button onclick="shanchudrugs('${drugseid.id }')">×</button></td>
                              </tr>
                              </c:forEach>
                           </tbody>
                        </table>
                   
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <!--//Typography-->
    
  <!--     <section class="buttom-footer py-lg-4 py-md-3 py-sm-3 py-3">
         <div class="container py-lg-5 py-md-5 py-sm-4 py-4">
            <div class="row footer-agile-grids text-center">
               <div class="col-lg-2 col-md-2 col-sm-2 wthree-left-right">
                
               </div>
               <div class="col-lg-8 col-md-8 col-sm-8 wthree-left-right">
                  <h4>企业信息展示</h4> 
                  <div class="wls-hours-list">
                     <ul>
                        <li class="d-flex">企业负责人<span class="time ml-auto"> 查欢</span></li>
                        <li class="d-flex">营业执照号  <span class="time ml-auto"> 1254565232154525</span></li>
                        <li class="d-flex">税务登记号<span class="time ml-auto"> 9852085208520</span></li>
                        <li class="d-flex">卫生许可证号 <span class="time ml-auto"> 9852085208520</span></li>
                        <li class="d-flex">健康证号   <span class="time ml-auto"> 9852085208520</span></li>
                        <li class="d-flex">负责人证件号 <span class="time ml-auto"> 9852085208520</span></li>
                     </ul>
                  </div>
               </div>
               <div class="col-lg-2 col-md-2 col-sm-2 wthree-left-right">
                 
               </div>
            </div>
         </div>
      </section> -->
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