$().ready(function() {
	$("#unitForm").validate({
		rules: {//验证规则
			weburl:{required:false,url:true,maxlength:50,normalText:true},
			picurl:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			weburl:{required:'网站的连接不能为空',maxlength:'最大长度不能超过50'},
			picurl:{required:'图片的连接不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
