<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <style type="text/css">
        .body {
            text-align: center;
            padding: 200px 0px;
            /*background:linear-gradient(orangered,blueviolet);*/

        }

        body {
            animation: mycolor 5s;
            animation-iteration-count: infinite;
        }

        @keyframes mycolor {
            0% {
                background: #428bca;
                left: 0px;
                top: 0px;
            }
            20% {
                background: #5cb85c;
                left: 200px;
                top: 0px;
            }
            40% {
                background: #5bc0de;
                left: 200px;
                top: 200px;
            }
            60% {
                background: #f0ad4e;
                left: 0px;
                top: 200px;
            }
            80% {
                background: #d9534f;
                left: 0px;
                top: 0px;
            }
            100% {
                background: #428bca;
                left: 0px;
                top: 0px;
            }

        }

        .header {
            padding: 50px 0px;
            text-align: center;

        }

        img {
            width: max-content;
            height: 250px;
        }


    </style>
</head>
<body>

<header class="header">
    <div calss="container">
        <div class="row">
            <div class="col-md-12">
                <img src="img/about-bg.jpg">
            </div>
        </div>
    </div>
</header>
<aside class="aside">
    <div class="container">
        <div class="row">
            <form action="registers.action" method="post">
                <div class="col-md-6">

                    <div class="row">

                        <div class="form-group col-md-4">
                            <input name="user.username" type="text" placeholder="你的个性id" class="form-control"/>
                        </div>
                        <div class="form-group col-md-4">
                            <input name="user.password" type="text" placeholder="你的专属密码" class="form-control"/>
                        </div>
                        <div class="form-group col-md-4">
                            <input type="text" name="user.sex" placeholder="你的性别" class="form-control">
                        </div>
                        <div class="form-group col-md-4">
                            <input type="text" name="user.age" placeholder="年龄" class="form-control">
                        </div>
                        <div class="form-group col-md-4">
                            <input name="user.tel" type="text" class="form-control" placeholder="电话">
                        </div>
                        <div class="form-group col-md-4">
                            <input name="user.address" type="text" class="form-control" placeholder="地址">
                        </div>

                    </div>
                </div>
                <div class="form-group col-md-3">
                    <input type="submit" class="btn  btn-primary" value="提交注册信息">
                    <!--<button class="btn btn-lg btn-primary">Send</button>-->
                </div>
            </form>
        </div>
    </div>
</aside>


</body>
</html>