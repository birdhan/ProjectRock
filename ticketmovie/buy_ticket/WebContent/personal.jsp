<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <style>
        body, html {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;

            margin: 0;
            padding: 0;
        }

        #background {
            position: fixed;
            top: 0;
            left: 0;

            z-index: -100;
        }

        .header {
            padding: 50px 0;
            text-align: center;
        }

        img {
            border-radius: 50px;
            width: 100px;
            height: 100px;
        }

        .manage {
            text-align: center;
        }

        body{
            font-size: 15px;
            line-height: 1.4em;
            color: #f2dede;
            font-family: 'Raleway', sans-serif;
        }
    </style>

</head>
<body>

<canvas id="background"></canvas>

<script async type="text/javascript" src="js/background.js"></script>

<div class="header">

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <img src="img/1.png">
            </div>
            <!-- <div class="col-md-12">
                 <span>小狗</span>
             </div>-->
        </div>
    </div>
    <br><br>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <span class="delimiter">小狗</span>
            </div>
        </div>
    </div>

</div>
<%-- <%
String name=(String)session.getAttribute("name");
%> --%>
<div class="manage">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <table class="table table-bordered table-responsive table-condensed">

                    <tr>
                        <td>个人信息</td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><%=session.getAttribute("name")%></td>
                        <td>性别</td>
                        <td><%=session.getAttribute("sex")%></td>
                    </tr>
                    <tr>
                        <td>年龄</td>
                        <td><%=session.getAttribute("age")%></td>
                        <td>电话</td>
                        <td><%=session.getAttribute("phone")%></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <br> <br>
    <h2>购票信息</h2>
    <br> <br>
<s:iterator value="list" status="li">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-xs-4"><img src="img/1.png" width="150px"></div>
                        <div class="clo-md-5 col-xs-5"><span align="left">片名:<s:property value="movie_name"/></span>
                            <br>
                            <span align="left">类型:<s:property value="movie_type"/></span>
                            <br>
                            <span align="left"><b style="width: 500px">简介:<s:property value="movie_sketch"/></b></span>
                        </div>
                        <div class="col-md-3 col-xs-3">
                            <br>
                            <span>票价:<s:property value="movie_price"/></span>
                            <br><br>
                            <button class="btn btn-info btn-xs">我要退票</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
      <br> <br>
    </s:iterator>
    
  

</div>
</body>
</html>