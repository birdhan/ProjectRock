<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>哈尔滨城市管理局官网</title>
<%@ include file="/commonjsp/indexCommon-css.jsp"%>
<!-- 样式环境 -->
<%@ include file="/commonjsp/indexCommon-js.jsp"%> 
<!-- js环境 -->
<script type="text/javascript" src="${ctx }/js/bootstrap.min.js"></script>
<!-- <script src="http://www.jq22.com/jquery/1.9.1/jquery.min.js"></script> -->

<script src="${ctx }/js/jquery-1.11.0.min.js" type="text/javascript"></script> 

<link rel="stylesheet" type="text/css" href="${ctx }/css/bootstrap.min.css"/> 
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' 

type='text/css'> 

<link rel="stylesheet" type="text/css" href="${ctx }/css/index/vendor/github.css"> 

	
	
	
	<style>
		* {
			margin: 0px;
			padding: 0px;
		}
		
		#Section1 li,
		#Section2 li {
			margin-left: 50px;
			list-style: disc;
		}
		
		#Section1 li a,
		#Section2 li a {
			color: black;
		}
		
		#bianji input {
			text-align: center;
			width: 530px;
			height: 40px;
		}
		
		#bianji span {
			margin-left: 40px;
		}
		
		#fanhui {
			margin-left: 80px;
		}
		
		#fanhui,
		#fasong {
			width: 107px;
			height: 42px;
		}
		/*选项卡的样式*/
		
		.demo {
			padding: 1em 0;
		}
		
		a:hover,
		a:focus {
			outline: none;
			text-decoration: none;
		}
		
		.tab .nav-tabs {
			margin-left: -240px;
		}
		
		.tab .nav-tabs li {
			margin: 0;
			width: 240px;
			height: 60px;
		}
		
		.tab .nav-tabs li a {
			font-size: 18px;
			color: #999898;
			margin: 0;
			padding: 20px 25px;
			border-radius: 0;
			border: none;
			text-transform: uppercase;
			position: relative;
		}
		
		.tab .nav-tabs li a:hover {
			border-top: none;
			border-bottom: none;
			border-right-color: #ddd;
		}
		
		.tab .nav-tabs li.active a,
		.tab .nav-tabs li.active a:hover {
			color: #fff;
			border: none;
			background: #1fc1dd;
			border-right: 1px solid #ddd;
		}
		
		.tab .nav-tabs li.active a:before {
			content: "";
			width: 58%;
			height: 0px;
			background: #fff;
			position: absolute;
			top: 0;
			left: 0;
			right: 0;
			margin: 0 auto;
		}
		
		.tab .nav-tabs li.active a:after {
			content: "";
			border-top: 10px solid #1fc1dd;
			border-left: 10px solid transparent;
			border-right: 10px solid transparent;
			position: absolute;
			bottom: -10px;
			left: 43%;
		}
		
		.tab .tab-content {
			width: 800px;
			margin-left: -240px;
			font-size: 13px;
			color: #999898;
			line-height: 25px;
			background: #fff;
			padding: 20px;
			border-top: none;
		}
		
		.tab .tab-content h3 {
			font-size: 24px;
			color: #999898;
			margin-top: 0;
		}
		
		@media only screen and (max-width: 800px) {
			.tab .nav-tabs li {
				width: 100%;
				text-align: center;
			}
			.tab .nav-tabs li.active a,
			.tab .nav-tabs li.active a:after,
			.tab .nav-tabs li.active a:hover {
				border: none;
			}
		}
		
		table tr {
			border: 1px solid red;
		}
		
		table tr td {
			border: 1px solid red;
		}
	</style>
	
</head>

<body>

	<%@ include file="/index/indexTop.jsp"%>

	<%@ include file="/index/indexBanner.jsp"%>
	<%@ include file="/index/indexMenu.jsp"%>
	<%@ include file="/index/indexSearch.jsp"%>

	<!--内容-->
	<div id="cgdt_sy">

		<div class="demo">
			<div class="container">
				<div class="row">
					<div class="col-lg-offset-3 col-lg-6">
						<div class="tab" role="tabpanel">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"
									style="background-color: #c3f6ff;"><a href="#Section1"
									aria-controls="home" role="tab" data-toggle="tab"
									style=" text-align: center;">已公开申请</a></li>
								<li role="presentation" style="background-color: #c3f6ff;">
									<a href="#Section2" aria-controls="profile" role="tab"
									data-toggle="tab" style="text-align: center;" >我的申请</a></li>
							</ul>
							<!-- 内容 -->
							<div class="tab-content tabs">
								<div role="tabpanel" class="tab-pane fade in active"
									id="Section1">
									<c:forEach var="gong" items="${list001}">
									
									<li>
									<a href="${pageContext.request.contextPath}/selById?aaId=${gong.id}">${gong.title}</a>
									<a href="${pageContext.request.contextPath}/selById?aaId=${gong.id}" style="float: right;"><fmt:formatDate value="${gong.createtime}" pattern="MM-dd"/></a>
									<!-- <span style="float: right;"></span> -->
									</li>
									
									</c:forEach>
									
								</div>
								<div role="tabpanel" class="tab-pane fade" id="Section2">
									<div>
										<span
											style="list-style: none;font: 20px '微软雅黑'; color: black;margin-left: 80px;width: 100px; height: 20px;;">标题</span>
										<span
											style="list-style: none;font: 20px '微软雅黑'; color: black;margin-left: 340px; width: 100px; height: 20px;;">状态</span>
										<span
											style="list-style: none;font: 20px '微软雅黑'; color: black;margin-left: 170px;width: 100px; height: 20px;">申请日期</span>
									</div>
									<!-- <table > -->
									<c:forEach var="fuser" items="${uidfind}">
								<%-- 	<tr>
									<td><a href="${pageContext.request.contextPath}/selById?aaId=${fuser.id}" >${fuser.title}</a></td>
									<td><span style="margin-left: 100px">${fuser.state}</span></td>
									<td><a href="${pageContext.request.contextPath}/selById?aaId=${fuser.id}" style="float: right;"><fmt:formatDate value="${fuser.createtime}" pattern="MM-dd"/></a></td>
									</tr> --%>
									
									
									<li>
									<a href="${pageContext.request.contextPath}/selById?aaId=${fuser.id}" >${fuser.title}</a>
									
									
									
									<a href="${pageContext.request.contextPath}/selById?aaId=${fuser.id}" style="float: right; margin-right: 20px;"><fmt:formatDate value="${fuser.createtime}" pattern="MM-dd"/></a>
									
									
									<span style=" float: right; margin-right: 200px;">${fuser.state}</span>
									<%-- <span
										style="float: right;"> ${fuser.createtime}</span> --%>
									</li> 
									
									</c:forEach>
									
									<!-- </table> -->
									<div>
										 
											
											<input id="shenqing" type="button" value="发起申请"
											style="margin-left: 300px;background-color: #1fc1dd; border-radius: 10px;color: #fff;font: 18px '微软雅黑';">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



	</div>

	<%@ include file="/index/indexButtom.jsp"%>
</body>
<script>
$('#shenqing').click(function(){
var loginUserCuid = $("#loginUserCuid").val();
	if(loginUserCuid == null || loginUserCuid == ""){
		$('#login_background').css('display', 'block');
		$('#login_alert').css('display', 'block');
		return;
	}else{
	window.location.href="${ctx}/formtiao";
	
	}
	
	


});


</script>
<script type="text/javascript">
	window.onload = function() {
		var index = document.getElementsByTagName("ol")[0]; //圆点
		var box = document.getElementById("all"); //   获得大盒子
		var ul = box.children[0].children[0]; // 获取ul
		var ulLis = ul.children; //  ul 里面的所有  li
		// 复习：  cloneNode()     克隆节点       追加a.appendChild(b)  把b 放到a 的最后面
		//1.  ulLis[0].cloneNode(true)  克隆  节点
		ul.appendChild(ulLis[0].cloneNode(true)); // ul 是 a   ulLis[0].cloneNode(true) 是b

		//2. 插入 ol 里面的li
		var ol = box.children[1]; // 获得ol
		// 因为 我们要创建很多个 ol 里面的li 所以需要用到for 循环哦
		for ( var i = 0; i < ulLis.length - 1; i++) { // ul 里面的li  长度 要减去 1  因为我们克隆一个
			// 创建节点 li
			var li = document.createElement("li");
			//	li.innerHTML = i + 1; //  存在加1 的关系
			// a.appendChild(b);
			ol.appendChild(li);
		}
		var olLis = ol.children; // 找到 ol 里面的li
		olLis[0].className = 'current';
		// 3. 动画部分  遍历小li ol
		for ( var i = 0; i < olLis.length; i++) {
			olLis[i].index = i; // 赋予索引号
			olLis[i].onmouseover = function() {
				// 清除所有人
				for ( var j = 0; j < olLis.length; j++) {
					olLis[j].className = "";
				}
				this.className = 'current';
				animate(ul, -this.index * ulLis[0].offsetWidth);
				key = square = this.index; // 鼠标经过 key square 要等于 当前的索引号
			}
		}
		// 4. 定时器部分  难点
		var timer = null; // 定时器
		var key = 0; // 用来控制图片的播放的
		var square = 0; // 用来控制小方块的
		timer = setInterval(autoplay, 3000); // 每隔3s 调用一次 autoplay
		function autoplay() {
			key++; // key == 1  先 ++
			console.log(key); //  不能超过5
			if (key > ulLis.length - 1) {
				// alert('停下');
				ul.style.left = 0;
				key = 1; // 因为第6张就是第一张，我们已经播放完毕了， 下一次播放第2张
				// 第2张的索引号是1
			}
			animate(ul, -key * ulLis[0].offsetWidth);
			// 小方块的做法
			square++; // 小方块自加1
			square = square > olLis.length - 1 ? 0 : square;
			/// 清除所有人
			for ( var i = 0; i < olLis.length; i++) {
				olLis[i].className = "";
			}
			olLis[square].className = "current"; // 留下当前自己的

		}

		// 鼠标经过和停止定时器
		box.onmouseover = function() {
			clearInterval(timer);
		}

		box.onmouseout = function() {
			timer = setInterval(autoplay, 3000); // 一定这么开
		}
		index.onclick = function() {
			this.HTML
			alert('888');
		}
		//  基本封装
		function animate(obj, target) {
			clearInterval(obj.timer); // 要开启定时器，先清除以前定时器
			// 有2个参数 第一个 对象谁做动画  第二 距离 到哪里
			// 如果 offsetLeft 大于了  target 目标位置就应该反方向
			var speed = obj.offsetLeft < target ? 15 : -15;
			obj.timer = setInterval(function() {
				var result = target - obj.offsetLeft; //  他们的值 等于 0 说明完全相等
				// 动画的原理
				obj.style.left = obj.offsetLeft + speed + "px";
				if (Math.abs(result) <= 15) {
					obj.style.left = target + "px"; //抖动问题
					clearInterval(obj.timer);
				}
			}, 10);
		}

	}
</script>
<script>
	$(function() {
		var ul = $('#cgfc_lunbo ul');
		var zoujiantou = $('#zuojiantou');
		var youjiantou = $('#youjiantou');
		var img = $('#cgfc_lunbo ul li');
		img.click(function() {
			window.location.href = '#';
		})
		zoujiantou.click(function() {
			var ul_margin = ul.css('margin-left');
			var ul_margin_num = parseInt(ul_margin);
			if (ul_margin_num > -430) {
				ul.css('margin-left', ul_margin_num - 150 + 'px');
			}
		})
		youjiantou.click(function() {
			var ul_margin = ul.css('margin-left');
			var ul_margin_num = parseInt(ul_margin);
			if (ul_margin_num < 0) {
				ul.css('margin-left', ul_margin_num + 150 + 'px');
			}

		})
	})
</script>
</html>
