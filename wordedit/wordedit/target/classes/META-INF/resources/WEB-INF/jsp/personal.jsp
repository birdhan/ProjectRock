<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title>personal</title>
    <link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath }/images/hljgsxy.jpg">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
   <link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/zidingyi.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/site.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/site.min.js"></script>
    <script type="text/javascript" src='${pageContext["request"].contextPath}/pageoffice.js' id="po_js_main"></script>
  </head>
  <body>
  <script type="text/javascript">
  window.onload=function (){
	  findsecond();

	}
 function pizhuup(id){
	 var asdfsdf=document.getElementById("annotations").value;
	 var datas={
				id:id,
				annotation:asdfsdf
		}
		$.ajax({
		    type: "POST",
		    dataType: "json",
		    url: "/wordedit/word/updateannotation" ,
		    data: datas,
		    success: function (result) {
		    	 alert("批注以更新");
		    },
		    error:function(){
		   	 alert("error");
		    }
		})
	
 }
 function xiala(){
	 document.getElementById('xiala1').style.display="";
 }
 function xialak(){
	 document.getElementById('xiala1').style.display="none";
 }
 function findsecond(){
	 var childList = document.getElementById('secondsssss').childNodes;
	 var len=childList.length;
	 for(var i=0;i<len;i++){
	    var sssss=document.getElementById('secondsssss');
	    sssss.removeChild(sssss.childNodes[i]);
	 }
	 var ids=document.getElementById('firstcol').value;
	 var datas={
				fid:ids,
		}
		$.ajax({
		    type: "POST",
		    dataType: "json",
		    url: "/wordedit/word/findbyfaid" ,
		    data: datas,
		    success: function (result) {
		    	var abcd=result['findbyfaid']
		    	var ibm='<select name=\"fathercolumn\" class=\"pankong\">';
		    	 for(var i=0;i<abcd.length;i++){
		    		 ibm+='<option value=\"'+abcd[i].id+'\">'+abcd[i].cname+'</option>'; 
		            }
		    	 ibm+='</select>';
		         $("#secondsssss").append(ibm);
		    },
		    error:function(){
		   	 alert("error");
		    }
		})
 }
 function shanchuang(){
	 var han=document.getElementsByClassName('pankong');
	 var result="ok";
	 for (var i=0;i<han.length;i++){
		 if(han[i].value==""){
			 result="error";
			 alert("上传信息不能为空！");
			 break;
		 }
	 }
	 if(result=="ok"){ 
		 document.getElementById("loadingbg").style.display="";
		 var formData = new FormData($("#form001")[0]);
		 $.ajax({
			    type: "POST",
			    dataType: "json",
			    url: "/wordedit/word/upfile" ,
			    async: false,
	            cache: false,
	            contentType: false,
	            processData: false,
			    data: formData,
			    success: function (result) {
			    	alert("上传成功");
			    	document.getElementById("loadingbg").style.display="none";
			    },
			    error:function(){
			   	 alert("error");
			   	document.getElementById("loadingbg").style.display="none";
			    }
			})
	 }
 }
 function findbyfird(id){
	 var datas={
				fid:id,
		}
		$.ajax({
		    type: "POST",
		    dataType: "json",
		    url: "/wordedit/word/findbyfaid" ,
		    data: datas,
		    success: function (result) {
		    	var abcd=result['findbyfaid']
		    	if(abcd==""){
		    		alert("这个是空的");
		    	}else{
		    	
		    	var ibm1='';
		    	 for(var i=0;i<abcd.length;i++){
		    		ibm1+='<a href=\"#'+abcd[i].id+'\" class=\"list-group-item\" data-toggle=\"collapse\" onclick=\"wchildren(\''+abcd[i].id+'\')\">|-------'+abcd[i].cname+'  <span class=\"dian1\">●●●</span></a>';
		    		ibm1+='<div class=\"collapse list-group-submenu\" id=\"'+abcd[i].id+'\">';
		    		ibm1+=' </div>';
		            }
		         document.getElementById(id).innerHTML=ibm1;
		     	
		    	}
		    },
		    error:function(){
		   	 alert("error");
		    }
		})
	 
	 
 }
 
 function wchildren(id){
	 var datas={
				wfid:id,
		}
		$.ajax({
		    type: "POST",
		    dataType: "json",
		    url: "/wordedit/word/findbyword" ,
		    data: datas,
		    success: function (result) {
		    	var abcde=result['findbyfacoid']
		    	if(abcde==""){
		    		alert("这个是空的");
		    	}else{
		    	
		    	var ibm1='';
		    	 for(var i=0;i<abcde.length;i++){
		    		ibm1+='<a href=\"#\" class=\"list-group-item\" data-toggle=\"collapse\" onclick="yulanword(\''+abcde[i].serveraddress+'\',\''+abcde[i].pngurl+'\',\''+abcde[i].wauthor+'\',\''+abcde[i].wordtype+'\',\''+abcde[i].worddate+'\',\''+abcde[i].annotation+'\',\''+abcde[i].id+'\',\''+abcde[i].wname+'\')">';
		    		ibm1+='<img class="imghan" src=\"${pageContext.request.contextPath }/images/word1.jpg\" width=\"20px\" height=\"20px\">';
		    		ibm1+='&nbsp;&nbsp;<div class="yang"><input type=\"text\" value=\"'+abcde[i].wname+'\" onchange="changename(this,\''+abcde[i].id+'\')"/></div>';
		    		ibm1+='&nbsp;浏览</a>';
		            }
		         document.getElementById(id).innerHTML=ibm1;
		     	
		    	}
		    },
		    error:function(){
		   	 alert("error");
		    }
		})
	 
	 
	 
 }
 function changename(enment,id){
	 var upname=enment.value;
	 var datas={
			id:id,
			name:upname
	}
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    url: "/wordedit/word/updateawname" ,
	    data: datas,
	    success: function (result) {
	    	 alert("文件名已更新");
	    },
	    error:function(){
	   	 alert("error");
	    }
	})
 }
 function yulanword(server,png,author,type,date,annotation,id,wname){
	 document.getElementById("imgword1").src=server+png; 
	 var contrast1=document.getElementById("contrast"); 
	 contrast1.setAttribute("onclick","addcontrast('"+id+"','"+wname+"')");
	 document.getElementById("wauthor1").innerHTML=author;
	 document.getElementById("wordtype").innerHTML=type;
	 var pizhu=document.getElementById("annotations");
	 pizhu.setAttribute("onchange","pizhuup('"+id+"')")
	 document.getElementById("worddate").innerHTML=date;
	 var onclicks=document.getElementById("deleteword");
	 onclicks.setAttribute("onclick", "deletewords('"+id+"')");
	 pizhu.value=annotation;
	 var heights=window.screen.availHeight;
		var widths=window.screen.availWidth;
	 document.getElementById("editword").href="javascript:POBrowser.openWindowModeless('word?docxname="+id+"','width="+widths+";height="+heights+";');";
	 
 }
 
 function addcontrast(id,wname){
	 var datas={
				wid:id,
				wname:wname	
		}
		$.ajax({
		    type: "POST",
		    dataType: "json",
		    url: "/wordedit/word/addcontrast" ,
		    data: datas,
		    success: function (result) {
		    	alert("添加成功!");
		    },
		    error:function(){
		   	 alert("error");
		    }
		})
	 
 }
 function deletewords(id){
	 if(confirm("删除后不可恢复")){
		 var datas={
					id:id,
			}
			$.ajax({
			    type: "POST",
			    dataType: "json",
			    url: "/wordedit/word/deleteword" ,
			    data: datas,
			    success: function (result) {
			    	var aas=result["result"];
			     	alert(aas);
			     	document.getElementById("imgword1").src="${pageContext.request.contextPath }/images/f8b-3c596db771f3han42347.png";
			    },
			    error:function(){
			   	 alert("error");
			    }
			})
		
	 }
 }
  </script>
<div class="bgloading" id="loadingbg" style="display: none;">
<img alt="" src="${pageContext.request.contextPath }/images/loading.gif" width="200px" height="200px">
<h3>上传中......</h3>
</div>
    <!--nav-->
    <nav role="navigation" class="navbar navbar-custom">
        <div class="container-fluid">
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button data-target="#bs-content-row-navbar-collapse-5" data-toggle="collapse" class="navbar-toggle" type="button">
              <span class="sr-only">HCY</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">当前用户：${user.name}</a>
            
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div id="bs-content-row-navbar-collapse-5" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
              <li class="active"><a href="${pageContext.request.contextPath }/word/first">前往首页</a></li>
              <li class="active"><a href="${pageContext.request.contextPath }/word/contrast">查重系统</a></li>
              <li class="active"><a href="${pageContext.request.contextPath }/word/zhuxiaodenglu">注销登录</a></li>
             
        
            </ul>

          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
    <!--header-->
    <div class="container-fluid">
    <!--documents-->
        <div class="row row-offcanvas row-offcanvas-left">
          <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
            <ul class="list-group panel">
                <li class="list-group-item"><img src="${pageContext.request.contextPath }/images/word1.jpg" width="18px" height="18px"> <b>文档栏目组</b></li>
                <c:forEach items="${findbyuid }" var="findbyuid1">
                <li>
                  <a href="#${findbyuid1.id }" class="list-group-item " data-toggle="collapse" onclick="findbyfird('${findbyuid1.id }')">${findbyuid1.cname }<span class="dian">●●●</span></a>
                  <div class="collapse" id="${findbyuid1.id }">
                  
                  </div>
                </li>
                </c:forEach>
              </ul>
          </div>
          <div class="col-xs-12 col-sm-9 content">
            <div class="panel panel-default">
              <div class="panel-heading">
                <a class="panel-title" href="javascript:alert('请选择一篇文档')" id="editword">文本编辑</a>
                <a class="panel-title" href="#" style="margin-left: 50px;" onclick="alert('请选择一篇文档')" id="contrast">添加查重</a> 
                <a class="panel-title" href="${pageContext.request.contextPath }/word/column" style="margin-left: 50px;">栏目管理</a>
                <a class="panel-title" href="#" style="margin-left: 50px;" onclick="xiala()">上传文档</a> 
                <a class="panel-title" href="#" style="margin-left: 50px;" onclick="alert('请选择一篇文档')" id="deleteword">删除文档</a> 
                <div style="width: 100%;height: 60px;margin-top: 25px;display: none;" id="xiala1">
               
                <form action="${pageContext.request.contextPath }/word/upfile" id="form001" enctype="multipart/form-data">
                <label>作者：</label>
                <input type="text" name="wauthor" class="pankong" id="wauthor">&nbsp;&nbsp;
                <label>类型：</label>
                <select name="wordtype" class="pankong">
                <c:forEach items="${findAll }" var="findAll">
                <option value="${findAll.name }">${findAll.name }</option>
                </c:forEach>
                </select>&nbsp;&nbsp;
                <label>一级目录</label>
                <select onchange="findsecond()" id="firstcol" class="pankong">
                 <c:forEach items="${findbyuid }" var="findbyuid">
                <option value="${findbyuid.id }">${findbyuid.cname }</option>
                 </c:forEach>
                </select>&nbsp;&nbsp;
                <label>二级目录</label>
                <span id="secondsssss">
                
                </span>&nbsp;&nbsp;
                <label>选择文档：</label>
                <input type="file" style="width: 205px;" name="word" class="pankong">
                </form>
                <br>
                <button onclick="shanchuang()">上传</button>
                <button onclick="xialak()">取消</button>
               
               
                </div>
              </div>
              <div class="panel-body">
                <div class="content-row">
                  
                  <!-- <iframe src="http://localhost:8081/html/index.html" style="width: 100%;height: 600px;"></iframe> -->
                  <div class="pngpicture">
                  <img class="pngccccc" src="${pageContext.request.contextPath }/images/f8b-3c596db771f3han42347.png" id="imgword1">
                  
                  </div>
                   <div class="pizhu">批注：<textarea cols="6" rows="2" style="width: 100%;" onchange="pizhuup()" id="annotations"></textarea></div>
                   <div><b>作者：</b><small id="wauthor1"></small>&nbsp;&nbsp;&nbsp;&nbsp;<b>类型：</b><small id="wordtype"></small>&nbsp;&nbsp;&nbsp;&nbsp;<b>时间：</b><small id="worddate"></small></div>
                  <div class="row">
                    <div class="col-md-12">
                        
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
      </div>
    </div>
  </body>
</html>
