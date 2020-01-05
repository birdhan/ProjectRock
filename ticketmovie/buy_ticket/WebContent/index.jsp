<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>用户购票系统</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Load fonts -->
    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:500' rel='stylesheet' type='text/css'>

    <!--Load styles -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="css/magnific-popup.css">
    <link rel="stylesheet" type="text/css" href="css/animate.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
<header>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img src="img/logo.png" alt="Seven HTML theme"/></a>
            </div>
            <div class="collapse navbar-collapse navbar-right" id="navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/infor.action">个人中心</a></li>
                    <li class="bbb"><a>登陆</a></li>
                    <li><a data-scroll href="index.html#home">关于</a></li>
                    <li><a data-scroll href="index.html#services">观影</a></li>
                    <!--<li><a data-scroll href="index.html#portfolio">登陆</a></li>-->

                    <li><a data-scroll href="index.html#about">详情</a></li>
                    <!--<li><a data-scroll href="index.html#blog">添加</a></li>-->
                    <li><a data-scroll href="index.html#contact">添加</a></li>


                </ul>
            </div>
        </div>
    </nav>
</header>
<div id="home" class="jumbotron slide slide-fullscreen">
    <div class="container">
        <span>My name is wang</span>
        <h1>欢迎来到亿达影视网上购票系统</h1>
        <!--<a href="#" class="btn btn-lg btn-primary button&#45;&#45;ujarak">Download</a>-->
    </div>
</div>
<!-- Start services section -->

<!--观影section-->
<section id="services">
    <div class="container">
        <header>
            <h2 class="wow fadeInDown">院线热映 热场优惠</h2>
            <p class="wow fadeInUp" data-wow-delay="0.1s">
                爱情片、剧情片、喜剧片、家庭片、伦理片、
                文艺片、音乐片、歌舞片、动漫片、
                西部片、武侠片、古装片、动作片、
                恐怖片、惊悚片等
            </p>
            <br><br><br>
            <!--电影-->
            <div class="container">
                <div class="row hidden_first">
                
                <s:iterator value="list" status="li">
                
                    <div class="form-group col-md-6 col-xs-6 div_ying">
                        <div class="row">
                       
                            <div class="col-md-4 col-xs-4"><img src="/patha/<s:property value="movie_img"/>" width="150px"></div>
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
                               <!--  <button class="btn btn-info btn-xs"><a href="chap1.html">在线购票</a></button> -->
                                <a class="btn btn-info btn-xs" href="${pageContext.request.contextPath}/relateaction.action?rel.m_id=<s:property value="id"/>">在线购票</a>
                                
                            </div>
                        </div>
                    </div>
                    
                   </s:iterator> 
                    
                    <%-- <div class="form-group col-md-6 col-xs-6 div_ying">
                        <div class="row">
                            <div class="col-md-4 col-xs-4"><img src="img/1.png" width="150px"></div>
                            <div class="clo-md-5 col-xs-5"><span align="left">片名:阿斯蒂芬</span>
                                <br>
                                <span align="left">类型:搞笑</span>
                                <br>
                                <span align="left"><b style="width: 500px">简介:对方水电费水电费水电费asdfaSDasdasdasdasd</b></span>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <br>
                                <span>票价:12.5</span>
                                <br><br>
                                <button class="btn btn-info btn-xs">在线购票</button>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    <div class="form-group col-md-6 col-xs-6 div_ying">
                        <div class="row">
                            <div class="col-md-4 col-xs-4"><img src="img/1.png" width="150px"></div>
                            <div class="clo-md-5 col-xs-5"><span align="left">片名:阿斯蒂芬</span>
                                <br>
                                <span align="left">类型:搞笑</span>
                                <br>
                                <span align="left"><b style="width: 500px">简介:对方水电费水电费水电费asdfaSDasdasdasdasd</b></span>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <br>
                                <span>票价:12.5</span>
                                <br><br>
                                <button class="btn btn-info btn-xs">在线购票</button>
                            </div>

                        </div>


                    </div>
                    <div class="form-group col-md-6 col-xs-6 div_ying">
                        <div class="row">
                            <div class="col-md-4 col-xs-4"><img src="img/1.png" width="150px"></div>
                            <div class="clo-md-5 col-xs-5"><span align="left">片名:阿斯蒂芬</span>
                                <br>
                                <span align="left">类型:搞笑</span>
                                <br>
                                <span align="left"><b style="width: 500px">简介:对方水电费水电费水电费asdfaSDasdasdasdasd</b></span>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <br>
                                <span>票价:12.5</span>
                                <br><br>
                                <button class="btn btn-info btn-xs">在线购票</button>
                            </div>

                        </div>


                    </div>
                    <div class="form-group col-md-6 col-xs-6 div_ying">
                        <div class="row">
                            <div class="col-md-4 col-xs-4"><img src="img/1.png" width="150px"></div>
                            <div class="clo-md-5 col-xs-5"><span align="left">片名:阿斯蒂芬</span>
                                <br>
                                <span align="left">类型:搞笑</span>
                                <br>
                                <span align="left"><b style="width: 500px">简介:对方水电费水电费水电费asdfaSDasdasdasdasd</b></span>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <br>
                                <span>票价:12.5</span>
                                <br><br>
                                <button class="btn btn-info btn-xs">在线购票</button>
                            </div>

                        </div>


                    </div>
                    <div class="form-group col-md-6 col-xs-6 div_ying">
                        <div class="row">
                            <div class="col-md-4 col-xs-4"><img src="img/1.png" width="150px"></div>
                            <div class="clo-md-5 col-xs-5"><span align="left">片名:阿斯蒂芬</span>
                                <br>
                                <span align="left">类型:搞笑</span>
                                <br>
                                <span align="left"><b style="width: 500px">简介:对方水电费水电费水电费asdfaSDasdasdasdasd</b></span>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <br>
                                <span>票价:12.5</span>
                                <br><br>
                                <button class="btn btn-info btn-xs">在线购票</button>
                            </div>

                        </div>


                    </div>
                    <div class="form-group col-md-6 col-xs-6 div_ying">
                        <div class="row">
                            <div class="col-md-4 col-xs-4"><img src="img/1.png" width="150px"></div>
                            <div class="clo-md-5 col-xs-5"><span align="left">片名:阿斯蒂芬</span>
                                <br>
                                <span align="left">类型:搞笑</span>
                                <br>
                                <span align="left"><b style="width: 500px">简介:对方水电费水电费水电费asdfaSDasdasdasdasd</b></span>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <br>
                                <span>票价:12.5</span>
                                <br><br>
                                <button class="btn btn-info btn-xs">在线购票</button>
                            </div>

                        </div>


                    </div>
                    <div class="form-group col-md-6 col-xs-6 div_ying">
                        <div class="row">
                            <div class="col-md-4 col-xs-4"><img src="img/1.png" width="150px"></div>
                            <div class="clo-md-5 col-xs-5"><span align="left">片名:阿斯蒂芬</span>
                                <br>
                                <span align="left">类型:搞笑</span>
                                <br>
                                <span align="left"><b style="width: 500px">简介:对方水电费水电费水电费asdfaSDasdasdasdasd</b></span>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <br>
                                <span>票价:12.5</span>
                                <br><br>
                                <button class="btn btn-info btn-xs">在线购票</button>
                            </div>

                        </div> --%>


                    </div>

                </div>


            </div>

        </header>
    </div>
</section>
<!-- End services section -->
<!-- Start portfolio section -->

<!--登陆页面-->
<div class="denglu container">
    <div class="row">
        <form action="loginaction.action" method="post">
            <div class="form-group col-md-9">
                <input type="text" name="username" placeholder="芳名" class="form-control">
            </div>
            <div class="form-group col-md-9">
                <input type="text" name="password" placeholder="密码" class="form-control">
            </div>
            <div class="form-group col-md-9">
                <input type="submit" value="提交" class="btn  btn-primary">
            </div>
            <div class="col-md-9">
                <p><b>还没有账号,我要<a href="register.jsp">注册</a></b></p>
            </div>

        </form>

    </div>
</div>
<!--<section id="portfolio">
    <header>
        <h2 class="wow fadeInDown">购票前请先登录</h2>
        <p class="wow fadeInUp" data-wow-delay="0.1s">
            注册登录购票可享受更多优惠,感谢您的使用,
            如果还没有账号请先<a href="register.html">注册</a>
        </p>
    </header>
    <div class="container">
        <div class="row">
            <div class="col-md=12">
                <form class="form-group form-horizontal" method="post" action="">

                    <div class="container">
                        <div class="row">
                            <div class="col-md=12">

                                <form class="form-group form-horizontal" method="post" action="">
                                    <div class=" input-group has-success col-md-4 col-md-offset-4 col-xs-8 col-xs-offset-2">
                                        <label for="user" class="sr-only">user</label>
                                        <span class="input-group-addon">账号:</span>
                                        <input type="text" class="form-control" id="user" placeholder="Mike">
                                    </div>
                                    <br>
                                    <div class=" input-group has-success col-md-4 col-md-offset-4 col-xs-8 col-xs-offset-2">
                                        <label for="pwd" class="sr-only">name</label>
                                        <span class="input-group-addon">密码:</span>
                                        <input type="text" class="form-control" id="pwd" placeholder="******">
                                    </div>
                                    <br>
                                    <div class=" col-md-2 col-md-offset-4 col-xs-3 col-xs-offset-3">
                                        <input type="submit" class="btn btn-primary btn-block btn-xs" value="登陆">
                                    </div>
                                    <div class=" col-md-2 col-xs-3">
                                        <input type="button" class="btn btn-primary btn-block btn-xs" value="取消">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <br><br><br><br><br><br><br><br><br><br>
</section>-->
<!-- End portfolio section -->


<!--详情用户信息-->

<section id="about">
    <div class="baner">
        <div class="container">
            <div class="row">
                <div class="col-md-4 box">
                    <h3>泰坦尼克号</h3>
                    <span class="delimiter"></span>
                    <p>
                        《泰坦尼克号》是美国二十世纪福斯电影公司、派拉蒙影业公司出品爱情片，由詹姆斯·卡梅隆执导，莱昂纳多·迪卡普里奥、凯特·温斯莱特领衔主演。影片以1912年泰坦尼克号邮轮在其处女启航时触礁冰山而沉没的事件为背景，讲述了处于不同阶层的两个人穷画家杰克和贵族女露丝抛弃世俗的偏见坠入爱河，最终杰克把生命的机会让给了露丝的感人故事.
                    </p>

                </div>
            </div>
        </div>
    </div>

</section>


<!--备用板块-->
<!--<section id="blog" class="dark">
    <div class="container">
        <header>
            <h2 class="wow fadeInDown">Our Blog Stories</h2>
            <p class="wow fadeInUp" data-wow-delay="0.1s">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                do eiusmod
                tempor incididunt ut.
            </p>
        </header>
    </div>
    <div class="blog-items clearfix">
        <div class="blog-item col-md-4 wow fadeInLeft">
            <img src="img/blog-1.png" align="Blog post" class="img-responsive" alt="Blog post"/>
            <div class="meta-data">
                <h3>Awesome blog title</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                    quis nostrud exercitation ullamco.
                </p>
                <a href="#">Read More...</a>
            </div>
        </div>
        <div class="blog-item col-md-4 wow fadeInLeft" data-wow-delay="0.2s">
            <img src="img/blog-2.png" align="Blog post" class="img-responsive" alt="Blog post"/>
            <div class="meta-data">
                <h3>Awesome blog title</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                    quis nostrud exercitation ullamco.
                </p>
                <a href="#">Read More...</a>
            </div>
        </div>
        <div class="blog-item col-md-4 wow fadeInLeft" data-wow-delay="0.4s">
            <img src="img/blog-3.png" align="Blog post" class="img-responsive" alt="Blog post"/>
            <div class="meta-data">
                <h3>Awesome blog title</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                    quis nostrud exercitation.
                </p>
                <a href="#">Read More...</a>
            </div>
        </div>
    </div>
    <div class="cleadfix">
        <div class="text-center load-more">
            <a href="#" class="fa fa-plus circled"></a>
        </div>
    </div>
</section>-->

<!--添加影片-->
<section id="contact">
    <div class="container">
        <header>
            <h2 class="wow fadeInDown">万部影片 实时更新</h2>
            <p class="wow fadeInUp" data-wow-delay="0.1s">
                王氏电影在中国以及澳大利亚和新西兰超过180个城市拥有直营影院516家，4571块银幕。其中IMAX银幕262块,位居全球第一
            </p>
        </header>
        <form class="row" enctype="multipart/form-data" id="file_upload" method="post" action="homeAction.action">
            <div class="form-group col-md-6">

                <input name="move.movie_name" type="text" placeholder="影片name" class="form-control"/>
            </div>
            <div class="form-group col-md-6">
                <input name="move.movie_type" type="text" placeholder="影片分类" class="form-control"/>
            </div>
            <div class="form-group col-md-6">
                <p id="previewImage">
                    <span class='center-block text-success'>图像预览</span>
                    <image id="imangid" class='img-thumbnail' style='max-width:400px; height:auto; '
                           src="img/about-bg.jpg"/>
                </p>
                <label for="imageFile" id="labela">上传图片</label>

                <input type="file" id="imageFile" name="imageFile"
                       accept="image/jpeg"/>
                <!--<p>图片预览：</p>
                　　<div id="test-image-preview"></div>
                　　<p>
                　　　　<input type="file" id="test-image-file" name="test" accept="image/gif, image/jpeg, image/png, image/jpg">
                　　</p>
                <p id="test-file-info"></p>-->
                <!--<input type="file" width="100px" height="30px">-->
                <!-- <input name="subject" type="subject" placeholder="Subject" class="form-control"/>-->
            </div>
            <div class="form-group col-md-6">
                <input name="move.movie_price" type="text" placeholder="票价" class="form-control"/>
            </div>
            <div class="form-group col-md-12">
                <textarea name="move.movie_sketch" class="form-control" rows="10" placeholder="简介"></textarea>
            </div>
            <div class="form-group col-md-12">
                <input type="submit" class="btn btn-lg btn-primary" value="添加到上映列表">
                <!--<button class="btn btn-lg btn-primary">Send</button>-->
            </div>
        </form>
        <div class="address">
            <div class="row">
                <div class="col-md-4 text-center wow zoomIn">
                    <i class="fa fa-phone circled"></i>

                </div>
                <div class="col-md-4 text-center wow zoomIn" data-wow-delay="0.2s">
                    <i class="fa fa-envelope circled"></i>

                </div>
                <div class="col-md-4 text-center wow zoomIn" data-wow-delay="0.4s">
                    <i class="fa fa-globe circled"></i>


                </div>
            </div>
        </div>
    </div>
</section>
<!-- End contact section -->
<!-- Start footer section -->
<footer>
    <div class="container">
        <p class="copyright">
            &copy; 2015 All rights reserved - Theme by <a href="#" target="_blank">
            <b>GraphBerry</b></a>-More Templates

        </p>
        <ul class="social">
            <li class="wow bounceIn"><a href="#/RqhEjP" target="_blank"><i class="fa fa-facebook"></i></a></li>
            <li class="wow bounceIn" data-wow-delay="0.1s"><a href="#/hUfpSB" target="_blank"><i
                    class="fa fa-twitter"></i></a></li>
            <li class="wow bounceIn" data-wow-delay="0.2s"><a href="#/k9zAy5" target="_blank"><i
                    class="fa fa-dribbble"></i></a></li>
            <li class="wow bounceIn" data-wow-delay="0.3s"><a href="#/FPjuCE" target="_blank"><i
                    class="fa fa-behance"></i></a></li>
            <li class="wow bounceIn" data-wow-delay="0.4s"><a href="#/UYjGTR" target="_blank"><i
                    class="fa fa-pinterest"></i></a></li>
            <li class="wow bounceIn" data-wow-delay="0.5s"><a href="#/r4xzR4" target="_blank"><i
                    class="fa fa-google"></i></a></li>
        </ul>
        <a href="index.html#home" data-scroll class="back-to-top"><i class="fa fa-chevron-up"></i></a>
    </div>
</footer>
<!-- End footer section -->

<!-- Load jQuery -->
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>

<!-- Load Booststrap -->
<script type="text/javascript" src="js/bootstrap.js"></script>

<!-- Load custom js plugins -->
<script type="text/javascript" src="js/jquery.mixitup.js"></script>
<script type="text/javascript" src="js/jquery.magnific-popup.min.js"></script>
<script type="text/javascript" src="js/wow.js"></script>
<script type="text/javascript" src="js/smooth-scroll.js"></script>

<!-- Load custom js for theme -->
<script type="text/javascript" src="js/app.js"></script>

<!--图片的上传与预览-->
<script>
    $(document).ready(function () {
        $("#imageFile").change(function () {
            /*var preview = document.getElementById('imageFile');
            if (preview != null) {
                preview.style.backgroundImage = '';
            }*/
            var fileReader = new FileReader();
            fileReader.onload = function (e) {

                var ingurl = e.target.result;
                var preview = document.getElementById('imangid');
                /*preview.style.backgroundImage = '';*/
                /* document.getElementById("labela").innerHTML=ingurl;*/

                preview.src = e.target.result;

                /*$("#previewImage").append("<span class='center-block text-success'>图像预览</span><image class='img-thumbnail' style='max-width:400px;height:auto;' src=" + e.target.result + "/>");*/
            }
            var imageFile = this.files[0];
            fileReader.readAsDataURL(imageFile);
        });
    })

    $(function () {

        $(".bbb, .bg").click(function () {
            /*$(".btn").toggleClass("on");*/
            /*$(".nav").toggleClass("on");*/
            $(".denglu").toggleClass("on");

            $(".bg").delay(500).fadeToggle();
        })


    })
    /* $("#send")
         .click(
             function() {
                 var xhr=new XMLHttpRequest();
                 xhr.open("post","fileUpload");
                 xhr.onreadystatechange = function() {
                     if (xhr.readyState == 4) {
                         if(xhr.status == 200){
                             alert("图片上传成功");
                         }else{
                             alert("图片上传失败")
                         }
                     }
                 };
                 var imageFile = $("#imageFile")[0].files[0];
                 var username=$("#username").val();
                 var myForm = new FormData();
                 myForm.append("username",username);
                 myForm.append("imageFile", imageFile);
                 xhr.send(myForm);
             });

 });*/



    /*var
        fileInput = document.getElementById('test-image-file'),
        info = document.getElementById('test-file-info'),
        preview = document.getElementById('test-image-preview');
    // 监听change事件:
    fileInput.addEventListener('change', function() {
        // 清除背景图片:
        preview.style.backgroundImage = '';
        // 检查文件是否选择:
        if (!fileInput.value) {
            info.innerHTML = '没有选择文件';
            return;
        }
        // 获取File引用:
        var file = fileInput.files[0];
        //判断文件大小
        var size = file.size;
        if (size >= 1 * 1024 * 1024) {
            alert('文件大于1兆不行!');
            return false;
        }
        // 获取File信息:
        info.innerHTML = '文件: ' + file.name + '<br>' +
            '大小: ' + file.size + '<br>' +
            '修改: ' + file.lastModifiedDate;
        if (file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif')
            alert('不是有效的图片文件!');
            return;

            // 读取文件:
            var reader = new FileReader();
        reader.onload = function (e) {
            var data = e.target.result; // 'data:image/jpeg;base64,/9j/4AAQSk...(base64编码)...}'
            info.innerHTML=data;
            preview.style.backgroundImage = 'url(' + data + ')';
            }
            // 以DataURL的形式读取文件:
            reader.readAsDataURL(file);
            console.log(file);
        })*/

</script>


<div class="bg"></div>
</body>
</html>