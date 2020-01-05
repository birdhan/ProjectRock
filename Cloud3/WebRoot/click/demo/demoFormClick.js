$().ready(function() {
    $("#demoForm").validate({ 
    	rules: {//验证规则	        	 
    		name:{
    			required:true,
    			maxlength:30,normalText:true
    		},
    		createTime:{
    			required:true
    		},
    		age:{
    			required:true,
    			isInt:true,normalText:true
    		},
    		depart: {
    			required:true,normalText:true
    		},
    		direction: {
    			required:true,normalText:true
    		}
    	},
    	messages:{//验证消息内容
    		name:{
    			required:"名字不能为空",
    			maxlength:"最大长度不能超过30"
    		},
    		createTime:{
    			required:"创建时间不能为空"
    		},
    		age:{
    			required:"年龄不能为空",
    			isInt:"年龄只能输入大于0的数"
    		},
    		depart: {
    			required:"请选择部门"
    		},
    		direction: {
    			required:"方向不能为空"
    		}
    	}
    })
});