<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
    <style type="text/css">
    	body { font:12px/1.8em "Microsoft YaHei",Verdana, Arial, Helvetica, sans-serif;  background:url(${ctx}/images/main_bg.png) repeat-x; }
    </style>
  </head>  
  
<body>
<div class="fail_page">
  <p>出错啦！！可以点击下面的debug查看错误信息。</p>  
</div>
<div style="margin-left: 10px; color:#0099FF;width:800px;width:auto;"><s:property value="exceptionStack"/></div>
<div style="margin-left: 10px;"><s:debug></s:debug></div>
</body>
<%@ include file="common-bottom.jsp" %>