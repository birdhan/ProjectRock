<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：文章详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>文章详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>文章标题：</th>
			<td align="left" class="formTable_td">
				${article.name}
			</td>
		</tr>
		<tr>
			<th>作者：</th>
			<td align="left" class="formTable_td">
				${article.author}
			</td>
		</tr>
		<tr>
			<th>所属栏目：</th>
			<td align="left" class="formTable_td">
				<id2name:commonId2Name whereCol="id" whereColValue="${article.sectionid}" tableName="service_section" selCol="name"/>
			</td>
		</tr>
		<tr>
			<th>创建时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${article.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th>封面图片：</th>
			<td align="left" class="formTable_td">
				<c:choose>
					<c:when test="${article.cover != ''}">
						<img src="${ctx}/uploadpic/getPic.do?id=${article.cover}" style="width:232px;height:162px;"/>
					</c:when>
					<c:otherwise>
						未上传封面
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>是否显示：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="article" dictValue="${article.isshow}" property="isshow"/>
			</td>
		</tr>
		<tr>
			<th>内容：</th>
			<td align="left" class="formTable_td">
				${article.contentvalue}
			</td>
		</tr>
		<tr>
			<th>视频文件：</th>
			<td align="left" class="formTable_td">
				<cloud:uploadAttachment modelName="article" isEdit="true" value="${article.videoid}" 
						hiddenId="videoid" attachmentNums="1" attachmentType="avi,AVI,mp4,MP4,rmvb,RMVB,wmv,WMV,mov,MOV" attachmentSize="${3*1024*1024}"/>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/article/searchArticle.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
