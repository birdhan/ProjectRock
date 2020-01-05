<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		
		<script type="text/javascript"> 
            $(function ()
            { 
                $("#accordion1").ligerAccordion(
                {                		
                    height: 400
                });
            });
     	</script> 
     	<style type="text/css">        
     		#accordion1{ width:200px;overflow:hidden; vertical-align:middle;}
     	</style>
	</head>

<body style="padding:5px;">
	<div id="accordion1"> 
         ${tree}       
    </div> 
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
