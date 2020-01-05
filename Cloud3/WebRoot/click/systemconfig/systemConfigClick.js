$().ready(function() {
	$("#systemConfig").validate({
		rules: {//验证规则
			dbDriver:{required:true},
			driverUrl:{required:true},
			driver:{required:true},
			user:{required:true},
			password:{required:true}
		}
	})
});