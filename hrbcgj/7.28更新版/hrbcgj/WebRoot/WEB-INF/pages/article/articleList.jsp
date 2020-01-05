<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/article/articleListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：文章列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="articleForm" name="articleForm" action="${ctx}/article/searchArticle.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>文章标题：</th>
						<td>
							<input name="name" type="text" id="name" value="${article.name}" autocomplete="off"/>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<th>所属栏目：</th>
						<td>
							<select id="sectionid" name="sectionid">
								<option value="">全部栏目</option>
								<option value="root">根级栏目</option>
								<c:forEach items="${sectionList}" var="sec">
									<option value="${sec.id}" <c:if test="${article.sectionid eq sec.id}">selected="selected"</c:if>>${sec.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/article/searchArticle.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/article/searchArticle.do" id="articleList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${articleList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${articleList_rowNum}
				</display:column>

				<display:column property="name" sortable="true" title="文章标题" style="text-align:center;" />

				<display:column property="author" sortable="true" title="作者" style="text-align:center;" />

				<display:column sortable="true" title="所属栏目" style="text-align:center;" >
					<id2name:commonId2Name whereCol="id" whereColValue="${articleList.sectionid}" tableName="service_section" selCol="name"/>
				</display:column>
				
				<display:column sortable="true" title="创建时间" style="text-align:center;" >
					<fmt:formatDate value="${articleList.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>

				<display:column sortable="true" title="是否显示" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="article" dictValue="${articleList.isshow}" property="isshow"/>
				</display:column>

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/article/editArticle.do?id=${articleList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/article/delArticleById.do?id=${articleList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/article/detailArticle.do?id=${articleList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/article/addArticle.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/article/delArticleByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
