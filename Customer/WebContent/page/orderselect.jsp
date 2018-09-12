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
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/page/img/favicon.png">
<title>荣墨斋艺术馆</title>

<!-- Icons -->
<link
	href="${pageContext.request.contextPath}/page/css/font-awesome.min.css"
	rel="stylesheet">
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

					function aaa(id,user, tel, contract, order, title, moner,
							payment, state, details) {

						$(".xuanfu03").toggleClass("on");

						$(".bg2").delay(50).fadeToggle();

						$('#user').val(user);

						$('#oid').val(id);
						
						$('#tel').val(tel);
						$('#contractnumber').val(contract);
						$('#ordernumber').val(order);
						$('#title').val(title);
						$('#money').val(moner);
						$('#paymentdate').val(payment);
						$('#paymentstate').val(state);
						$('#details').val(details);

					}
					/*  附录详情*/
					function xiangqing(xq) {

						$(".xiangqign").toggleClass("on");

						$(".bg3").delay(50).fadeToggle();
						$('#detailss').html(xq);

					}

					/*  客户下单  添加订单*/
					function cbook() {

						$(".cbook").toggleClass("on");

						$(".bg4").delay(50).fadeToggle();

					}

					function shanchu(ids,t) {
						var co=confirm("是否删除本条数据");
						if (co==true) {
							$.ajax({
								type : "POST",
								dataType : "json",
								url : "/Customer/shanchuorder",
								data : {
									id : ids
								},
								success : function(result) {
									if (result) {
										jQuery(t).parent().parent().parent()
												.remove();//删除节点
										
									}
								},
								error : function() {
									alert("异常！");
								}

							});
							
						}
						
						/* var r = confirm("确定删除");
						if (r == true) {
							window.location.href = "/Customer/deleteorder?id="
									+ id;
						} */

					}

					function submitorder() {
						
						$.ajax({
							
							type:"POST",
							dataType:"json",
							url:"/Customer/xiugaiorder",
							data:$("#formorder").serialize(),
							success:function(result){
								if (result) {
									alert("修改成功");
									window.location.reload();
									$(".xuanfu03").toggleClass("on");

									$(".bg2").delay(50).fadeToggle();
								}
							},
							erroe:function(){
								alert("修改失败");
							}
						});
						/* $('#formorder').submit();
						alert("修改成功！"); */

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
			<li class="breadcrumb-item">订单信息</li>
			<li class="breadcrumb-item">查询结果</li>

			<!-- Breadcrumb Menu-->
			<li class="breadcrumb-menu hidden-md-down">
				<div class="btn-group" role="group"
					aria-label="Button group with nested dropdown">
					<!-- <div class="btn btn-secondary" onclick="ddd()"
						style="cursor: pointer;">
						<i class="icon-speech"></i>&nbsp;&nbsp;分类查询
					</div>
 -->

					<!-- <div class="btn btn-secondary " style="cursor: pointer;" onclick="ccc()"><i
						class="icon-settings"></i> &nbsp;新增客户</div> -->


				</div>
			</li>
		</ol>



		<div class="container-fluid">
			<div class="animated fadeIn">
				<div class="row">

					<!-- 四个顶部四色框 -->


				</div>
				<!-- 列表 -->
				<br>
				<table class="table table-hover table-outline mb-0 hidden-sm-down">
					<thead class="thead-default">
						<tr>
							<th class="text-center">客户姓名</th>
							<th>联系方式</th>
							<th class="text-center">合同号</th>
							<th>单号</th>
							<th class="text-center">标题</th>
							<th>金额</th>
							<th>付款时间</th>
							<th>付款情况</th>
							<th class="text-center">详情</th>
							<th class="text-center">操作</th>

						</tr>
					</thead>
					<tbody id="adminTbody">

						<c:forEach items="${find }" var="orderlist">
							<tr>
								<!-- 客户姓名 -->
								<td class="text-center">${orderlist.user }</td>
								<!-- 联系方式 -->
								<td>${orderlist.tel }</td>

								<!--  合同号-->
								<td class="text-center">${orderlist.contractnumber }</td>

								<!--  单号-->
								<td>${orderlist.ordernumber }</td>

								<!-- 标题 -->
								<td class="text-center" style="width: 10%;">
									${orderlist.title }</td>

								<!--金额  -->
								<td>${orderlist.money }</td>


								<!-- 付款时间 -->
								<td class="text-center">${orderlist.paymentdate }</td>

								<!-- 付款情况 -->
								<td class="text-center">${orderlist.paymentstate }</td>



								<!-- 附录-->
								<td class="text-center">
									<button class="btn btn-sm btn-success"
										onclick="xiangqing('${orderlist.details}')">详情</button>
								</td>

								<!-- 操作-->
								<td class="text-center">


									<div style="cursor: pointer;">
										<div onclick="shanchu('${orderlist.id}',this)"
											class="btn-group float-right" style="margin-left: 4%;">

											<i class="icon-close" style="width: 20px; height: 20px;"></i>
										</div>
										
										<div onclick="aaa('${orderlist.id}','${orderlist.user }','${orderlist.tel }','${orderlist.contractnumber }','${orderlist.ordernumber }','${orderlist.title }','${orderlist.money }','${orderlist.paymentdate }','${orderlist.paymentstate }','${orderlist.details}')"
											class="btn-group float-right" style="margin-left: 4%;">

											<i class="icon-arrow-up-circle" style="width: 20px; height: 20px;"></i>
										</div>
									</div>



								</td>
							</tr>
						</c:forEach>
						<!-- 一次循环结束 -->

					</tbody>
				</table>
			</div>
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

	<%-- <script src="${pageContext.request.contextPath}/page/js/app.js"></script> --%>






	<script src="${pageContext.request.contextPath}/page/js/views/main.js"></script>


	<!--  搜索框-->
	<div class="xuanfu01 ">
		<br>
		<br>
		<br>
		<!-- 搜索分类 -->
		<div class="row">
			<div class="col-md-12" style="width: 600px;">
				<div class="card">
					<div class="card-header">
						<strong>查询订单</strong>
					</div>
					<div class="card-block">
						<form action="" method="post" enctype="multipart/form-data"
							class="form-horizontal ">

							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="text-input">客户名称</label>
								<div class="col-md-9">
									<input type="text" id="text-input" name="text-input"
										class="form-control" placeholder="客户名称">

								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="email-input">联系方式</label>
								<div class="col-md-9">
									<input type="email" id="email-input" name="email-input"
										class="form-control" placeholder="联系方式">

								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="password-input">合同号</label>
								<div class="col-md-9">
									<input type="text" id="password-input" name="password-input"
										class="form-control" placeholder="客户爱好">

								</div>
							</div>
							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="password-input">单号</label>
								<div class="col-md-9">
									<input type="text" id="password-input" name="password-input"
										class="form-control" placeholder="年龄">

								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="password-input">标题</label>
								<div class="col-md-9">
									<input type="text" id="password-input" name="password-input"
										class="form-control" placeholder="最近联系时间">

								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-3 form-control-label" for="password-input">金额</label>
								<div class="col-md-9">
									<input type="text" id="password-input" name="password-input"
										class="form-control" placeholder="最近联系时间">

								</div>
							</div>


						</form>
					</div>
					<div class="card-footer">
						<button type="submit" class="btn btn-sm btn-primary">
							<i class="fa fa-dot-circle-o"></i>开始查询
						</button>
						<button type="reset" class="btn btn-sm btn-danger" onclick="ddd()">
							<i class="fa fa-ban"></i>取消查询
						</button>
					</div>
				</div>


			</div>
		</div>


	</div>


	<!-- 修改订单信息框 -->

	<div class="xuanfu03">
		<br>
		<br>
		<br>
		<!-- 修改订单信息分类 -->
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header">
						<strong>修改订单信息</strong>
					</div>
					<div class="card-block">
						<form action="/Customer/updataorder" method="post" id="formorder"
							class="form-horizontal ">

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">客户姓名</label>
								<div class="col-md-4">

									<input type="text" id="user" name="user" class="form-control"
										readonly="readonly">
										<input type="hidden" name="id" id="oid">
								</div>

								<label class="col-md-2 form-control-label" for="email-input">联系电话</label>
								<div class="col-md-4">

									<input type="text" id="tel" name="tel" class="form-control"
										readonly="readonly">
								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">合同号</label>
								<div class="col-md-4">
									<input type="text" id="contractnumber" name="contractnumber"
										class="form-control">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">订单号</label>
								<div class="col-md-4">
									<input type="text" id="ordernumber" name="ordernumber"
										class="form-control">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">标题</label>
								<div class="col-md-4">
									<input type="text" id="title" name="title" class="form-control">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">金额</label>
								<div class="col-md-4">
									<input type="text" id="money" name="money" class="form-control">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">付款时间</label>
								<div class="col-md-4">
									<input type="date" id="paymentdate" name="paymentdate"
										class="form-control">

								</div>

								<label class="col-md-2 form-control-label" for="select">付款情况</label>
								<div class="col-md-4">
									<select id="paymentstate" name="paymentstate"
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
										class="form-control"></textarea>
								</div>

							</div>

							<button type="reset" class="btn btn-sm btn-primary"
								onclick="submitorder()">
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
		<br>
		<br>
		<br>

		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header">
						<strong>客户订单</strong>
					</div>
					<div class="card-block">
						<form action="" method="post" enctype="multipart/form-data"
							class="form-horizontal ">

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">客户</label>
								<div class="col-md-4">
									<!-- <input type="text" id="disabled-input" name="disabled-input" class="form-control" placeholder="客户名"> -->
									<input type="text" id="disabled-input" name="disabled-input"
										class="form-control" placeholder="查欢" disabled="">
								</div>

								<label class="col-md-2 form-control-label" for="email-input">手机号</label>
								<div class="col-md-4">
									<input type="email" id="disabled-input" name="disabled-input"
										class="form-control" placeholder="15145033695" disabled="">

								</div>
							</div>

							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">标题</label>
								<div class="col-md-4">
									<input type="text" id="text-input" name="text-input"
										class="form-control" placeholder="标题">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">合同号</label>
								<div class="col-md-4">
									<input type="email" id="email-input" name="email-input"
										class="form-control" placeholder="合同">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">单号</label>
								<div class="col-md-4">
									<input type="text" id="text-input" name="text-input"
										class="form-control" placeholder="单号">

								</div>

								<label class="col-md-2 form-control-label" for="email-input">付款时间</label>
								<div class="col-md-4">
									<input type="date" id="email-input" name="email-input"
										class="form-control" placeholder="Enter Email">

								</div>
							</div>


							<div class="form-group row">
								<label class="col-md-2 form-control-label" for="text-input">金额</label>
								<div class="col-md-4">
									<input type="text" id="text-input" name="text-input"
										class="form-control" placeholder="Text">

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
									<textarea id="textarea-input" name="textarea-input" rows="9"
										class="form-control" placeholder="Content.."></textarea>
								</div>

							</div>

							<button type="submit" class="btn btn-sm btn-primary">
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
							<p id="detailss">这个人就是一个sb，欠揍，能他妈气死人，他要是墨迹完了还不买，我把腿卸下来一个</p>

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