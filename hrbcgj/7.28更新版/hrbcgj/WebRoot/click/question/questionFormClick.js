$().ready(function() {
	$("#questionForm").validate({
		rules: {//验证规则
			name:{required:true,maxlength:50,normalText:true},
			createtime:{required:false,maxlength:50},
			answercontent:{required:true,maxlength:1000}
		},
		messages:{//验证消息内容
			name:{required:'问题名称不能为空',maxlength:'最大长度不能超过50'},
			createtime:{required:'创建时间不能为空',maxlength:'最大长度不能超过50'},
			answercontent:{required:'解答内容不能为空',maxlength:'最大长度不能超过1000'}
		}
	})
});
