<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<!-- JQuery -->
	<script src="${ctx }/javascript/jquery/jquery-1.9.0.js"></script>
	<script src="${ctx}/jscomponents/map/map.js"></script>
	<script src="${ctx}/jscomponents/basefunction/basefun.js"></script>
	<script src="${ctx}/jscomponents/basefunction/ajax/baseAjax.js"></script>
    <script src="${ctx}/jscomponents/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/jscomponents/webuploader/webuploader.min.js"></script>
    
    <!-- layer -->
	<link rel="stylesheet" href="${ctx}/jscomponents/layer/css/layui.css">
	<link href="${ctx}/jscomponents/webuploader/webuploader.css" type="text/css" rel="stylesheet"/>
    
    <style type="text/css">
    body {background-color: #F7F7F7;}
    * {font-family: "Microsoft YaHei"}
    .layui-table tr th {
		background-color: #F7F7F7;
		width:150px;
	}
	
	.layui-table tr td {
		background-color: #FCFCFC;
	}
	input[type='checkbox'] {
		margin-left: 5px;
	}
	input[disabled], select[disabled], textarea[disabled], input[readonly], select[readonly], textarea[readonly] {
	    cursor: not-allowed;
	    background-color: #eee;
	}
	.layui-progress {
	    position: relative;
	    height: 6px;
	    border-radius: 20px;
	    background-color: #e2e2e2;
	}
	.layui-progress-text {
	    position: relative;
	    top: -20px;
	    line-height: 18px;
	    font-size: 12px;
	    color: #666;
	}
	.layui-progress-bar {
	    position: absolute;
	    left: 0;
	    top: 0;
	    width: 0;
	    max-width: 100%;
	    height: 6px;
	    border-radius: 20px;
	    text-align: right;
	    background-color: #5FB878;
	    transition: all .3s;
	    -webkit-transition: all .3s;
	}
	.layui-bg-blue {
	    background-color: #1E9FFF!important;
	}
	.layui-bg-green {
	    background-color: #5FB878!important;
	}
    </style>

  </head>
  
<body style="padding: 3px;padding-top: 2px;">
	<!-- 模块名称 -->
	<input type="hidden" id="funmodulename" value="${funmodulename}"/>
	<input type="hidden" id="hiddenId" value="${hiddenId}"/>
	<input type="hidden" id="fileSizeLimit" value="${fileSizeLimit}"/>
	
	<table class="layui-table" style="margin-top: 2px;">
		<tr>
			<th colspan="8">
				操作小贴士：<br/>
				1. 本附件窗口请至少上传一个附件文件。<br/>
				2. 允许上传格式为 <font color="red"><c:choose><c:when test="${attachmentType eq '*'}">全部类型</c:when><c:otherwise>${attachmentType}</c:otherwise></c:choose> </font>。<br/>
				3. 每个文件不得超过 <font color="red">${attachmentSize}</font>。<br/>
				4. 目前此控件还有好多功能未完善，待后续完善。
			</th>
		</tr>
  		<tr>
  			<th style="width:50px;">序号</th>
  			<th colspan="2">选择文件/原文件名</th>
  			<th>文件大小</th>
  			<th>服务器文件名</th>
  			<th>上传时间</th>
  			<th style="width:200px;">上传进度</th>
  			<th style="width:80px;">操作</th>
  		</tr>
  		
  		<tbody id="attachmentMainTableTBody">
  		<c:forEach var="attachment" items="${attachList}" varStatus="status">
  		<tr>
  			<td>
  				${status.count}
  				<input type="hidden" id="attachid${status.index}" value="${attachment.id}"/>
  			</td>
  			<td style="width: 200px;">
  				<input type="text" class="layui-input" id="attachment${status.index}" readonly="readonly" style="color:#666666;" value="${attachment.orgfilename}"/>
  			</td>
  			<td style="width: 100px;">
  				<div class="btns">
			        <div id="picker${status.index}">选择文件</div>
			    </div>
  			</td>
  			<td>
  				<span id="filesizeSpan${status.index}">${attachment.filesize}</span>
  			</td>
  			<td>
  				<span id="serverfilenameSpan${status.index}">${attachment.serverfilename}</span>
  			</td>
  			<td>
  				<span id="uploadtimeSpan${status.index}"><fmt:formatDate value="${attachment.uploadtime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
  			</td>
  			<td>
  				<c:choose>
  					<c:when test="${fn:length(attachment.serverfilename) != 0}">
  						<div id="uploadProgressDiv${status.index}" class="layui-progress" lay-showpercent="true" style="margin-top: 8px;">
				        	<div class="layui-progress-bar layui-bg-breen" id="progressPercentDiv${status.index}" lay-percent="100%" style="width: 100%;">
				        		<span class="layui-progress-text">已成功上传</span>
				        	</div>
				      	</div>
  					</c:when>
  					<c:otherwise>
  						<div id="uploadProgressDiv${status.index}" class="layui-progress" lay-showpercent="true" style="margin-top: 8px;display: none;">
				        	<div class="layui-progress-bar layui-bg-blue" id="progressPercentDiv${status.index}" lay-percent="0%" style="width: 0%;">
				        		<span class="layui-progress-text">0%</span>
				        	</div>
				      	</div>
  					</c:otherwise>
  				</c:choose>
  			</td>
  			<td>
  				<i class="layui-icon" style="cursor:pointer; font-size: 20px; color: #FF5722;" title="移除附件" onclick="removeFile(${status.index})">&#xe640;</i>
  				<!-- <i class="layui-icon" style="cursor:pointer; font-size: 20px; color: #FF5722; margin-left: 10px;" title="暂停上传"><span class="fa fa-pause"></span></i>
  				<i class="layui-icon" style="cursor:pointer; font-size: 20px; color: #FF5722; margin-left: 10px;" title="继续上传"><span class="fa fa-play"></span></i> -->
  			</td>
  		</tr>
  		</c:forEach>
  		</tbody>
  		
  	</table>
  	
  	<table>
  		<tr>
  			<td>
  				<button  class="layui-btn layui-btn-normal" onclick="closeCurWin()">关闭窗口</button>
  				<!-- <button  class="layui-btn layui-btn-danger" onclick="pauseAll()">全部暂停</button>
  				<button  class="layui-btn" onclick="continueUploadAll()">全部继续上传</button> -->
  				<!-- <button  class="layui-btn layui-btn-danger" onclick="">移除附件</button> -->
  			</td>
  		</tr>
  	</table>
</body>

<script>
$(function(){
	initAttachmentInputs();
});

var attachInputsArr = new Array();
function initAttachmentInputs() {
	var attachments = $("input[id^='attachment']");						// 找到所有控件
	var fileSizeLimit = $("#fileSizeLimit").val();						// 文件限制容量大小
	for(var a=0;a<attachments.length;a++) {								// 初始遍历所有上传控件
		var index = attachments[a].id.replace("attachment" , "");		// 索引
		
		var uploader = WebUploader.create({
			fileSizeLimit: fileSizeLimit,
			auto: true,
		    swf:  getRootPath() + "/jscomponents/webuploader/Uploader.swf",  
		    fileNumLimit : 999,
		    server: getRootPath() + "/attachment/uploadFile.action",  
		    compress: false,
		    accept: [{
		        title: "文档",
		        extensions: '${attachmentType}'
		    }],
		    pick: "#picker" + index
		});
		
		// 当文件被加入队列之前触发，此事件的handler返回值为false，则此文件不会被添加进入队列
		uploader.on("beforeFileQueued" , function(file) {
			var index = file.source._refer[0].id.replace("picker" , "");
			updateProgress(true , 0 , index);						// 将进度条初始化恢复最初原始状态
			packageParams(index);									// 拼装参数
		});
		
		// 当文件被加入队列以后触发
		uploader.on("fileQueued" , function(file) {
			var index = file.source._refer[0].id.replace("picker" , "");
			var fileSize = bytesToSize(parseInt(file.size));		// 文件大小
			$("#attachment" + index).val(file.name);				// 附件文件名
			$("#filesizeSpan" + index).html(fileSize);
			$("#serverfilenameSpan" + index).html("文件上传中..");
		});
		
		/*
		 当validate不通过时，会以派送错误事件的形式通知调用者。通过upload.on('error', handler)可以捕获到此类错误，目前有以下错误会在特定的情况下派送错来。
		 Q_EXCEED_NUM_LIMIT 在设置了fileNumLimit且尝试给uploader添加的文件数量超出这个值时派送。
		 Q_EXCEED_SIZE_LIMIT 在设置了Q_EXCEED_SIZE_LIMIT且尝试给uploader添加的文件总大小超出这个值时派送。
		 Q_TYPE_DENIED 当文件类型不满足时触发。。
		 */
		uploader.on("error" , function(type) {
			if(type == "Q_TYPE_DENIED") layer.msg("文件上传格式不正确！",{icon: 5});
			if(type == "Q_EXCEED_SIZE_LIMIT") layer.msg("上传文件不得超过最大限制！",{icon: 5});
			if(type == "Q_EXCEED_NUM_LIMIT") layer.msg("上传文件不得超过最大数量！",{icon: 5});
		});
		
		// 当文件上传出错时触发
		uploader.on("uploadError" , function(file , reason) {
			//uploader.reset();
		});
		
		// 当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。如果此事件handler返回值为false, 则此文件将派送server类型的uploadError事件
		uploader.on("uploadAccept" , function(object , ret) {
			var file = object.file;
			var index = file.source._refer[0].id.replace("picker" , "");
			
			var fileSize = bytesToSize(parseInt(file.size));				// 上传附件大小
			$("#filesizeSpan" + index).html(fileSize);
			$("#serverfilenameSpan" + index).html(ret.serverfilename);
			$("#uploadtimeSpan" + index).html(ret.uploadtime);
			
			$("#progressPercentDiv" + index).removeClass("layui-bg-blue");	// 移除蓝色样式
			$("#progressPercentDiv" + index).addClass("layui-bg-green");	// 追加绿色成功样式
			
			$("#attachid" + index).val(ret.attachmentid);
			
			reShowAttachFiles();
		});
		
		// 不管成功或者失败，文件上传完成时触发
		uploader.on("uploadComplete" , function(file) {
			//uploader.reset();
		});
		
		// 当所有文件上传结束时触发
		uploader.on("uploadFinished" , function(file) {
			//uploader.reset();
		});
		
		// 当开始上传流程时触发
		uploader.on("startUpload" , function() {
			
		});
		
		// 当开始上传流程暂停时触发
		uploader.on("stopUpload" , function() {
			if(uploader.isInProgress()) {
				var index = uploader.options.pick.replace("#picker" , "");
				layer.msg("啊噢！不好意思，刚才溜神了，第" + (parseInt(index) + 1) + "为您重新传，请稍候一下下！！！");
				uploader.upload();
			}
		});
		
		// 上传进度
		uploader.on("uploadProgress" , function(file , percentage) {
			var index = file.source._refer[0].id.replace("picker" , "");
			updateProgress(false , percentage , index);
		});
		
		attachInputsArr.push(uploader);
	}
}

/**
 * 更新进度条
 */
function updateProgress(isInit , percentage , idIndex) {
	if(isInit) {												// 进度条重新初始化
		$("#progressPercentDiv" + idIndex).removeClass("layui-bg-green");	// 将绿色状态变为蓝色
		$("#progressPercentDiv" + idIndex).addClass("layui-bg-blue");
		percentage = 0;
	}
	$("#uploadProgressDiv" + idIndex).css("display" , "");
	var proDiv = $("#uploadProgressDiv" + idIndex).children("div")[0];
	proDiv.setAttribute("lay-percent" , parseInt(percentage*100));
	proDiv.style.width = parseInt(percentage*100) + "%";
	$(proDiv).children()[0].innerHTML = parseInt(percentage*100) + "%";
}

/**
 * 拼装参数
 */
function packageParams(index) {
	if(attachInputsArr.length == 0) return;
	
	var param = new Object();
	var funmodulename = $("#funmodulename").val();
	param.funmodulename = funmodulename;
	var attachmentid = $("#attachid" + index).val();
	param.attachmentid = attachmentid;
	
	attachInputsArr[index].options.formData = param;
}

/**
 * 全部暂停
 */
function pauseAll() {
	for(var i=0;i<attachInputsArr.length;i++) {
		attachInputsArr[i].stop(true);
	}
}

/**
 * 全部取消停止
 */
function cancelUploadAll() {
	for(var i=0;i<attachInputsArr.length;i++) {
		attachInputsArr[i].cancelFile();
	}
}

/**
 * 继续上传
 */
function continueUploadAll() {
	for(var i=0;i<attachInputsArr.length;i++) {
		attachInputsArr[i].upload();
	}
}

/**
 * 转换容量
 */
function bytesToSize(bytes) {
    if (bytes === 0) return '0 B';
    var k = 1024, 
        sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
        i = Math.floor(Math.log(bytes) / Math.log(k));
 
   return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
}

/**
 * 关闭当前窗口
 */
function closeCurWin() {
	if(checkHasFileUploading()) {
		layer.confirm("当前有文件正在上传，是否强制关闭？", 
			{
				btn: ["确定","取消"], 
				icon: 3
			}, 
			function(){										// 删除相位后的逻辑
				parent.closeLayer();
			}
		);
	} else {
		parent.closeLayer();
	}
}

/**
 * 检测当前是否有正在上传的文件
 */
function checkHasFileUploading() {
	for(var a=0;a<attachInputsArr.length;a++) {
		if(attachInputsArr[a].isInProgress()) {
			return true;
		}
	}
	return false;
}

/**
 * 移除文件
 */
function removeFile(index) {
	var attachid = $("#attachid" + index).val();			// 附件文件数据的ID值
	if(attachid.length != 0) {								// 附件数据的ID值
		var confirmIndex = layer.confirm("您确定要删除当前附件吗？", 
			{
				btn: ["确定","取消"], 
				icon: 3
			}, 
			function() {									// 删除相位后的逻辑
				var uploadObj = getUploadObjByIndex(index);
				uploadObj.reset();
				uploadObj.stop(true);
				
				var delAttachmentUrl = getRootPath() + "/attachment/delAttachmentFileById.do";
				var param = new Object();
				param.id = $("#attachid" + index).val();
				$.ajaxJSON(delAttachmentUrl , param , function(data){	// 删除后台文件和数据
					layer.msg(data.msg);
				},function(e){});
				
				$("#attachid" + index).val("");
				$("#attachment" + index).val("");
				$("#filesizeSpan" + index).html("");
				$("#serverfilenameSpan" + index).html("");
				$("#uploadtimeSpan" + index).html("");
				$("#uploadProgressDiv" + index).css("display" , "none");
				layer.close(confirmIndex);
				
				reShowAttachFiles();
			},function(e) {									// 取消事件
				
			}
		);
	} else {
		$("#attachid" + index).val("");
		$("#attachment" + index).val("");
		$("#filesizeSpan" + index).html("");
		$("#serverfilenameSpan" + index).html("");
		$("#uploadtimeSpan" + index).html("");
		$("#uploadProgressDiv" + index).css("display" , "none");
		
		layer.msg("当前没有可删除的附件");
	}
}

/**
 * 通过索引获取上传控件对象
 */
function getUploadObjByIndex(index) {
	for(var a=0;a<attachInputsArr.length;a++) {
		if(index == a) {
			return attachInputsArr[a];
		}
	}
	return null;
}

/**
 * 回显父页面文件信息
 */
function reShowAttachFiles() {
	var hiddenId = $("#hiddenId").val();
	var tbodyId = hiddenId + "TBody";
	parent.$("#" + tbodyId).html("");
	var hiddenObj = parent.$("#" + hiddenId);
	hiddenObj.val("");
	
	var trs = $("#attachmentMainTableTBody").children();
	for(var i=0;i<trs.length;i++) {
		var attachid = $("#attachid" + i).val();
		if(attachid.length == 0) continue;
		var attachmentOrgName = $("#attachment" + i).val();			// 原附件名称
		var filesize = $("#filesizeSpan" + i).html();				// 文件大小
		var uploadtime = $("#uploadtimeSpan" + i).html();			// 上传时间" + attachmentOrgName + "
		parent.$("#" + tbodyId).append("<tr><td>" + (i+1) + "</td><td><a href='" + getRootPath() + "/attachment/downLoadAttachment?id=" + attachid + "'>" + attachmentOrgName + "</a></td><td>" + filesize + "</td><td>" + uploadtime + "</td></tr>");
		
		if(hiddenObj.val().length == 0) hiddenObj.val(attachid);
		else hiddenObj.val(hiddenObj.val() + "," + attachid);
	}
}
</script>
</html>