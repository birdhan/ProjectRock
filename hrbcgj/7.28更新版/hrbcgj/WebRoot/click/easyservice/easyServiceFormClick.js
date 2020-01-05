$().ready(function() {
	$("#easyServiceForm").validate({
		rules: {//验证规则
			name:{required:true,normalText:true,maxlength:50,normalText:true},
			logopic:{required:true,maxlength:100,normalText:true},
			linkurl:{required:true,url:true,maxlength:1000,normalText:true}
		},
		messages:{//验证消息内容
			name:{required:'服务名称不能为空',maxlength:'最大长度不能超过50'},
			logopic:{required:'图标不能为空',maxlength:'最大长度不能超过100'},
			linkurl:{required:'外部链接不能为空',maxlength:'最大长度不能超过1000'}
		}
	})
});
