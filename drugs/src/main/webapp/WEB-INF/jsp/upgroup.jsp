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
      <!--stylesheets-->
      <link href="${pageContext.request.contextPath }/css/style.css" rel='stylesheet' type='text/css' media="all">
      <!--//stylesheets-->
      <link href="${pageContext.request.contextPath }/css/gooleapis.css" rel="stylesheet">
   </head>
   <body>
      <script type="text/javascript">
      var bbb="${jieguo}";
      if(typeof bbb == "undefined" || bbb == null || bbb == ""){
    	  
      }else{
    	  alert(bbb);
      }
      
    
      </script>
      <!-- short -->
	<div class="using-border py-3">
		<div class="inner_breadcrumb  ml-4">
			<ul class="short_ls">
				<li><a href="${pageContext.request.contextPath }/consumer/first">第一网上药房</a></li>
				<li>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>

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
	function subminii(){
		document.getElementById('form222').submit();
	}
	</script>
      <!--contact -->
      <section class="contact py-lg-4 py-md-3 py-sm-3 py-3">
         <div class="container py-lg-5 py-md-4 py-sm-4 py-3">
            <h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">升级成为企业账号</h3>
            <div class="agile-info-para">
              
               <div class="row contact-wls-detail">
                  <div class="col-md-12 contact-form pb-lg-3 pb-2">
                     <form action="${pageContext.request.contextPath }/consumer/signenter" method="post" id="form222">
                        <div class="row agile-wls-contact-mid mb-lg-4 mb-3">
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <input type="text" class="form-control" name="yingye" placeholder="营业执照号" onkeyup="value=value.replace(/[^\d]/g,'') ">
                           </div>
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <input type="text" class="form-control" name="shuiwu" placeholder="税务登记证号" onkeyup="value=value.replace(/[^\d]/g,'') ">
                           </div>
                        </div>
                        <div class="row agile-wls-contact-mid mb-lg-4 mb-3">
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <input type="text" class="form-control" name="weisheng" placeholder="卫生许可证号" onkeyup="value=value.replace(/[^\d]/g,'') ">
                           </div>
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <input type="text" class="form-control" name="jiankang" placeholder="健康证号" onkeyup="value=value.replace(/[^\d]/g,'') ">
                           </div>
                        </div>
                         <div class="row agile-wls-contact-mid mb-lg-4 mb-3">
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <input type="text" class="form-control" name="personcharge" placeholder="单位负责人" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')">
                           </div>
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <input type="text" class="form-control" name="personnumber" placeholder="负责人证件号" onkeyup="value=value.replace(/[^\d]/g,'') ">
                           </div>
                        </div>
                           <div class="row agile-wls-contact-mid mb-lg-4 mb-3">
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <input type="text" class="form-control" name="companyname" placeholder="单位名称" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')">
                           </div>
                           <div class="col-lg-6 col-md-6 form-group contact-forms">
                              <input type="text" class="form-control" name="companyadd" placeholder="单位地点" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')">
                           </div>
                        </div>
                       
                        <button type="button" class="btn sent-butnn btn-lg" onclick="subminii()">提交申请</button>
                     </form>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <!--//contact  -->
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