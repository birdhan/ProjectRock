<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keyword" content="">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/page/img/favicon.png">
<title>荣墨斋艺术馆</title>

<!-- Icons -->
<link href="${pageContext.request.contextPath }/page/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/page/css/simple-line-icons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/page/css/index.css" rel="stylesheet">

<!-- Main styles for this application -->
<link href="${pageContext.request.contextPath }/page/css/style.css" rel="stylesheet">
</head>

<body
	class="app header-fixed sidebar-fixed aside-menu-fixed aside-menu-hidden">
	
	


	<header class="app-header navbar">
		<button class="navbar-toggler mobile-sidebar-toggler hidden-lg-up"
			type="button">☰</button>
		<a class="navbar-brand" href="www.baidu.com"></a>
		<ul class="nav navbar-nav hidden-md-down">
			
			<li class="nav-item px-1"><a class="nav-link" href="#"></a></li>

			<li class="nav-item px-1"><a class="nav-link">当前用户：</a></li>
			<li class="nav-item px-1"><a class="nav-link">系统管理员</a></li>
		</ul>
		<ul class="nav navbar-nav ml-auto">
			<!--  <li class="nav-item hidden-md-down">
                <a class="nav-link" href="#"><i class="icon-bell"></i><span class="badge badge-pill badge-danger">5</span></a>
            </li> -->
			<li class="nav-item hidden-md-down">
<!--头部时间   js  -->
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
									+ "-" + m + "-" + d + "    " + h + ":"
									+ minute + ":" + s + "   " + ww;
							//document.write(y+"-"+m+"-"+d+"   "+h+":"+minute+":"+s);  
						}
						
						function ddd() {
							
						        $(".xuanfu01").toggleClass("on");

						        $(".bg").delay(50).fadeToggle();
						  


						}
						function ccc() {

					       
					            
					            $(".xuanfu02").toggleClass("on");

					            $(".bg1").delay(50).fadeToggle();
					        


					    }
						
						function aaa() {

						       		            
				            $(".xuanfu03").toggleClass("on");

				            $(".bg2").delay(50).fadeToggle();
				        


				    }
						/*  附录详情*/
						function xiangqing(xq) {

	       		            
							 $(".xiangqign").toggleClass("on");

					            $(".bg3").delay(50).fadeToggle(); 
				        


				    }
						
						/*  客户下单  添加订单*/
						function cbook() {

	       		            
							 $(".cbook").toggleClass("on");

					            $(".bg4").delay(50).fadeToggle(); 
				        


				    }
						
						function shanchu(){
							var r=confirm("确定删除");
							if (r==true)
							{
							    alert("你一选择删除");
							}
							else
							{
								alert("你选择了取消");
							}
						}
						
						function detype(type){
							var det=confirm("确定删除");
							if(det){
								
							window.location.href="/Customer/deletetype?typeid="+type;
							}
						}
						
						function submittype(){
							
							$('#formtype').submit();
						}
						
						function destatusaa(id){
							var st=confirm("确认删除");
							if(st){
								
							window.location.href="/Customer/deletestatus?id="+id;
							}
						}
						
						function addstate(){
							$('#formstate').submit();
						}
						
						function deaddress(id){
							var dea=confirm("确认删除");
							if(dea){
								
							window.location.href="/Customer/deleteaddress?id="+id;
							}
						}
						
						function addaddress(){
							$('#formaddress').submit();
						}
						
						function submitadmin(){
							
							
							$.ajax({
								type: "POST",//方法类型
				                dataType: "json",//预期服务器返回的数据类型
				                url: "/Customer/adminupdate" ,//url
				                data: $('#adminup').serialize(),
				                success: function (result) {
				                    console.log(result);//打印服务端返回的数据(调试用)
				                   
				                    if(result){
				                    	alert("修改成功");
				                    }else{
				                    	alert("密码不正确");
				                    }
				                    ;
				                },
				                error : function() {
				                    alert("异常！");
				                }
							});
						}

						
					</script>
				</div>
			</li>
			

		</ul>
	</header>

	<div class="app-body">
		<!-- 左边栏目列表 -->
		<div class="sidebar">
			<nav class="sidebar-nav">
				<ul class="nav">
					

					<li class="nav-title">客户信息管理</li>

					
					<li class="nav-item"><a class="nav-link" href="/Customer/index"><i
						class="icon-star"></i> 客户资料</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/Customer/selecttype?cType="><i class="icon-star"></i>
						客户类型</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/Customer/selectstatus?cState="><i class="icon-star"></i>
						客户状态</a></li>

				<li class="nav-item"><a class="nav-link" href="/Customer/orderSelect"><i
						class="icon-star"></i> 订单管理</a></li>

				<li class="nav-item"><a class="nav-link" href="/Customer/selectrecord"><i
						class="icon-star"></i> 成交记录</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/Customer/parameterset"><i class="icon-star"></i> 参数设定</a></li>
							
				

				</ul>
			</nav>
		</div>
                      <!-- 左边列表栏结束 -->
		                <!-- 头目录 -->
		<main class="main"> <!-- Breadcrumb -->
		<ol class="breadcrumb">
			<li class="breadcrumb-item">Home</li>
			<li class="breadcrumb-item">Admin</li>
			<li class="breadcrumb-item active">参数设定</li>

			<!-- Breadcrumb Menu-->
			<li class="breadcrumb-menu hidden-md-down">
				<div class="btn-group" role="group"
					aria-label="Button group with nested dropdown">
				<!-- 	<div class="btn btn-secondary" onclick="ddd()" style="cursor:pointer;"><i class="icon-speech"></i>&nbsp;&nbsp;分类查询</div> -->
					 
						
						<!-- <div class="btn btn-secondary " style="cursor: pointer;" onclick="ccc()"><i
						class="icon-settings"></i> &nbsp;新增客户</div> -->
						
						
				</div>
			</li> 
		</ol>



		<div class="container-fluid">
			<div class="animated fadeIn">
				<div class="row">
				
				<!-- 四个顶部四色框 -->
					
					<div class="col-sm-6 col-lg-3">
						<div class="card card-inverse card-primary">
							<div class="card-block pb-0">
								<div class="btn-group float-right">
									
									<i class="icon-location-pin"></i>
								</div>
								<h4 class="mb-0">客户类型设置</h4>
								<br><br><br>
								 <div class="col-lg-12" style="color: #20a8d8;">
                            <div class="card">
                                <div class="card-header">
                                    <i class="fa fa-align-justify"></i> 添加/删除
                                </div>
                                <div class="card-block">
                                    <table class="table">
                                        
                                        <tbody>
                                        <c:forEach items="${type }" var="type">
                                            <tr>
                                            
                                                <td>
                                                  ${type.customertype }                                                                           
                                                </td>
                                               
                                                <td>
                                                <div onclick="detype('${type.id }')">
                                                <i class="icon-close"></i>
                                                </div>
                                                </td>
                                            </tr>
                                             </c:forEach>
                                           
                                            <tr>
                                            
                                            <td colspan="2">
                                             <form action="/Customer/addtype" method="post" id="formtype">
                                            <input type="text" name="customertype"  placeholder="添加类型" style="width: 100%;">
                                             </form>
                                            <td>
                                            </tr>
                                            <tr>
                                            <td>
                                            <button class="btn btn-primary btn-sm" onclick="submittype()">添加</button>
                                            </td>
                                            </tr>
                                         
                                            
                                            
                                        </tbody>
                                    </table>
                                   
                                </div>
                            </div>
                        </div>
								
								
								
								
							</div>
							<div class="chart-wrapper px-1" style="height: 70px;">
								<canvas id="card-chart1" class="chart" height="70"></canvas>
							</div>
						</div>
					</div>
					

					<div class="col-sm-6 col-lg-3">
						<div class="card card-inverse card-info">
							<div class="card-block pb-0">
								<button type="button"
									class="btn btn-transparent active p-0 float-right">
									<i class="icon-location-pin"></i>
								</button>
								<h4 class="mb-0">客户状态设置</h4>
								<br><br><br>
								 <div class="col-lg-12" style="color: #63c2de;">
                            <div class="card">
                                <div class="card-header">
                                    <i class="fa fa-align-justify"></i> 添加/删除
                                </div>
                                <div class="card-block">
                                    <table class="table">
                                        
                                        <tbody>
                                        <c:forEach items="${state }" var="state">
                                            <tr>
                                                <td>
                                                ${state.customerstate }
                                                  
                                                </td>
                                                <td>
                                               <div onclick="destatusaa('${state.id }')">
                                                <i class="icon-close"></i>
                                                </div>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                           
                                            
                                            <tr>
                                            
                                            <td colspan="2">
                                            <form action="/Customer/addstatus" id="formstate" method="post">
                                            <input type="text" name="customerstate"  placeholder="添加状态" style="width: 100%;">
                                            </form>
                                            <td>
                                            </tr>
                                            <tr>
                                            <td>
                                            <button class="btn btn-primary btn-sm" onclick="addstate()">添加</button>
                                            </td>
                                            </tr>
                                        
                                        </tbody>
                                    </table>
                                   
                                </div>
                            </div>
                        </div>
							</div>
							<div class="chart-wrapper px-1" style="height: 70px;">
								<canvas id="card-chart2" class="chart" height="70"></canvas>
							</div>
						</div>
					</div>
				

					<div class="col-sm-6 col-lg-3">
						<div class="card card-inverse card-warning">
							<div class="card-block pb-0">
								<div class="btn-group float-right">
									<!-- <button type="button"
										class="btn btn-transparent active dropdown-toggle p-0"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> -->
										<i class="icon-location-pin"></i>
									<!-- </button> -->
									<div class="dropdown-menu dropdown-menu-right">
										<a class="dropdown-item" href="#">Action</a> <a
											class="dropdown-item" href="#">Another action</a> <a
											class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
								<h4 class="mb-0">地区设置</h4>
								
									<br><br><br>
								 <div class="col-lg-12" style="color: #f8cb00">
                            <div class="card">
                                <div class="card-header">
                                    <i class="fa fa-align-justify"></i> 添加/删除
                                </div>
                                <div class="card-block">
                                    <table class="table">
                                        
                                        <tbody>
                                        <c:forEach items="${address }" var="addre">
                                            <tr>
                                                <td>
                                                ${addre.customeraddress }
                                               
                                                </td>
                                                <td>
                                                <div onclick="deaddress('${addre.id}')">
                                                <i class="icon-close"></i>
                                                </div>
                                                </td>
                                            </tr>
                                            </c:forEach>
                                            
                                            
                                            <tr>
                                            
                                            <td colspan="2">
                                            <form action="/Customer/addaddress" method="post" id="formaddress">
                                            <input type="text" name="customeraddress"  placeholder="添加地区" style="width: 100%;">
                                            </form>
                                            <td>
                                            </tr>
                                            <tr>
                                            <td>
                                            <button class="btn btn-primary btn-sm" onclick="addaddress()">添加</button>
                                            </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                   
                                </div>
                            </div>
                        </div>
								
								
								
								
							</div>
							<div class="chart-wrapper" style="height: 70px;">
								<canvas id="card-chart3" class="chart" height="70"></canvas>
							</div>
						</div>
					</div>
					

					<div class="col-sm-6 col-lg-3">
						<div class="card card-inverse card-danger">
							<div class="card-block pb-0">
								<div class="btn-group float-right">
									<!-- <button type="button"
										class="btn btn-transparent active dropdown-toggle p-0"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> -->
										<i class="icon-location-pin"></i>
									<!-- </button> -->
									<div class="dropdown-menu dropdown-menu-right">
										<a class="dropdown-item" href="#">Action</a> <a
											class="dropdown-item" href="#">Another action</a> <a
											class="dropdown-item" href="#">Something else here</a>
									</div>
								</div>
								<h4 class="mb-0">用户信息修改</h4>
										<br><br><br>
							  <div class="row" style="color: #f86c6b;">
                        <div class="col-md-12" style="width: 600px;">
                            <div class="card">
                                <div class="card-header">
                                    <strong>用户修改</strong>
                                </div>
                                <div class="card-block">
                                    <form method="post"  class="form-horizontal " id="adminup" action="##" onsubmit="return false">
                                        
                                        <div class="form-group row">
                                            <label class="col-md-6 form-control-label" for="text-input">用户名</label>
                                            <div class="col-md-12">
                                                <input type="text" name="username" class="form-control">
                                                
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-md-6 form-control-label" for="email-input">原密码</label>
                                            <div class="col-md-12">
                                                <input type="text" name="password" class="form-control">
                                                
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-md-6 form-control-label" for="password-input">新密码</label>
                                            <div class="col-md-12">
                                                <input type="text" name="password2" class="form-control" >
                                                
                                            </div>
                                        </div>
                                        
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <button type="button" class="btn btn-sm btn-primary" onclick="submitadmin()"><i class="fa fa-dot-circle-o"></i>修改</button>
                                    
                                </div>
                            </div>

                           
                            </div>
                        </div>
                       
							
							
							
							
							</div>
							<div class="chart-wrapper px-1" style="height: 70px;">
								<canvas id="card-chart4" class="chart" height="70"></canvas>
							</div>
						</div>
					</div>
					
				</div>
					
				</div>
				                   
								
								
							</div>
						
					
					
				
		</main>



	</div>

	<footer class="app-footer">
		Welcome &copy; 2018.Technical support by hanchunyang.
		
	</footer>

	<!-- Bootstrap and necessary plugins -->
	<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
	
	<script src="bower_components/tether/dist/js/tether.min.js"></script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="bower_components/pace/pace.min.js"></script>


	<!-- Plugins and scripts required by all views -->
	<script src="bower_components/chart.js/dist/Chart.min.js"></script>


	<!-- GenesisUI main scripts -->

	<%-- <script src="${pageContext.request.contextPath }/page/js/app.js"></script> --%>





	
	<script src="${pageContext.request.contextPath }/page/js/views/main.js"></script>
	
	
	<!--  搜索框-->
	<div class="xuanfu01 ">
	<br><br><br>
    <!-- 搜索分类 -->
      <div class="row">
                        <div class="col-md-12" style="width: 600px;">
                            <div class="card">
                                <div class="card-header">
                                    <strong>查询订单</strong>
                                </div>
                                <div class="card-block">
                                    <form action="" method="post" enctype="multipart/form-data" class="form-horizontal ">
                                        
                                        <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="text-input">客户名称</label>
                                            <div class="col-md-9">
                                                <input type="text" id="text-input" name="text-input" class="form-control" placeholder="客户名称">
                                                
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="email-input">联系方式</label>
                                            <div class="col-md-9">
                                                <input type="email" id="email-input" name="email-input" class="form-control" placeholder="联系方式">
                                                
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="password-input">合同号</label>
                                            <div class="col-md-9">
                                                <input type="text" id="password-input" name="password-input" class="form-control" placeholder="客户爱好">
                                                
                                            </div>
                                        </div>
                                         <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="password-input">单号</label>
                                            <div class="col-md-9">
                                                <input type="text" id="password-input" name="password-input" class="form-control" placeholder="年龄">
                                              
                                            </div>
                                        </div>
                                          
                                         <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="password-input">标题</label>
                                            <div class="col-md-9">
                                                <input type="text" id="password-input" name="password-input" class="form-control" placeholder="最近联系时间">
                                               
                                            </div>
                                        </div>
                                       
                                       <div class="form-group row">
                                            <label class="col-md-3 form-control-label" for="password-input">金额</label>
                                            <div class="col-md-9">
                                                <input type="text" id="password-input" name="password-input" class="form-control" placeholder="最近联系时间">
                                               
                                            </div>
                                        </div>
                                        
                                        
                                    </form>
                                </div>
                                <div class="card-footer">
                                    <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-dot-circle-o"></i>开始查询</button>
                                    <button type="reset" class="btn btn-sm btn-danger" onclick="ddd()"><i class="fa fa-ban"></i>取消查询</button>
                                </div>
                            </div>

                           
                            </div>
                        </div>
                       
                       
                    </div>
     
      
      <!-- 修改订单信息框 -->
      
      <div class="xuanfu03">
      <br><br><br>
    <!-- 修改订单信息分类 -->
        <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong>修改订单信息</strong>
                                </div>
                                <div class="card-block">
                                    <form action="" method="post" enctype="multipart/form-data" class="form-horizontal ">
                                        
                                        <div class="form-group row">
                                            <label class="col-md-2 form-control-label" for="text-input">客户姓名</label>
                                            <div class="col-md-4">
                                               
                                               <input type="text" id="disabled-input" name="disabled-input" class="form-control" value="查欢" disabled="">
                                            </div>
                                            
                                            <label class="col-md-2 form-control-label" for="email-input">联系电话</label>
                                            <div class="col-md-4">
                                                
                                                <input type="text" id="disabled-input" name="disabled-input" class="form-control" disabled="" value="1423658987">
                                            </div>
                                        </div>
                                        
                                         <div class="form-group row">
                                            <label class="col-md-2 form-control-label" for="text-input">合同号</label>
                                            <div class="col-md-4">
                                                <input type="text" id="text-input" name="text-input" class="form-control" placeholder="Text">
                                               
                                            </div>
                                            
                                            <label class="col-md-2 form-control-label" for="email-input">订单号</label>
                                            <div class="col-md-4">
                                                <input type="email" id="email-input" name="email-input" class="form-control" placeholder="Enter Email">
                                                
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="form-group row">
                                            <label class="col-md-2 form-control-label" for="text-input">标题</label>
                                            <div class="col-md-4">
                                                <input type="text" id="text-input" name="text-input" class="form-control" placeholder="请输入1-100之间整数">
                                              
                                            </div>
                                            
                                            <label class="col-md-2 form-control-label" for="email-input">金额</label>
                                            <div class="col-md-4">
                                                <input type="email" id="email-input" name="email-input" class="form-control" placeholder="Enter Email">
                                                
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="form-group row">
                                            <label class="col-md-2 form-control-label" for="text-input">付款时间</label>
                                            <div class="col-md-4">
                                                <input type="date" id="text-input" name="text-input" class="form-control" placeholder="Text">
                                              
                                            </div>
                                            
                                            <label class="col-md-2 form-control-label" for="select">付款情况</label>
                                            <div class="col-md-4">
                                                <select id="select" name="select" class="form-control">
                                                    <option value="0" selected="selected">未付款</option>
                                                    <option value="1">已付款</option>
                                                    
                                                </select>
                                            </div>
                                        
                                        
                                            
                                        </div>
                                        
                                      
                                       
                                       
                                        
                                        <div class="form-group row">
                                        <label class="col-md-2 form-control-label" for="textarea-input">详情</label>
                                            <div class="col-md-4">
                                                <textarea id="textarea-input" name="textarea-input" rows="9" class="form-control" placeholder="Content.."></textarea>
                                            </div>
                                        
                                        </div>
                                        
                                    <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-dot-circle-o"></i>确认修改</button>
                                    <button type="reset" class="btn btn-sm btn-danger" onclick="aaa()"><i class="fa fa-ban"></i>取消修改</button>
                                         </form>
                                </div>
                                
                            
                    </div>
                    </div>
                    
      </div>
      </div>
      
      <!-- 客户订单 -->
            <div class="cbook">
      <br><br><br>
    
        <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <strong>客户订单</strong>
                                </div>
                                <div class="card-block">
                                    <form action="" method="post" enctype="multipart/form-data" class="form-horizontal ">
                                        
                                        <div class="form-group row">
                                            <label class="col-md-2 form-control-label" for="text-input">客户</label>
                                            <div class="col-md-4">
                                                <!-- <input type="text" id="disabled-input" name="disabled-input" class="form-control" placeholder="客户名"> -->
                                               <input type="text" id="disabled-input" name="disabled-input" class="form-control" placeholder="查欢" disabled="">
                                            </div>
                                            
                                            <label class="col-md-2 form-control-label" for="email-input">手机号</label>
                                            <div class="col-md-4">
                                                <input type="email" id="disabled-input" name="disabled-input" class="form-control" placeholder="15145033695" disabled="">
                                                
                                            </div>
                                        </div>
                                        
                                         <div class="form-group row">
                                            <label class="col-md-2 form-control-label" for="text-input">标题</label>
                                            <div class="col-md-4">
                                                <input type="text" id="text-input" name="text-input" class="form-control" placeholder="标题">
                                               
                                            </div>
                                            
                                            <label class="col-md-2 form-control-label" for="email-input">合同号</label>
                                            <div class="col-md-4">
                                                <input type="email" id="email-input" name="email-input" class="form-control" placeholder="合同">
                                                
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="form-group row">
                                            <label class="col-md-2 form-control-label" for="text-input">单号</label>
                                            <div class="col-md-4">
                                                <input type="text" id="text-input" name="text-input" class="form-control" placeholder="单号">
                                              
                                            </div>
                                            
                                            <label class="col-md-2 form-control-label" for="email-input">付款时间</label>
                                            <div class="col-md-4">
                                                <input type="date" id="email-input" name="email-input" class="form-control" placeholder="Enter Email">
                                                
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="form-group row">
                                            <label class="col-md-2 form-control-label" for="text-input">金额</label>
                                            <div class="col-md-4">
                                                <input type="text" id="text-input" name="text-input" class="form-control" placeholder="Text">
                                              
                                            </div>
                                            <label class="col-md-2 form-control-label" for="select">付款情况</label>
                                            <div class="col-md-4">
                                                <select id="select" name="select" class="form-control">
                                                    <option value="0">已付款</option>
                                                    <option value="1">未付款</option>
                                                   
                                                </select>
                                            </div>
                                           
                                        </div>
                                       
                                       
                                        
                                        <div class="form-group row">
                                        <label class="col-md-2 form-control-label" for="textarea-input">详情</label>
                                            <div class="col-md-4">
                                                <textarea id="textarea-input" name="textarea-input" rows="9" class="form-control" placeholder="Content.."></textarea>
                                            </div>
                                        
                                        </div>
                                        
                                    <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-dot-circle-o"></i>保存</button>
                                    <button type="reset" class="btn btn-sm btn-danger" onclick="cbook()"><i class="fa fa-ban"></i>取消</button>
                                         </form>
                                </div>
                                
                            
                    </div>
                    </div>
                    
      </div>
      </div>
      
      <div class="xiangqign">
       <div class="row">
                        <div class="col-sm-12 col-md-12">
                            <div class="card card-inverse card-success text-center">
                                <div class="card-block">
                                    <blockquote class="card-blockquote">
                                        <p>这个人就是一个sb，欠揍，能他妈气死人，他要是墨迹完了还不买，我把腿卸下来一个</p>
                                        
                                    </blockquote>
                                </div>
                            </div>
                        </div>
                        </div>
      
      </div>
      
      
<div class="bg" onclick="ddd()"></div>
<div class="bg1" onclick="ccc()"></div>
<div class="bg2" onclick="aaa()"></div>
<div class="bg3" onclick="xiangqing()"></div>
<div class="bg4" onclick="cbook()"></div>
</body>
</html>