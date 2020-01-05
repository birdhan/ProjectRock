$().ready(function() {
	$("#attachmentForm").validate({
		rules: {//验证规则
			fileName:{required:true,maxlength:100,normalText:true},
			filePath:{required:true,maxlength:500,normalText:true},
			fileSize:{required:true,maxlength:30,normalText:true},
			fileType:{required:true,maxlength:30,normalText:true},
			uploadUserId:{required:true,maxlength:30,normalText:true},
			uploadTime:{required:false,maxlength:50},
			contextPath:{required:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			fileName:{required:'附件名称不能为空',maxlength:'最大长度不能超过100'},
			filePath:{required:'附件路径不能为空',maxlength:'最大长度不能超过500'},
			fileSize:{required:'附件大小不能为空',maxlength:'最大长度不能超过30'},
			fileType:{required:'附件类型不能为空',maxlength:'最大长度不能超过30'},
			uploadUserId:{required:'附件上传人不能为空',maxlength:'最大长度不能超过30'},
			uploadTime:{required:'附件上传时间不能为空',maxlength:'最大长度不能超过50'},
			contextPath:{required:'工程上下文根不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
