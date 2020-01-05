<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
    <style type="text/css">
    	body { font:12px/1.8em "Microsoft YaHei",Verdana, Arial, Helvetica, sans-serif;  background:url(${ctx}/images/main_bg.png) repeat-x; }
    </style>
    
    <script type="text/javascript">
    	function autoReturn() {
    		window.location.href = '${listUrl}';
    	}
    	
    	setTimeout("autoReturn()",1000);
    </script>
  </head>  
  
<body>
<div class="success_page">
  <p>2秒钟后跳转到列表页面</p>
  <ul>
    <li><a href="${listUrl}">返回列表页面>></a></li>
  </ul>
</div>
</body>
<%@ include file="common-bottom.jsp" %>