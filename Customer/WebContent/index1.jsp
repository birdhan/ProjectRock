<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script> -->
<script type="text/javascript" src="${ctx }js/json2.js"></script>
<%-- <script type="text/javascript" src="${ctx }js/jquery.min.js"></script> --%>
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }js/jquery.json-2.3.js"></script>
<script type="text/javascript">
$(function(){
	$('#btn2').click(function(){
		register();
		
	});
})


function register(){
	var form = new Object();
	form.account = $("#userId").val();
	form.pwd = $("#psw").val();
	form.username = $("#sex").val();
	form.idnum = $("#tel").val();
	$.ajax({    
        type: "POST", // 用POST方式传输    
        dataType: "text", // 数据格式:JSON    
        url: "<%=request.getContextPath()%>/test01.action", // 目标地址    
        data: form,    
        success: function (data){     
        	alert("成功");
        	/* window.location.href = "${ctx}/index"; */
        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
              alert("error")  
        }       
    }); 
}





<%-- $(document).ready(function(){
	
	$('#btn1').click(function(){
		
		var jsonuserinfo=JSON.stringify($('#form1').serializeObject());//将json对象转换为json字符串
		alert(jsonuserinfo);
		
		$.ajax({  
	        type : 'POST',  
	        contentType : 'application/json',  
	        url : '<%=request.getContextPath()%>/addUserInfo.action',  
	        data : jsonuserinfo,  
	        dataType : 'json',  
	        success : function(data) {  
	            alert("新增成功！");  
	        },  
	        error : function(data) {  
	            alert("error")  
	        }  
	    });  
		
		
	});
	
	//将form表单数据转换成json对象
	$.fn.serializeObject = function() {  
	    var o = {};  
	    var a = this.serializeArray();  
	    
	    $.each(a, function() {  
	        if (o[this.name]) {  
	            if (!o[this.name].push) {  
	                o[this.name] = [ o[this.name] ];  
	            }  
	            o[this.name].push(this.value || '');  
	        } else {  
	            o[this.name] = this.value || '';  
	        }  
	    });  
	    return o;  
	};
	
});
 --%>

</script>
</head>


<body>
<button id="btn">开始</button>
<div id="div1">div1</div>
<form action="" method="post" id="form1">
<input type="text" name="name" id="userId">
<input type="text" name="sex" id="sex">

<input type="text" name="tel" id="tel">
<input type="text" name="pwd" id="psw">

<input type="button" value="提交1" id="btn1" onclick="register()">
<input type="button" value="提交" id="btn2">
<input type="button" value="提交" id="btn3">
<input type="button" value="提交" id="btn4">


</form>

</body>
</html>