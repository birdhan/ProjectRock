<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">

    <title></title>
    <link rel="shortcut icon" href="img/favicon.ico">
    
    <!-- global stylesheets -->
    <link href="css/backs.css" rel="stylesheet">
    <link rel="stylesheet" href="css/css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/font-icon-style.css">
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <link rel="stylesheet" href="css/colorbox.css" />
	<script src="js/jquery-1.9.1.min.js"></script> 
    <script src="js/jquery.colorbox.js"></script>	

    
</head>

<body>
 <script>
        $(document).ready(function () {
            $("#imageFile").change(function () {


                var fileReader = new FileReader();
                fileReader.onload = function (e) {


                    var ingurl = e.target.result;
                    var preview = document.getElementById('imangid');
                    preview.src=e.target.result;
                }
                var imageFile = this.files[0];
                fileReader.readAsDataURL(imageFile);
            });
        })
    </script>
<script type="text/javascript">

			$(document).ready(function(){

				//Examples of how to assign the Colorbox event to elements

				/* $(".ajax").colorbox();

				$(".youtube").colorbox({iframe:true, innerWidth:640, innerHeight:390});

				$(".vimeo").colorbox({iframe:true, innerWidth:500, innerHeight:409});
 */
				$(".iframe").colorbox({iframe:true, width:"60%", height:"70%"});

				/* $(".inline").colorbox({inline:true, width:"50%"}); */


			});
function shanchu(){
	
	confirm("确认删除本条记录");
}
		</script>
    <header class="header">
        <nav class="navbar navbar-expand-lg ">

            <div class="container-fluid ">
                <div class="navbar-holder d-flex align-items-center justify-content-between">
                    <div class="navbar-header">
                        <a href="index.html" class="navbar-brand">
                          <div class="brand-text brand-big hidden-lg-down"><img src="img/logo-white.png" alt="Logo" class="img-fluid"></div>
                        </a>
                    </div>
                </div>
            </div>
        </nav>
    </header>
    <div class="page-content d-flex align-items-stretch">

        <!--*****左侧导航栏*****-->
        <nav class="side-navbar">
            <div class="sidebar-header d-flex align-items-center">
                <div class="avatar"><img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
                <div class="title">
                    <h1 class="h4">${admin}</h1>
                </div>
            </div>
            <hr>

            <ul class="list-unstyled">
                <li> <a href="${pageContext.request.contextPath }/backadmin"> <i class="icon-grid"></i>用户管理 </a></li>
                <li> <a href="${pageContext.request.contextPath }/orderadmin"> <i class="icon-grid"></i>订单管理</a></li>
                <li> <a href="${pageContext.request.contextPath }/shopadmin"> <i class="icon-grid"></i>商品管理</a></li>
                <li> <a href="${pageContext.request.contextPath }/upshoping"> <i class="icon-grid"></i>上传商品</a></li>
                <li> <a href="${pageContext.request.contextPath }/typeadmin"> <i class="icon-grid"></i>类型管理</a></li>
            </ul>
        </nav>

        <div class="content-inner chart-cont">

            
            <div class="row">
            <div class="col-md-8">                                                                
               <form action="${pageContext.request.contextPath }/addkuaixiao" class="form-group" enctype="multipart/form-data" method="post">
               <label>商品名称</label>
               <input type="text" class="form-control" id="kname" name="kname">
               <label>商品价格</label>
               <input type="text" class="form-control" id="kprice" name="kprice">
               <label>商品特点</label>
               <input type="text" class="form-control" id="kpoint" name="kpoint">
               <label>商品详情</label>
               <input type="text" class="form-control" id="kdetails" name="kdetails">
               <label>商品类型</label>
               <select class="form-control" id="ktype" name="ktype">
               <c:forEach items="${typelist}" var="typelist">
               <option>${typelist.ktype}</option>
               </c:forEach>
               </select>
               <label>图片</label>
               <input type="file" id="imageFile" name="imageFile" >
               <input type="submit" value="添加" class="form-control btn btn-success">
               </form>
               </div>
               <div class="col-md-4">
               
               <img alt="没找到" src="http://img.zcool.cn/community/0152f156d40c006ac7252ce62e8f3e.gif" width="80%" height="80%"  id="imangid" style="margin-top: 50px;">
               
               
               </div>
            </div> 
        </div>
    </div> 
 
    <script src="js/popper/popper.min.js"></script>
    <script src="js/tether.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.cookie.js"></script>
    <script src="js/jquery.validate.min.js"></script> 
    <script src="js/chart.min.js"></script> 
    <script src="js/front.js"></script> 
		
</body>

</html>