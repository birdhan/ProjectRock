/**
 * 附件上传插件
 * au: eoms-wudan
 * @type 
 */

var swfu;
var fileQueue = [];
var p_attachmentFileObject = [];
var p_attachmentRoot = null;
var p_attachmentViewRoot = null;
var p_deleteParams = [];
var p_swf_att_limit;
var uploadPath ;
/**
 * 附件对象
 */
function portal_attachment(){
	this.id = arguments[0];
	this.name = arguments[1];
	this.path = arguments[2];
}

//初始化 swf
function initSwfupload(url,sizeLimit,types,typsdesc,uploadLimit,nameSpace,path){
		this.uploadPath = path;
		p_swf_att_limit = uploadLimit;
		//初始化界面
		init(uploadLimit,sizeLimit,nameSpace);
		//初始化 swfupload
		var settings={
			/**
			 * 基本开始
			 * @type 
			 */
			flash_url : path+"/click/attachment/swfupload.swf",         //要加载的flash  url
			file_post_name:"filedata",                           //文件名 这里用的是默认值
			upload_url: url,                                     //处理文件上传的servlet
			post_params: {"name" : "test"},                      //传入的参数
			file_size_limit : sizeLimit,  	                     //每个文件的大小限制
			file_types : types,                                  //附件类型
			file_types_description : typsdesc,                   //没发现有什么用  随便给个值
			file_upload_limit : uploadLimit,                     //一次上传的文件个数  最多上传的个数
			file_queue_limit : 0,                                //队列的大小限制
			/**
			 * 基本设置结束
			 * @type 
			 */
			
			
			/**
			 * 事件触发始化开始
			 * @type 
			 */
			file_queue_error_handler : fileQueueError,			 //队列出错
			file_dialog_complete_handler : fileDialogComplete,   //选择好文件后提交
			file_queued_handler : fileQueued,
			swfupload_load_failed_handler : swfUploadLoadFailed, //swf 加载失败
			swfupload_loaded_handler : swfUploadLoaded,          //swf 加载完成
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,
			/**
			 * 事件触发初始化结束
			 * @type 
			 */
			
			/**
			 * 控件及按钮的初始化开始
			 * @type 
			 */
			button_image_url : path+"/images/attachment/select-files.png",
			button_placeholder_id : "buttonPlaceholder",
			button_width: 100,
			button_height: 23,
			button_text : '<span style="display:block;"></span>',
			custom_settings : {
				fileContainer_Id    : "fileContainer",  	//图片的容器id
				fileTable_Id		: "fileTable",
				btnUpload_ID		: "btnUpload",          //上传按钮
				clearAll_Id			: "clearAll", 			//全部清空
			   	errorMsg_Id			: "errorMsg",  			//错误信息
			  	errorMsg_fadeOutTime: 2000  				//错误信息淡出的时间
			},
			debug: false,                                   //是否显示调试窗口
			auto_upload:false                               //阻止自动提交
			/**
			 * 控件及按钮的初始化结束
			 * @type 
			 */
		};
		swfu = new SWFUpload(settings);
}
//初始化 界面
function init(uploadLimit,sizeLimit,nameSpace){
	if($(".ke-dialog-upload").length>0){
		$(".ke-dialog-upload").remove();
	}
	var uploadbody='' +
		 '<div id="uploadBody" class="ke-dialog-upload">'
		+'	<div id="uploadHead" class="ke-dialog-upload-head">'
		+'  <div style="float: left">附件上传</div><div id="iconClose" class="" title=""></div><div style="clear: both"></div>'
		+'	</div>'
		+'	<!--content-->'
		+'	<div class="ke-dialog-content">'
		+'		<div class="ke-dialog-content-head">'
		+'		  <div id="errorMsg" class="ke-dialog-content-error"></div>'
		+'		  <div id="swfbuttonParent" class="ke-swfupload-button"><span id="buttonPlaceholder" /></div>'
		+'		  <div class="ke-inline-block ke-swfupload-desc">允许同时上传&nbsp;<font color="red">'+uploadLimit+'</font>&nbsp;个附件，单个附件不超过<font color="red">'+sizeLimit+'</font></div>'
		+'			<div class="ke-button-common ke-button-outer ke-swfupload-startupload"><input id="btnUpload" type="button" class="ke-button-common ke-button" value="开始上传"></div>'
		+'			<div style="clear: both;"></div>'		
		+' 		</div>'
		+'		<div id="fileContainer" class="ke-swfupload-body">'
		+'			<table id="fileTable" border="1px" cellspacing="0px" class="ke-swfupload-table"><tr class="head"><td width="30%" align="center">文件名</td><td width="15%" align="center">大小</td><td width="30%" align="center">进度</td><td width="15%" align="center">状态</td><td width="10%" align="center">操作</td></tr></table>'
		+'		</div>'
		+'	</div>'
		+'	<!--bottom-->'
		+'	<div class="ke-dialog-footer">'
		+'		<span class="ke-button-common ke-button-outer" title="全部删除"><input id="clearAll" class="ke-button-common ke-button" type="button" value="全部删除"></span>'
		+'		<span class="ke-button-common ke-button-outer" title="关闭"><input id="btncancel" class="ke-button-common ke-button" type="button" value="关闭"></span>'
		+'  </div>'
		+'</div>'
		+' <div id="swfupload-mask" style="display: block; width: 100%; height: 100%; position: absolute; left: 0px; top: 0px; z-index: 811212;" class="ke-dialog-mask"></div>';
	$('body').append(uploadbody);
	if(nameSpace == "" || nameSpace == null || nameSpace.length == 0){
		$("#btncancel").bind("click",{uploadBodyId:'uploadBody'},on_attachment_close);
	}else{
		$("#btncancel").bind("click",{uploadBodyId:'uploadBody'},eval(nameSpace));
	}
	
	//$("#iconClose").bind("click",{uploadBodyId:'uploadBody'},eval(nameSpace));
	var dragging = false;
	var iX, iY;
	$("#uploadHead").mousedown(function(e) {
		dragging = true;
		iX = e.clientX - this.offsetParent.offsetLeft;
		iY = e.clientY - this.offsetParent.offsetTop;
		return false;
	});
	document.onmousemove = function(e) {
		if (dragging) {
			var e = e || window.event;
			var oX = e.clientX - iX;
			var oY = e.clientY - iY;
			$("#uploadBody").css({"left":oX<0?0:oX + "px", "top":oY<0?0:oY + "px"});
			return false;
		}
	};
	$(document).mouseup(function(e) {
		dragging = false;
		e.cancelBubble = true;
	});
}
 
// 关闭 /取消 按钮 事件      已完成
function iconClose(event){
	if(typeof swfu != 'undefined' || swfu!=null){
		swfu.destroy();
	}
	$("#"+event.data.uploadBodyId).remove();
	$(".ke-dialog-mask").remove();
}
//外部关闭函数
function on_attachment_close(event){
	iconClose(event);
	
}

// 添加错误信息      已完成
function addErrorMsg(message,isFadeOut){
	$("#"+swfu.customSettings.errorMsg_Id).empty().html(message);
	if(isFadeOut){
		setTimeout(function () {
			$("#"+swfu.customSettings.errorMsg_Id).children().fadeOut(1000);
		},parseInt(swfu.customSettings.errorMsg_fadeOutTime));
	}
}

//swf 加载失败      已完成
function swfUploadLoadFailed() {
	//clearTimeout(this.customSettings.loadingTimeout);
	var message='<div id="divAlternateContent" style="background-color: #FFFF66; text-align:center;">'
			+'<a href="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" target="_blank"><font color="red">请安装或者升级您的Flash插件!</font></a></div>';
	addErrorMsg(message,false);
}

//swf 加载 完成    未测试
function swfUploadLoaded() {
	//var self = this;
	//clearTimeout(this.customSettings.loadingTimeout);
	addErrorMsg("",false);
	$("#"+this.customSettings.btnUpload_ID).click(function (){swfu.startUpload()});
}

//每次被加入 到列队中 已完成
function fileQueued(file){
	fileQueue.push(file.id);
	/*this.customSettings.queue = this.customSettings.queue || new Array();
        while (this.customSettings.queue.length > 0) {
            this.cancelUpload(this.customSettings.queue.pop(), false);
        }
        this.customSettings.queue.push(file.id);*/

	addReadyFileInfo(file,"等待上传");
}

// 选择文件后 添加到 操作层中   已完成
function addReadyFileInfo(file,message){
	
	var progressbar = '<div class="ke-progressbar">'
			+'          		 <div class="ke-progressbar-bar">'
            +'               	 	<div id="ke-progressbar-'+file.id+'" class="ke-progressbar-bar-inner"></div>'
            +'           		 </div>'
            +'            		<div id="ke-percent-'+file.id+'" class="ke-progressbar-percent">0%</div>'
            +'        </div>';
    var num = file.id;
    num = num.substring(num.length-1,num.length);
    if(num%2 == 0){
    	var fileItem = '<tr id="swf-file-'+file.id+'" class="ke-fileItem-odd"><td>&nbsp'+file.name+'&nbsp</td><td align="center">'+getFileSize(file)+'</td><td>'+progressbar+'</td><td id="ke-message-'+file.id+'" align="center">'+message+'</td><td align="center"><a id="file-del'+file.id+'" class="file-del" >删除</a><input type="hidden" id="file-hidden'+file.id+'" /></td></tr>';
    }else{
    	var fileItem = '<tr id="swf-file-'+file.id+'" class="ke-fileItem-alt"><td>&nbsp'+file.name+'&nbsp</td><td align="center">'+getFileSize(file)+'</td><td>'+progressbar+'</td><td id="ke-message-'+file.id+'" align="center">'+message+'</td><td align="center"><a id="file-del'+file.id+'" class="file-del" >删除</a><input type="hidden" id="file-hidden'+file.id+'" /></td></tr>';
    }
  	   	
	$("#"+swfu.customSettings.fileTable_Id).append(fileItem);
	$("#file-del"+file.id).click( 
			function(){
				var id = this.id;
				var hiddenId = "file-hidden"+id.substring(8,id.length);
				if(document.getElementById(hiddenId).value.length == 0){
					$("#swf-file-"+file.id).remove();
				swfu.cancelUpload(file.id,false);
				swfu.file_upload_limit = swfu.file_upload_limit + 1;
				//alert(swfu.file_upload_limit);
				
				}
			}
		);
}

//全部清空  已完成
function clearUpload(){
	for(var i=0;i<fileQueue.length;i++){
		swfu.cancelUpload(fileQueue[i],false);
		//swfu.file_upload_limit = swfu.file_upload_limit + 1;
				//alert(swfu.file_upload_limit);
	}
	$(".ke-fileItem-odd").remove();
	$(".ke-fileItem-alt").remove();
	var stats = swfu.getStats();
				stats.successful_uploads = 0;
				//alert(stats.successful_uploads);
				swfu.setStats(stats);
}


//文件对话框选择完成  已完成
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesQueued > 0) {
			$("#"+this.customSettings.clearAll_Id).bind("click",clearUpload);
		}
		if(this.settings.auto_upload){//是否要上传
			this.startUpload();
		}
	} catch (ex) {
		this.debug(ex);
	}
}

//都加入列队中 的错误信息  已完成
function fileQueueError(file, errorCode, message) {
	try {
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
			//message = "<font color='red'>附件数量超过"+swfu.getSetting('file_upload_limit')+"个!</font>";
			alert("附件数量超过"+swfu.getSetting('file_upload_limit')+"个!");
			break;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			//message =  "<font color='red'>文件&nbsp"+file.name+"&nbsp超过"+swfu.getSetting('file_size_limit')+"!</font>";
			alert("文件 "+file.name+" 超过"+swfu.getSetting('file_size_limit')+"!");
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			//message = "<font color='red'>文件&nbsp"+file.name+"&nbsp为0字节!</font>";
			alert("文件 "+file.name+" 为0字节!");
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			//message = "<font color='red'>文件&nbsp"+file.name+"&nbsp格式不符合要求!</font>";
			alert("文件 "+file.name+" 格式不符合要求!");
			break;
		default:
			//message="<font color='red'>选择文件发生错误!"+"</font>";
			alert(message);
			return;
		}
		
	} catch (ex) {
		//this.debug(ex);
	}
}

//格式化文件大小  已完成
function getFileSize(file){
	var fomartSizeKB = (file.size/1024).toFixed(1);
	var fomartSizeMB = 0;
	var fomartSizeGB = 0;
	if(fomartSizeKB < 1024){
		return fomartSizeKB+" KB";
	}else if(fomartSizeKB > 1024 && fomartSizeKB < (1024*1024)){
		fomartSizeMB = (fomartSizeKB/1024).toFixed(1);
		return fomartSizeMB+" MB";
	}else if(fomartSizeKB > 1024*1024 && fomartSizeKB < (1024*1024*1024)){
		fomartSizeGB = (fomartSizeKB/(1024*1024)).toFixed(1);
		return fomartSizeGB+" GB";
	}else{
		return fomartSizeKB+" KB";
	}
}

// 单个 文件上传完    已完成
function uploadComplete(file) { 
	
	
	try {
		if (this.getStats().files_queued > 0) {
			this.startUpload();
		}
	} catch (ex) {
		this.debug(ex);
	}
	
}

//淡入效果  已完成
function fadeIn(element, opacity) {
	var reduceOpacityBy = 5;
	var rate = 30;	// 15 fps


	if (opacity < 100) {
		opacity += reduceOpacityBy;
		if (opacity > 100) {
			opacity = 100;
		}

		if (element.filters) {
			try {
				element.filters.item("DXImageTransform.Microsoft.Alpha").opacity = opacity;
			} catch (e) {
				element.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=' + opacity + ')';
			}
		} else {
			element.style.opacity = opacity / 100;
		}
	}

	if (opacity < 100) {
		setTimeout(function () {
			fadeIn(element, opacity);
		}, rate);
	}
}

// 上传的进度  已完成
function uploadProgress(file, bytesLoaded) {
	$("#ke-message-"+file.id).html("正在上传...");
	//$("#file-del"+file.id).remove();
	try {
		var percent = Math.ceil((bytesLoaded / file.size) * 100);
		if(percent>100){
			percent=100;
		}
		if(percent == 100){
			$("#ke-message-"+file.id).html("上传完成");
		}
		var progress = $("#ke-progressbar-"+file.id);
		var _percent = $("#ke-percent-"+file.id);
		progress.css("width",percent + "%");
		_percent.html(percent + "%");
		
	}catch (ex) {
		this.debug(ex);
	}
	
}
// 上传到服务器后 的放回信息
function uploadSuccess(file, serverData) {
	
	try {
		var msg = $("#ke-message-"+file.id);
		
		var data = eval("(" + serverData + ")");
		
		if (data.status == 0 || data.status == "0") {
			msg.html("上传完成");
			var _att = new portal_attachment(data.id,data.name,data.path);
			p_attachmentFileObject.push(_att);
			p_attachmentRoot = data.root;
			p_attachmentViewRoot = data.viewRoot;
			p_deleteParams.push(data.id+","+data.path);
			$("#file-hidden"+file.id)[0].value = data.id + "," + data.path;
			$("#file-del"+file.id)[0].onclick=function(){
				var id = this.id;
				var hiddenId = "file-hidden"+id.substring(8,id.length);
				var idStr = data.id + "," + data.path;
				var params =[];
				params.push(idStr);
				var idArr = idStr.split(",");
				for(var i=0; i<p_attachmentFileObject.length; i++){
					if(idArr[0] == p_attachmentFileObject[i].id){
						p_attachmentFileObject.splice(i,1);
					}
				}
				$.ajax({
					type : 'post',
					url : uploadPath+'/DeleteAttachmentServlet',
					data : {
					 	params : params
					}
				})
				$("#swf-file-"+id.substring(8,id.length)).remove();
				//这里是为了解决上传限制的问题
				var stats = swfu.getStats();
				stats.successful_uploads = stats.successful_uploads -1;
				swfu.setStats(stats);
				//这里是为了解决上传限制的问题
			}
			$("#clearAll")[0].onclick=function(){
				
				$.ajax({
					type : 'post',
					url : uploadPath+'/DeleteAttachmentServlet',
					data : {
					 	params : p_deleteParams
					},
					success : function(){
						p_deleteParams = [];
						p_attachmentFileObject = [];
					}
				})
				//这里是为了解决上传限制的问题
				var stats = swfu.getStats();
				stats.successful_uploads = 0;
				alert(stats.successful_uploads);
				swfu.setStats(stats);
				//这里是为了解决上传限制的问题
			}
			return;
		}else if(data.status == 1 || data.status == "1"){
			msg.html("上传失败");
			return;
		}else if(data.status == 2 || data.status == "2"){
			msg.html("格式不对");
			return;
		}else if(data.status == 3 || data.status == "3"){
			msg.html("文件过大");
			return;
		}else if(data.status == 4 || data.status == "4"){
			msg.html("文件为空");
			return;
		}else if(data.status == 5 || data.status == "6"){
			msg.html("路径错误");
			return;
		}else{
			msg.html("上传失败");
			return;
		}
	} catch (ex) {
		this.debug(ex);
	}
}
//上传的错误
function uploadError(file, errorCode, message) {
	var message =  "<font color='red'>文件上传出错!</font>";
	var msg = $("#ke-message-"+file.id);
	var flag=false;
	try {
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
				msg.html("取消上传");
				break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
				msg.html("停止上传");
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
				msg.html("文件过大");
				break;
		default:
			addErrorMsg(message,true);
			break;
		}
		//addFileInfo(file.id,imageName);
	} catch (ex3) {
		this.debug(ex3);
	}
}


//外部启动方法
function attachment_startUpload(sizeLimit,types,uploadLimit,userId,contextPath,nameSpace,path){
		var url = path+"/FileUploadServlet";
		if(contextPath !=null && contextPath != "" && contextPath.length > 0){
			url +="?contextPath="+contextPath;
		}
		if(userId !=null && userId != "" && userId.length > 0){
			url += "&userId="+userId;
		}
		var typesdesc = "chose files";
		initSwfupload(url,sizeLimit,types,typesdesc,uploadLimit,nameSpace,path);
	}
