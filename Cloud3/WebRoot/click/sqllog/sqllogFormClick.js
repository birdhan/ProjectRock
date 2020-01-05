$().ready(function() {
	$("#sqllogForm").validate({
		rules: {//验证规则
			fbegintime:{required:false,maxlength:50},
			fendtime:{required:false,maxlength:50},
			fsql:{required:false,maxlength:2000,normalText:true},
			fsqltype:{required:false,maxlength:50,normalText:true},
			fparameters:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			fbegintime:{required:'开始时间不能为空',maxlength:'最大长度不能超过50'},
			fendtime:{required:'结束时间不能为空',maxlength:'最大长度不能超过50'},
			fsql:{required:'SQL不能为空',maxlength:'最大长度不能超过2000'},
			fsqltype:{required:'SQL类型不能为空',maxlength:'最大长度不能超过50'},
			fparameters:{required:'参数不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
