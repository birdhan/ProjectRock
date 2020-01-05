<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>Springmvc Demo</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!--PageOffice.js和jquery.min.js文件一定要引用-->
     <script type="text/javascript" src='${pageContext["request"].contextPath}/jquery.min.js'></script>
     <script type="text/javascript" src='${pageContext["request"].contextPath}/pageoffice.js' id="po_js_main"></script>
</head>
<body>
Hello PageOffice !<br>
<a href="javascript:POBrowser.openWindowModeless('word','width=1200px;height=800px;');">在线打开文件</a>
</body>
</html>