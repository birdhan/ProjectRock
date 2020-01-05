<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>success</title>

<script type='text/javascript'>  
              
            window.onload = function(){  
            	var flag = confirm("注册成功单击确定返回主页");  
                if(flag){  
                	window.location.href='/buy_ticket/selectAll.action';
                }else{  
                	var flag1 = confirm("你确定要退出本站吗");  
                	if (flag1) {
						alert("不可能的");
						window.location.href='/buy_ticket/selectAll.action';
					}else{
						window.location.href='/buy_ticket/selectAll.action';
					}
                }  
            }  
            /* function f2(){  
                var flag = confirm("这是第二种弹窗confirm单击确定返回true,单击取消返回false");  
                if(flag){  
                  alert("你点击的是确定");  
                }else{  
                  alert("你单击的是取消");   
                }  
              }  
               */
           /*  $(window).load(function(){  
                alert("jquery===》window load" );  
            })  
              
            $(document).ready(function () {  
                alert("jquery====》document ready");  
            });  
              
            $(function(){  
                alert("jquery====》document onload");  
            });  
              
            function aaa(){  
                alert("静态标签====》onload");  
            }   */
        </script>  
</head>
<body>


</body>
</html>