$().ready(function() {
	$("#excelFileForm").validate({
		rules: {//验证规则
			name:{required:true,maxlength:50,normalText:true},
			uploadtime:{required:false,maxlength:50},
			attachmentfileid:{required:false,maxlength:1000,normalText:true}
		},
		messages:{//验证消息内容
			name:{required:'文件名称不能为空',maxlength:'最大长度不能超过50'},
			uploadtime:{required:'上传时间不能为空',maxlength:'最大长度不能超过50'},
			attachmentfileid:{required:'附件不能为空',maxlength:'最大长度不能超过1000'}
		}
	})
});
