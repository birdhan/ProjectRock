<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.*,java.text.*"%>
<%
	SimpleDateFormat template = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = template.format(new java.util.Date());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keyword" content="">
<link rel="shortcut icon" href="img/favicon.png">
<title>客户管理系统</title>

<!-- Icons -->
<link href="css/font-awesome.min.css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/page/css/simple-line-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/page/css/index.css"
	rel="stylesheet">

<!-- Main styles for this application -->
<link href="${pageContext.request.contextPath}/page/css/style.css"
	rel="stylesheet">
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
				
				window.onload=function(){
				     
				      goPage(1,10);
				      
				      var tempOption="";
				     
				  	for(var i=1;i<=totalPage;i++)
				  	{
				  		tempOption+='<option value='+i+'>'+i+'</option>'
				  	}
				  	$("#jumpWhere").html(tempOption);
				  	
				}
				var pageSize=0;//每页显示行数
				var currentPage_=1;//当前页全局变量，用于跳转时判断是否在相同页，在就不跳，否则跳转。
				var totalPage;//总页数
				function goPage(pno,psize){
					
				    var itable = document.getElementById("adminTbody");
				    var num = itable.rows.length;//表格所有行数(所有记录数)
				 
				     pageSize = psize;//每页显示行数
				    //总共分几页 
				    if(num/pageSize > parseInt(num/pageSize)){    
				            totalPage=parseInt(num/pageSize)+1;   
				      }else{   
				            totalPage=parseInt(num/pageSize);    
				        }   
				    var currentPage = pno;//当前页数
					 currentPage_=currentPage;
				    var startRow = (currentPage - 1) * pageSize+1; 
				    var endRow = currentPage * pageSize;
				        endRow = (endRow > num)? num : endRow;    
				       //遍历显示数据实现分页
				    /*for(var i=1;i<(num+1);i++){    
				        var irow = itable.rows[i-1];
				        if(i>=startRow && i<=endRow){
				            irow.style.display = "";    
				        }else{
				            irow.style.display = "none";
				        }
				    }*/
				   
					$("#adminTbody tr").hide();
					for(var i=startRow-1;i<endRow;i++)
					{
						$("#adminTbody tr").eq(i).show();
					}
				    var tempStr = "共"+num+"条记录 分"+totalPage+"页 当前第"+currentPage+"页";
					 document.getElementById("barcon1").innerHTML = tempStr;
					
				    if(currentPage>1){
						$("#firstPage").on("click",function(){
							goPage(1,psize);
						}).removeClass("ban");
						$("#prePage").on("click",function(){
							goPage(currentPage-1,psize);
						}).removeClass("ban");   
				    }else{
						$("#firstPage").off("click").addClass("ban");
						$("#prePage").off("click").addClass("ban");  
				    }
				 
				    if(currentPage<totalPage){
						$("#nextPage").on("click",function(){
							goPage(currentPage+1,psize);
						}).removeClass("ban")
						$("#lastPage").on("click",function(){
							goPage(totalPage,psize);
						}).removeClass("ban")
				    }else{
						$("#nextPage").off("click").addClass("ban");
						$("#lastPage").off("click").addClass("ban");
				    }   
					
					$("#jumpWhere").val(currentPage);
				}
				 
				 
				function jumpPage()
				{
					var num=parseInt($("#jumpWhere").val());
					if(num!=currentPage_)
					{
						goPage(num,pageSize);
					}
				}
				
					//这里代码多了几行，但是不会延迟显示，速度比较好，格式可以自定义，是理想的时间显示
					setInterval("fun(show_time)", 1);
					function fun(timeID) {
						var date = new Date(); //创建对象  
						var y = date.getFullYear(); //获取年份  
						var m = date.getMonth() + 1; //获取月份  返回0-11  
						var d = date.getDate(); // 获取日  
						var w = date.getDay(); //获取星期几  返回0-6   (0=星期天) 
						var ww = ' 星期' + '日一二三四五六'.charAt(new Date().getDay());//星期几
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

						document.getElementById(timeID.id).innerHTML = y + "-"
								+ m + "-" + d + "    " + h + ":" + minute + ":"
								+ s + "   " + ww;
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

					function aaa(id, name, zuijin, result, tel, age, buysome,
							guanzhudu, state, hobby, addre, details, type) {

						$(".xuanfu03").toggleClass("on");

						$(".bg2").delay(50).fadeToggle();

						$("#id01").val(id);
						$("#username").val(name);
						$("#zuidate").val(zuijin);
						$("#result").val(result);
						$("#tel").val(tel);
						$("#age").val(age);
						$("#buysome").val(buysome);
						$("#guanzhudu").val(guanzhudu);
						$("#state").val(state);
						$("#hobby").val(hobby);
						$("#addre").val(addre);
						$("#details").val(details);
						$("#type").val(type);

					}
					/*  附录详情*/
					function xiangqing(xq) {

						$(".xiangqign").toggleClass("on");

						$(".bg3").delay(50).fadeToggle();

						$(".xiangqing01").html(xq)

					}

					/*  客户下单  添加订单*/
					function cbook(id, username, telephone) {

						$(".cbook").toggleClass("on");

						$(".bg4").delay(50).fadeToggle();

						$("#uid").val(id);

						$("#user").val(username);

						$("#tel01").val(telephone);

					}

					function shanchu(ids, h) {
						var r=confirm("是否删除本条数据");
						if (r==true) {
							$.ajax({
								type : "POST",
								dataType : "json",
								url : "/Customer/shanchuuser",
								data : {
									id : ids
								},
								success : function(result) {
									if (result) {
										jQuery(h).parent().parent().parent()
												.remove();//删除节点
										/* alert("删除成功"); */
									}
								},
								error : function() {
									alert("异常！");
								}

							});
						}

						

						/* var r = confirm("确定删除");
						if (r == true) {
							window.location.href="/Customer/deleteuser?id="+id+"&cType="+type+"&cState=";
						}  */
					}

					function updata() {

						$.ajax({
							type : "POST",
							dataType : "json",
							url : "/Customer/xiugaiuser",
							data : $("#updata").serialize(),
							success : function(result) {
								if (result) {
									alert("修改成功");
									$(".xuanfu03").toggleClass("on");

									$(".bg2").delay(50).fadeToggle();
									window.location.reload();
								}
							},
							error : function() {
								alert("修改异常");
							}
						});

						/* $("#updata").submit();
						alert("修改成功!"); */
					}

					function submitorder() {

						$.ajax({
							type : "POST",
							dataType : "json",
							url : "/Customer/joinorder",
							data : $("#userorder").serialize(),
							success : function(result) {
								if (result) {
									alert("下单成功");

									$(".cbook").toggleClass("on");

									$(".bg4").delay(50).fadeToggle();
								}

							},
							error : function() {
								alert("下单失败");
							}

						});

					}
					function submitselect() {
						$("#selectuser").submit();

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

				<li class="nav-item"><a class="nav-link"
					href="/Customer/orderSelect"><i class="icon-star"></i> 订单管理</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/Customer/selectrecord"><i class="icon-star"></i> 成交记录</a></li>

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
			<li class="breadcrumb-item">客户资料</li>
			<li class="breadcrumb-item">查询结果</li>

			<!-- Breadcrumb Menu-->
			<li class="breadcrumb-menu hidden-md-down"></li>
		</ol>



		<div class="container-fluid">
			<div class="animated fadeIn">





				<!-- 列表 -->
				<br>
				<table class="table table-hover table-outline mb-0 hidden-sm-down">
					<thead class="thead-default">
						<tr>
							<th class="text-center">客户姓名</th>
							<th>最近联系时间/结果</th>
							<th class="text-center">联系方式</th>
							<th>年龄</th>
							<th class="text-center">曾购产品</th>
							<th>关注度</th>
							<th>客户状态</th>
							<th style="max-width: 20%;" class="text-center">客户爱好</th>
							<th>创建时间</th>
							<th>地区</th>
							<th class="text-center">附录</th>
							<th class="text-center">操作</th>

						</tr>
					</thead>
					<tbody id="adminTbody">

						<c:forEach items="${findBySome}" var="user">



							<tr>
								<!-- 客户姓名 -->
								<td class="text-center">${user.username }</td>
								<!-- 联系时间/结果 -->
								<td style="width: 14%;">
									<div>${user.zuijindate}</div> <small> ${user.result } </small>
								</td>

								<!--  联系方式-->
								<td class="text-center">${user.tel }</td>

								<!--  age-->
								<td>${user.age }</td>

								<!-- 曾购产品 -->
								<td class="text-center" style="width: 8%;">${user.buysome }</td>

								<!--关注度  -->
								<td>
									<div class="clearfix">
										<div class="float-left">
											<strong>${user.guanzhudu }%</strong>
										</div>

									</div>
									<div class="progress progress-xs">
										<div class="progress-bar bg-info" role="progressbar"
											style="width: ${user.guanzhudu }%" aria-valuenow="10"
											aria-valuemin="0" aria-valuemax="100"></div>
									</div>
								</td>


								<!-- 客户状态 -->
								<td class="text-center">${user.state }</td>

								<!-- 客户爱好 -->
								<td class="text-center" style="width: 10%;"><small>${user.hobby }
								</small></td>

								<!-- 创建时间 -->
								<td class="text-center">${user.createdate }</td>


								<td class="text-center">${user.addre }</td>

								<!-- 附录-->
								<td class="text-center">
									<button class="btn btn-sm btn-success"
										onclick="xiangqing('${user.details}')">详情</button>
								</td>

								<!-- 操作-->
								<td class="text-center">


									<div>
										<div onclick="shanchu('${user.id}',this)"
											class="btn-group float-right" style="margin-left: 4%;">

											<i class="icon-close" style="width: 15px; height: 15px;"></i>
										</div>
										<div
											onclick="aaa('${user.id}','${user.username}','${user.zuijindate}','${user.result }','${user.tel }','${user.age }','${user.buysome }','${user.guanzhudu }','${user.state }','${user.hobby }','${user.addre }','${user.details}','${user.type }')"
											class="btn-group float-right" style="margin-left: 4%;">
											<i class="icon-arrow-up-circle"></i>
										</div>


										<div
											onclick="cbook('${user.id}','${user.username}','${user.tel }')"
											class="btn-group float-right" style="margin-left: 4%;">
											<i class="icon-basket-loaded"></i>
										</div>

									</div>

								</td>
							</tr>

							<!-- 一次循环结束 -->
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>


		</main>



	</div>

	<footer class="app-footer">
	
	<ul class="pagination" style="float: left; margin-top: 6px;">
		<li class="page-item"><a class="page-link" href="###" id="firstPage">首页</a></li>
		<li class="page-item"><a class="page-link" href="###" id="prePage">上一页</a>
		</li>
		<li class="page-item"><a class="page-link" href="###" id="nextPage">下一页</a></li>
		<li class="page-item"><a class="page-link" href="###" id="lastPage">尾页</a></li>
		<li class="page-item"><select id="jumpWhere" class="page-link">
				</select></li>
		<li class="page-item"><a class="page-link" href="###" id="jumpPage" onclick="jumpPage()">跳转</a></li>
	</ul>
	<span id="barcon1" class="barcon1" style="float: left; margin-left: 10px;"></span>
	<span style="float: right;"> Welcome &copy; 2018.Technical
		support by hanchunyang.</span> </footer>

	<!-- Bootstrap and necessary plugins -->
	<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>

	<script src="bower_components/tether/dist/js/tether.min.js"></script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="bower_components/pace/pace.min.js"></script>


	<!-- Plugins and scripts required by all views -->
	<script src="bower_components/chart.js/dist/Chart.min.js"></script>


	<!-- GenesisUI main scripts -->

	<script src="js/app.js"></script>






	<script src="js/views/main.js"></script>


	<!--  搜索框-->
	<div class="xuanfu01 ">
		<br> <br> <br>
		<!-- 搜索分类 -->
		<div class="row">
			<div class="col-md-12" style="width: 600px;">
				<div class="card">
					<div class="card-header">
						<strong>分类查询</strong>
					</div>
					<div class="card-block">
						<form action="/Customer/selectuser" method="post" id="selectuser"
							class="form-horizontal ">

							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="text-input">客户名称</label>
								<div class="col-md-9">
									<input type="text" name="username" class="form-control"
										placeholder="客户名称">

								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="email-input">联系方式</label>
								<div class="col-md-9">
									<input type="text" name="tel" class="form-control"
										placeholder="联系方式">

								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="password-input">客户爱好</label>
								<div class="col-md-9">
									<input type="text" name="hobby" class="form-control"
										placeholder="客户爱好">

								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="password-input">客户年龄</label>
								<div class="col-md-9">
									<input type="text" name="age" class="form-control"
										placeholder="年龄">

								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="password-input">最近联系时间</label>
								<div class="col-md-9">
									<input type="date" name="zuijindate" class="form-control"
										placeholder="最近联系时间">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="select">客户状态</label>
								<div class="col-md-9">
									<select name="state" class="form-control">
										<c:forEach items="${state }" var="states">
											<option value="${states.customerstate }">${states.customerstate }</option>
										</c:forEach>

									</select>
								</div>
							</div>

						</form>
					</div>
					<div class="card-footer">
						<button type="reset" class="btn btn-sm btn-primary"
							onclick="submitselect()">
							<i class="fa fa-dot-circle-o"></i>提交查询
						</button>
						<button type="reset" class="btn btn-sm btn-danger" onclick="ddd()">
							<i class="fa fa-ban"></i>收起本页
						</button>
					</div>
				</div>


			</div>
		</div>


	</div>






	<!-- 新增客户框 -->

	<div class="xuanfu02">
		<br> <br> <br>
		<!-- 新增客户分类 -->
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header">
						<strong>添加客户信息</strong>
					</div>
					<div class="card-block">
						<form action="/Customer/registers" method="post"
							class="form-horizontal " id="formid">

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">客户姓名</label>
								<div class="col-md-4">
									<input type="text" id="text-input" name="username"
										class="form-control" placeholder="请输入3-5个字符">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">联系电话</label>
								<div class="col-md-4">
									<input type="text" id="telephone" name="tel"
										class="form-control" placeholder="不能为空">

								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">最近联系时间</label>
								<div class="col-md-4">
									<input type="date" id="text-input" name="zuidate"
										class="form-control" placeholder="有效时间">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">联系结果</label>
								<div class="col-md-4">
									<input type="text" id="email-input" name="result"
										class="form-control" placeholder="字符不宜过长">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">关注度</label>
								<div class="col-md-4">
									<input type="text" id="text-input" name="guanzhudu"
										class="form-control" placeholder="请输入1-100之间整数">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">曾购藏品</label>
								<div class="col-md-4">
									<input type="text" id="email-input" name="buysome"
										class="form-control" placeholder="不宜过长">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">收藏爱好</label>
								<div class="col-md-4">
									<input type="text" id="text-input" name="hobby"
										class="form-control" placeholder="爱好">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">年龄</label>
								<div class="col-md-4">
									<input type="text" name="age" class="form-control"
										placeholder="有效整数默认20">

								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="select">客户状态</label>
								<div class="col-md-4">
									<select id="select" name="state" class="form-control">
										<c:forEach items="${state }" var="state">
											<option value="${state.customerstate }">${state.customerstate }</option>
										</c:forEach>
									</select>
								</div>

								<label class="col-md-2 form-control-label" for="select">客户类型</label>
								<div class="col-md-4">
									<select id="select" name="type" class="form-control">
										<c:forEach items="${type }" var="type">
											<option value="${type.customertype }">${type.customertype }</option>
										</c:forEach>
									</select>
								</div>
							</div>



							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="textarea-input">附录</label>
								<div class="col-md-4">
									<textarea id="textarea-input" name="details" rows="9"
										class="form-control" placeholder="Content.."></textarea>
								</div>

								<label class="col-md-2 form-control-label" for="select">客户地址</label>
								<div class="col-md-4">
									<select id="select" name="addre" class="form-control">
										<c:forEach items="${address }" var="address">
											<option value="${address.customeraddress }">${address.customeraddress }</option>

										</c:forEach>

									</select>
								</div>


							</div>

							<button type="reset" class="btn btn-sm btn-primary"
								onclick="upsubmin()">
								<i class="fa fa-dot-circle-o"></i> 保存客户
							</button>
							<button type="reset" class="btn btn-sm btn-danger"
								onclick="ccc()">
								<i class="fa fa-ban"></i>收起本页
							</button>
						</form>

						<script type="text/javascript">
							function upsubmin() {

								var tel = document.getElementById("telephone").value;
								if (tel == "") {
									alert("电话不能为空");
								} else {
									document.getElementById("formid").submit();
									alert("保存成功");
								}

							}
						</script>
					</div>


				</div>
			</div>

		</div>
	</div>


	<!-- 修改客户信息框 -->

	<div class="xuanfu03">
		<br> <br> <br>
		<!-- 修改客户信息分类 -->
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header">
						<strong>修改客户信息</strong>
					</div>
					<div class="card-block">
						<form action="/Customer/updataUser" method="post" id="updata"
							class="form-horizontal ">
							<input type="hidden" name="id" id="id01">

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">客户姓名</label>
								<div class="col-md-4">
									<input type="text" id="username" name="username"
										class="form-control">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">联系电话</label>
								<div class="col-md-4">
									<input type="email" id="tel" name="tel" class="form-control">

								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">最近联系时间</label>
								<div class="col-md-4">
									<input type="date" id="zuidate" name="zuidate"
										class="form-control">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">联系结果</label>
								<div class="col-md-4">
									<input type="email" id="result" name="result"
										class="form-control">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">关注度</label>
								<div class="col-md-4">
									<input type="text" id="guanzhudu" name="guanzhudu"
										class="form-control">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">曾购藏品</label>
								<div class="col-md-4">
									<input type="email" id="buysome" name="buysome"
										class="form-control">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">收藏爱好</label>
								<div class="col-md-4">
									<input type="text" id="hobby" name="hobby" class="form-control">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">年龄</label>
								<div class="col-md-4">
									<input type="email" id="age" name="age1" class="form-control">

								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="select">客户状态</label>
								<div class="col-md-4">
									<select id="state" name="state" class="form-control">
										<c:forEach items="${state }" var="state">
											<option value="${state.customerstate }">${state.customerstate }</option>
										</c:forEach>

									</select>
								</div>

								<label class="col-md-2 form-control-label" for="select">客户类型</label>
								<div class="col-md-4">

									<select id="type" name="type" class="form-control">
										<c:forEach items="${type }" var="type">
											<option value="${type.customertype }">${type.customertype }</option>
										</c:forEach>

									</select>
								</div>
							</div>



							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="textarea-input">附录</label>
								<div class="col-md-4">
									<textarea id="details" name="details" rows="9"
										class="form-control"></textarea>
								</div>


								<label class="col-md-2 form-control-label" for="select">客户地址</label>
								<div class="col-md-4">
									<select id="addre" name="addre" class="form-control">
										<c:forEach items="${address }" var="address">
											<option value="${address.customeraddress }">${address.customeraddress }</option>
										</c:forEach>

									</select>
								</div>



							</div>

							<button type="reset" class="btn btn-sm btn-primary"
								onclick="updata()">
								<i class="fa fa-dot-circle-o"></i>确认修改
							</button>
							<button type="reset" class="btn btn-sm btn-danger"
								onclick="aaa()">
								<i class="fa fa-ban"></i>取消修改
							</button>
						</form>

					</div>


				</div>
			</div>

		</div>
	</div>

	<!-- 客户订单 -->
	<div class="cbook">
		<br> <br> <br>

		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header">
						<strong>客户订单</strong>
					</div>
					<div class="card-block">
						<form action="/Customer/userorder" method="post" id="userorder"
							class="form-horizontal ">

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">客户</label>
								<div class="col-md-4">
									<!-- <input type="text" id="disabled-input" name="disabled-input" class="form-control" placeholder="客户名"> -->
									<input type="text" id="user" name="user" class="form-control"
										readonly="readonly"> <input type="hidden" name="uid"
										id="uid">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">手机号</label>
								<div class="col-md-4">
									<input type="text" id="tel01" name="tel" class="form-control"
										readonly="readonly">

								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">标题</label>
								<div class="col-md-4">
									<input type="text" id="title" name="title" class="form-control"
										placeholder="标题">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">合同号</label>
								<div class="col-md-4">
									<input type="text" id="contractnumber" name="contractnumber"
										class="form-control" placeholder="合同">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">单号</label>
								<div class="col-md-4">
									<input type="text" id="ordernumber" name="ordernumber"
										class="form-control" placeholder="单号">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">付款时间</label>
								<div class="col-md-4">
									<input type="date" id="paymentdate" name="paymentdate"
										class="form-control" placeholder="Enter Email">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">金额</label>
								<div class="col-md-4">
									<input type="text" id="money" name="money" class="form-control"
										placeholder="Text">

								</div>
								<label class="col-md-2 form-control-label" for="select">付款情况</label>
								<div class="col-md-4">
									<select id="paymentstate" name=paymentstate
										class="form-control">
										<option value="未付款">未付款</option>
										<option value="已付款">已付款</option>

									</select>
								</div>

							</div>



							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="textarea-input">详情</label>
								<div class="col-md-4">
									<textarea id="details" name="details" rows="9"
										class="form-control" placeholder="Content.."></textarea>
								</div>

							</div>

							<button type="reset" class="btn btn-sm btn-primary"
								onclick="submitorder()">
								<i class="fa fa-dot-circle-o"></i>保存
							</button>
							<button type="reset" class="btn btn-sm btn-danger"
								onclick="cbook()">
								<i class="fa fa-ban"></i>取消
							</button>
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
							<p class="xiangqing01">这个人就是一个sb，欠揍，能他妈气死人，他要是墨迹完了还不买，我把腿卸下来一个</p>

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