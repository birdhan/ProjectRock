$().ready(function() {
	$("#appActionForm").validate({
		rules: {//验证规则
			title:{required:false,maxlength:50,normalText:true},
			actice:{required:false,maxlength:500,normalText:true},
			createtime:{required:false,maxlength:50,normalText:true},
			state:{required:false,maxlength:50,normalText:true},
			reply:{required:false,maxlength:200,normalText:true},
			uid:{required:false,maxlength:50}
		},
		messages:{//验证消息内容
			title:{required:'标题不能为空',maxlength:'最大长度不能超过50'},
			actice:{required:'内容不能为空',maxlength:'最大长度不能超过500'},
			createtime:{required:'申请时间不能为空',maxlength:'最大长度不能超过50'},
			state:{required:'状态不能为空',maxlength:'最大长度不能超过50'},
			reply:{required:'批文不能为空',maxlength:'最大长度不能超过200'},
			uid:{required:'用户id不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
