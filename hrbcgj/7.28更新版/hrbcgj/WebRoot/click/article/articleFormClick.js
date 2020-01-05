$().ready(function() {
	$("#articleForm").validate({
		rules: {//验证规则
			name:{required:true,normalText:true,maxlength:50,normalText:true},
			author:{required:true,normalText:true,maxlength:50,normalText:true},
			createtime:{required:true,maxlength:50},
			cover:{required:false,maxlength:1000,normalText:true},
			isshow:{required:true,normalText:true,maxlength:50},
			contentvalue:{required:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			name:{required:'文章标题不能为空',maxlength:'最大长度不能超过50'},
			author:{required:'作者不能为空',maxlength:'最大长度不能超过50'},
			createtime:{required:'创建时间不能为空',maxlength:'最大长度不能超过50'},
			cover:{required:'封面图片不能为空',maxlength:'最大长度不能超过1000'},
			isshow:{required:'是否显示不能为空',maxlength:'最大长度不能超过50'},
			contentvalue:{required:'内容不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
