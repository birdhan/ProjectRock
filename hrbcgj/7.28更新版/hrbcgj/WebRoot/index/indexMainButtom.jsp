<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--新闻，公告-->
<div id="main_2">
	<div id="wangshangbanshi">
		<div class="wsbs_title">网上办事</div>
		<div class="wsbs_log_all">
			<div class="wsbs_log">
				<a href="#"><img src="${ctx }/images/index/zaixianshenban.png"><br />在线申办</a>
			</div>
			<div class="wsbs_log">
				<a href="#"><img src="${ctx }/images/index/xiazai.png"><br />表格下载</a>
			</div>
			<div class="wsbs_log">
				<a href="#"><img src="${ctx }/images/index/banshizhinan.png"><br />办事指南</a>
			</div>
			<div class="wsbs_log">
				<a href="#"><img src="${ctx }/images/index/changjianwenti.png"><br />常见问题</a>
			</div>
		</div>
	</div>
	<div id="chengguanfengcai">
		<div class="cgfc_title">城管风采</div>
		<img src="${ctx }/images/index/zuo_jiantou.png" id="youjiantou">
		<div id="cgfc_lunbo">
			<ul>
				<c:forEach items="${cgfc_articleList}" var="article">
					<li style="margin-left: 10px;cursor:pointer; background-image: url(${ctx}/uploadpic/getPic.action?id=${article.cover});" onclick="openDetailMain('${cgfc_sectionId}','${cgfc_secondSectionId }','${article.id }')">
						<a href="javascript:void(0)" ><cloud:subString end="7" begin="0" value="${article.name}"/></a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<img src="${ctx }/images/index/you_jiantou.png" id="zuojiantou">
               </div>
	<div id="toupiao">
		<div class='tabs tabs_default'>
			<ul class='horizontal' style=" height: 45px;">
				<li>
					<a href="#tab-1" style="font-size: 16px; width : 100px; height: 40px;">进行的投票</a>
				</li>
				<li>
					<a href="#tab-2" style="font-size: 16px; width : 100px; height: 40px;">完成的投票</a>
				</li>
			</ul>
			<div id='tab-1' style="width: 180px; height: 200px;" class="toupiao"> <!--进行的投票  -->
				<div id="toupiao_list" >
					<c:forEach var="it" items="${FvoteList}" >
					<li style="list-style: none;">
						<a href="javascript:void(0);" onclick="myDetail('${it.id}')" style="margin-top: 13px;">
												<c:choose>
													<c:when test="${fn:length(it.title)>6}">${fn:substring(it.title, 0, 6)  }...</c:when>
													<c:otherwise>${it.title}</c:otherwise>
												</c:choose>
									</a>
						<a class="tab_date" style="color: #b8b8b8; font-size:12px; font-family: " 宋体 ";">${fn:substring(it.startTime,0,10) }...</a>
					</li>
					</c:forEach>
				</div>
			</div>
			<div id='tab-2' style="width: 180px; height: 200px; list-style: none;"><!--完成的投票  -->
                       <div id="toupiao_list">
				<c:forEach var="it" items="${TvoteList}" >
					<li style="list-style: none;">
						<a href="javascript:void(0);" onclick="TmyDetail('${it.id}')" style="margin-top: 13px;">
												<c:choose>
													<c:when test="${fn:length(it.title)>6}">${fn:substring(it.title, 0, 6)  }...</c:when>
													<c:otherwise>${it.title}</c:otherwise>
												</c:choose>
									</a>
						<a class="tab_date" style="color: #b8b8b8; font-size: 12px; font-family: " 宋体 ";">${fn:substring(it.startTime,0,10) }...</a>
					</li>
					</c:forEach>
				
                       </div>  
			</div>
		</div>
	</div>
</div>

<script>
	function myDetail(letterId) {
		var usercuid="${sessionScope.webSiteLoginUser.id}";
		window.open("${ctx}/myDetail?sectionId=index&secondSectionId=default&letterId="+letterId+"&usercuid="+usercuid);
	}
	
	
	function TmyDetail(letterId) {
        var usercuid="${sessionScope.webSiteLoginUser.id}";
		window.open("${ctx}/TmyDetail?sectionId=index&secondSectionId=default&letterId="+letterId+"&usercuid="+usercuid);
	}
</script>