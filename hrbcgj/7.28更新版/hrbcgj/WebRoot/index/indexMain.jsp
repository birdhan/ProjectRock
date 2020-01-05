<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--新闻，公告-->
<div class="main">
	<div id="main_lunbotu">
		<div class="all" id='all'>
			<div class="screen">
				<ul>
					<c:choose>
						<c:when test="${fn:length(tpxw_articleList) >= 5}">
							<c:forEach items="${tpxw_articleList}" var="article" varStatus="status" begin="0" end="4">
								<li id="index"><img style="cursor: pointer;" onmouseover=this.stop() onmouseout=this.start() onclick="openDetailMain('${tpxw_sectionId}','${tpxw_secondSectionId }','${article.id }')" src="${ctx}/uploadpic/getPic.action?id=${article.cover}" width="480" height="330"/></li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${tpxw_articleList}" var="article" varStatus="status">
								<li id="index"><img style="cursor: pointer;" onmouseover=this.stop() onmouseout=this.start() onclick="openDetailMain('${tpxw_sectionId}','${tpxw_secondSectionId }','${article.id }')" src="${ctx}/uploadpic/getPic.action?id=${article.cover}" width="480" height="330"/></li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
				</ul>
			</div>
			<ol>
			</ol>
		</div>
	</div>
	<div class="main_text">
		<div class="container">
			<div class="row">
				<div class="twelve columns">
					<article>
						<div class='tabs tabs_default'>
							<ul class='horizontal'>
								<li>
									<a href="#tab-1">城管新闻</a>
								</li>
								<li>
									<a href="#tab-2">公示公告</a>
								</li>
								<li>
									<a href="#tab-3">图片新闻</a>
								</li>
							</ul>
							<div id='tab-1'>
							
								<c:forEach items="${cgxw_articleList}" var="article" varStatus="status" begin="0" end="7">
									<li>
										<a href="javascript:void(0)" onclick="openDetailMain('${cgxw_sectionId}','${cgxw_secondSectionId }','${article.id }')" ><cloud:subString end="18" begin="0" value="${article.name}"/></a>
										<a class="tab_date" style="color: #b8b8b8; font-size: 12px; font-family: " 宋体 ";"><fmt:formatDate value="${article.createtime}" pattern="MM-dd"/></a>
									</li>
								</c:forEach>
							</div>
							<div id='tab-2'>
								<c:forEach items="${gsggxw_articleList}" var="article" varStatus="status"  begin="0" end="7">
									<li>
										<a href="javascript:void(0)" onclick="openDetailMain('${gsggxw_sectionId}','${gsggxw_secondSectionId }','${article.id }')"><cloud:subString end="18" begin="0" value="${article.name}"/></a>
										<a class="tab_date" style="color: #b8b8b8; font-size: 12px; font-family: " 宋体 ";"><fmt:formatDate value="${article.createtime}" pattern="MM-dd"/></a>
									</li>
								</c:forEach>
							</div>
							<div id='tab-3'>
								<c:forEach items="${tpxw_articleList}" var="article" varStatus="status"  begin="0" end="7">
									<li>
										<a href="javascript:void(0)" onclick="openDetailMain('${tpxw_sectionId}','${tpxw_secondSectionId }','${article.id }')"><cloud:subString end="18" begin="0" value="${article.name}"/></a>
										<a class="tab_date" style="color: #b8b8b8; font-size: 12px; font-family: " 宋体 ";"><fmt:formatDate value="${article.createtime}" pattern="MM-dd"/></a>
									</li>
								</c:forEach>
							</div>
						</div>
					</article>
				</div>
			</div>
		</div>
	</div>
</div>