$().ready(function() {
	$("#guideForm").validate({
		rules: {//验证规则
			title:{required:true,normalText:true,maxlength:100,normalText:true},
			createtime:{required:false,maxlength:50},
			detailcontent:{required:false,maxlength:50},
			sortnum:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			title:{required:'标题不能为空',maxlength:'最大长度不能超过100'},
			createtime:{required:'创建时间不能为空',maxlength:'最大长度不能超过50'},
			detailcontent:{required:'详细内容不能为空',maxlength:'最大长度不能超过50'},
			sortnum:{required:'排序顺序不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
