$().ready(function() {
	$("#premierLetterForm").validate({
		rules: {//验证规则
			title:{required:true,maxlength:300,normalText:true},
			reqcontent:{required:true,maxlength:1000,normalText:true},
			reqtime:{required:false,maxlength:50},
			reqregisteruser:{required:true,maxlength:200,normalText:true},
			repstatus:{required:false,maxlength:50},
			repcontent:{required:false,maxlength:1000,normalText:true},
			reptime:{required:false,maxlength:100,normalText:true}
		},
		messages:{//验证消息内容
			title:{required:'标题不能为空',maxlength:'最大长度不能超过300'},
			reqcontent:{required:'邮件内容不能为空',maxlength:'最大长度不能超过1000'},
			reqtime:{required:'申请时间不能为空',maxlength:'最大长度不能超过50'},
			reqregisteruser:{required:'申请人不能为空',maxlength:'最大长度不能超过200'},
			repstatus:{required:'状态不能为空',maxlength:'最大长度不能超过50'},
			repcontent:{required:'回复内容不能为空',maxlength:'最大长度不能超过1000'},
			reptime:{required:'回复时间不能为空',maxlength:'最大长度不能超过100'}
		}
	})
});
