<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/article/articleFormClick.js"></script> <!-- js事件文件 -->
		<script type="text/javascript" src="${ctx}/jscomponents/layer/layer.js"></script>
		<script src="${ctx}/jscomponents/basefunction/basefun.js" type="text/javascript"></script>
	</head>

<body>
<div class="right_title"><p>位置：文章表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/article/saveArticle.do" method="post" name="articleForm" id="articleForm">
		<input type="hidden" id="id" name="id" value="${article.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>文章表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>文章标题：</th>
				<td>
					<input name="name" type="text" id="name" value="${article.name}" autocomplete="off" style="width:250px;"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>所属栏目：</th>
				<td>
					<select id="sectionid" name="sectionid">
						<option value="root">根级栏目</option>
						<c:forEach items="${sectionList}" var="sec">
							<option value="${sec.id}" <c:if test="${article.sectionid eq sec.id}">selected="selected"</c:if>>${sec.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>作者：</th>
				<td>
					<input name="author" type="text" id="author" value="${article.author}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>创建时间：</th>
				<td>
					<cloud:inputDate497 value="${article.createtime}" property="createtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>封面图片：</th>
				<td>
					<cloud:uploadPic imgId="cover" picFileId="${article.cover}"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>是否显示：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${article.isshow}" property="isshow" moduleName="article"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>内容：</th>
				<td>
					<cloud:htmleditor value="${article.contentvalue}" property="contentvalue"/>
				</td>
			</tr>
			<tr>
				<th>视频文件：</th>
				<td>
					<cloud:uploadAttachment modelName="article" isEdit="true" value="${article.videoid}" 
						hiddenId="videoid" attachmentNums="1" attachmentType="avi,AVI,mp4,MP4,rmvb,RMVB,wmv,WMV,mov,MOV" attachmentSize="${3*1024*1024}"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/article/searchArticle.do')"/>
					<input type="reset" value="重置" class="btn"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
