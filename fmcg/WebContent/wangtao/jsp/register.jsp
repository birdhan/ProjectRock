<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <!-- 重置文件 -->
  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" href="css/style001.css">
  <title>用户注册</title>
</head>
<body>

  <div class="reg_div">
    <p>注册</p>
    <form action="${pageContext.request.contextPath }/adduser" id="formregister">
    <ul class="reg_ul">
      <li>
          <span>用户名：</span>
          <input type="text" name="name" value="" placeholder="用户名必须为中文字符" class="reg_user">
          <span class="tip user_hint"></span>
      </li>
      <li>
          <span>密码：</span>
          <input type="password" name="pwd" value="" placeholder="6-16位密码" class="reg_password">
          <span class="tip password_hint"></span>
      </li>
      <li>
          <span>确认密码：</span>
          <input type="password" value="" placeholder="确认密码" class="reg_confirm">
          <span class="tip confirm_hint"></span>
      </li>
      <li>
          <span>收货地址：</span>
          <input type="text" name="address"  placeholder="收货地址" class="reg_email">
          <span class="tip email_hint"></span>
      </li>
      <li>
          <span>手机号码：</span>
          <input type="text" name="tel" placeholder="手机号" class="reg_mobile">
          <span class="tip mobile_hint"></span>
      </li>
      <li>
        <button type="button" class="red_button">注册</button>
      </li>
    </ul>
    </form>
  </div>
  
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/script01.js"></script>
 <script type="text/javascript">
 $('.red_button').click(function(){

	  if( user_Boolean == false){
		  alert("用户格式不正确");
	  }else if( password_Boolean == false){
		  alert("密码格式不正确");
	  }else if(varconfirm_Boolean == false){
		  alert("两次密码不一致");
	  }else if( emaile_Boolean == false){
		  alert("地址信息不规范");
	  }else if( Mobile_Boolean == false){
		  alert("手机号格式不正确");
	  }else{
		  $("#formregister").submit();
		  alert("注册成功");
	  }
	});

</script>
 
</body>
</html>

