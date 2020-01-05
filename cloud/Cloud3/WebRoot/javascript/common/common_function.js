/**
 * 日期转换的方法
 * @param format 例如：yyyy-MM-dd hh:mm:ss
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

/**
 * 去除空格
 * @returns
 */
String.prototype.LTrim = function() {
	return this.replace(/(^\s*)/g, "");
}
String.prototype.RTrim = function() {
	return this.replace(/(\s*$)/g, "");
}
String.prototype.Trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 模拟java replaceAll方法
 */
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}


/**
 * 截取字符串拼写好span标签
 * @param value : 待截取的值
 * @param subLeng : 截取长度
 */
function substring2Span(value,sublength) {
	if(value.length > sublength) {		//保证待截取的值一定要大于要截取的长度值
		value = "<span title='"+value+"'>"+value.substring(0,sublength)+"...</span>";
		return value;
	} else {							//如果小于，那么直接返回当前值
		return value;
	}
}

/**
 * 通过id获取元素
 * @param id
 * @returns
 */
function getEleById(id) {
	return document.getElementById(id);
}

/**
 * 通过名字获取元素
 * @param name
 * @returns
 */
function getElesByName(name) {
	return document.getElementsByName(name);
}

/**
 * 将字符串转成日期类型数据
 * @param strDate
 */
function getDateByString(strDate) {
	return new Date(Date.parse(strDate.replace(/-/g,   "/")));
}

/**JavaScript Document
 * js获取项目根路径，如： http://localhost:8083/uimcardprj 
 */
function getRootPath(){
	//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath=window.document.location.href;
	//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8083
	var localhostPaht=curWwwPath.substring(0,pos);
	//获取带"/"的项目名，如：/uimcardprj
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	//return(localhostPaht+projectName);
	return projectName;
}

function goToUrl(url) {
	window.location.href = url;
}

/**
 * 得到字典的label值
 * @param moduleName ： 模块名
 * @param dictType ：控件类型
 * @param property ： 属性名称
 * @param dictValue ： 字典值
 * @returns {String}：如果找到了label值则正常返回，若没有找到则返回相关错误信息
 */
function getDictLabel(moduleName,dictType,property,dictValue) {
	var returnValue = "";
	if(dictValue.Trim() != "") {		
		var dictValue_arr = dictValue.split(",");
		var dictList4Json = getEleById("dictList4Json").value;
		if(dictList4Json.Trim() != "") {
			dictList4Json = eval("("+dictList4Json+")");
			for(var i=0;i<dictList4Json.length;i++) {
				for(var j=0;j<dictValue_arr.length;j++) {
					if(dictList4Json[i].moduleName == moduleName && dictList4Json[i].dictType == dictType && dictList4Json[i].property == property && dictList4Json[i].dictValue.Trim() == dictValue_arr[j].Trim()) {
						if(returnValue == "") {
							returnValue += dictList4Json[i].dictLabel;
						} else {
							returnValue += ","+dictList4Json[i].dictLabel;
						}
						
						if(dictValue_arr.length == returnValue.split(",").length) {
							break;
						}
					}
				}			
			}
		}
		if(returnValue == "") {
			returnValue = "<span title='字典类型为：" + dictType + "，属性为："+property+"，模块名称为："+moduleName+"，值为"+dictValue+"没有在数据字典中配置'><font color='red'>未找到字典值&nbsp;(请看详细)</font></span>";
		} 
	} 
	
	return returnValue;
}

/**
 * 删除多个记录的校验
 * @param delUrl
 * @return
 */
function delMoreByIds(delUrl) {
	var ids = "";
	var common_del_ids = document.getElementsByName("ids");
	for(var i=0;i<common_del_ids.length;i++) {
		if(common_del_ids[i].checked == true) {
			ids += common_del_ids[i].value + ",";
		}		
	}
	if(ids != "") {
		ids = ids.substring(0, ids.length-1);
		$.ligerDialog.confirm("您确定要删除选择的数据吗？", function (yes) {
			if(yes) {
				if(delUrl.indexOf("?") == -1) {//表示没有别的参数
					window.location.href = delUrl + "?ids=" + ids;
				} else {
					window.location.href = delUrl + "&ids=" + ids;
				}	
			}
		});
	} else {
		f_alert("请选择要删除的数据","error");
	}
}

/**
 * 删除某个记录
 * @param url
 */
function delDataById(url) {
	$.ligerDialog.confirm("您确定要删除选择的数据吗？", function (yes) {
		if(yes) {
			window.location.href = url;
		}
	});
}

/**
 * 弹出框，默认的弹出框方法，如果不填写弹出类型，将以警告方式弹出
 * @param type
 */
function f_alert0(content)
{
    $.ligerDialog.alert(content, '提示', "warn");
}

/**
 * 弹出框
 * @param type
 */
function f_alert(content,type)
{
    var dialog = $.ligerDialog.alert(content, '提示', type);
}

/**
 * 弹出框
 * @param type
 */
function f_alert2(content,type)
{
    switch (type)
    {
        case "success":
            $.ligerDialog.success(content);
            break;
        case "warn":
            $.ligerDialog.warn(content);
            break;
        case "question":
            $.ligerDialog.question(content);
            break;
        case "error":
            $.ligerDialog.error(content);
            break;
        case "confirm":
            $.ligerDialog.confirm(content, function (yes)
            {
            	alert(yes);
            });
            break;
        case "warning":
            $.ligerDialog.warning(content, function (type)
            {
                alert(type);
            });
            break;
        case "prompt":
            $.ligerDialog.prompt(content, function (yes, value)
            {
                if (yes) alert(value);
            }); 
            break;
        case "prompt2":
            $.ligerDialog.prompt(content,'初始化值', function (yes, value)
            {
                if (yes) alert(value);
            });
            break;
        case "prompt3":
            $.ligerDialog.prompt(content, true, function (yes, value)
            {
                if (yes) alert(value);
            });
            break;
        case "prompt4":
            $.ligerDialog.prompt(content, '初始化多选框值', true, function (yes, value)
            {
                if (yes) alert(value);
            });
            break;
        case "waitting":
            $.ligerDialog.waitting('正在保存中,请稍候...');
            setTimeout(function ()
            {
                $.ligerDialog.closeWaitting();
            }, 2000);
            break;
        case "waitting2":
            var gridTable = $.ligerDialog.waitting('正在保存中2,请稍候...');
            setTimeout(function ()
            {
                gridTable.close();
            }, 1000);
            break;
    }
}

/**
 * 弹出框重复提示
 */
var tip;
function f_tip(con) {
	tip = $.ligerDialog.tip({  title: '提示信息',content:con});
    setTimeout("autoClose_tip()",5000);
}

/**
 * 弹出框不重复提示
 */
function f_tip2(con) {
    if (!tip) {
        tip = $.ligerDialog.tip({ title: '提示信息', content: con });
        setTimeout("autoClose_tip()",5000);
    }
    else {
        var visabled = tip.get('visible');
        if (!visabled) tip.show();
        tip.set('content', con);
        setTimeout("autoClose_tip()",5000);
    }
}

/**
 * 自动关闭右下角弹出消息
 */
function autoClose_tip() {
	tip.close();
}

/**
 * 列表全选功能
 */
var checkflag = "false";
function choose() {
    var objs = document.getElementsByName("ids");
    if(checkflag=="false"){
        for(var i=0; i<objs.length; i++) {
            if(objs[i].type.toLowerCase() == "checkbox" )
                objs[i].checked = true;
            checkflag="true";
        }
    }else if(checkflag=="true"){
        for(var i=0; i<objs.length; i++) {
            if(objs[i].type.toLowerCase() == "checkbox" )
                objs[i].checked = false;
            checkflag="false"
        }
    }
}

/**
 * 过滤特殊字符，包括数据库敏感字符
 * @param value
 */
function filterString(value) {
	var regStr = ["'","%","^","`","*","‘","’"," "];										//不符合字符
	var flag = 0;
	for(var i=0;i<regStr.length;i++) {
		if(value.indexOf(regStr[i]) !=-1) {
			return false;
		}
	}
	return true;
}

/**
 * 展开部门树
 */
var deptTreeWin;
function openDeptTree(hiddenId,single,autoCheckboxEven,checkbox) {
	var radomParam = Math.random();
	var deptsNo = getEleById(hiddenId).value;										//获取该控件下的初始部门值
	deptTreeWin = $.ligerDialog.open(
		{title:'部门', url: getRootPath()+'/department/openDeptTree.do?radomParam='+radomParam+"&deptsNo="+deptsNo+"&hiddenId="+hiddenId+"&single="+single+"&autoCheckboxEven="+autoCheckboxEven+"&checkbox="+checkbox, height: 500, width: 500, name:'deptTreeIframe',isResize: true}
	);
}

/**
 * 关闭部门树窗口
 */
function closeDeptTreeWin() {
	deptTreeWin.close();
}

/**
 * 展开人员树
 */
var userTreeWin;
function openUserTree(hiddenId,single,autoCheckboxEven,checkbox,confirmBtnClick) {
	var radomParam = Math.random();
	var userId = getEleById(hiddenId).value;										//获取该控件下的初始人员值	
	userTreeWin = $.ligerDialog.open(
		{title:'人员', url: getRootPath()+'/systemuser/openUserTree.do?radomParam='+radomParam+"&userId="+userId+"&hiddenId="+hiddenId+"&single="+single+"&autoCheckboxEven="+autoCheckboxEven+"&checkbox="+checkbox+"&confirmBtnClick="+confirmBtnClick, height: 500, width: 500, name:'userTreeIframe',isResize: true}
	);
}

/**
 * 关闭部门树窗口
 */
function closeUserTreeWin() {
	userTreeWin.close();
}

/**
 * 图片上传插件的图片回显方法
 */
function showPic(picFileId,imgId) {
	getEleById(imgId+"Img").src = getRootPath() + "/uploadpic/getPic.do?id="+picFileId+"&tempParm="+Math.random();
	getEleById(imgId+"Img").style.width = "232px";
	getEleById(imgId+"Img").style.heigth = "162px";
	
	getEleById(imgId).value = picFileId;
}

/**
 * 显示原图片
 * @param imgId
 * @returns
 */
function showNoPicFile(imgId) {
	getEleById(imgId+"Img").src = getRootPath() + "/images/uppic.jpg";
	getEleById(imgId+"Img").style.width = "232px";
	getEleById(imgId+"Img").style.heigth = "162px";
	getEleById(imgId).value = "";			//将隐藏域值置空
}

/**
 * 清除图片
 * @param imgId
 * @param picFileId
 */
function clearPic(imgId,picFileId) {
	$.ligerDialog.confirm("您确定要清除此图片吗？", function (yes) {
		if(yes) {
			$.ajax({   
				type:"post",     
				url:getRootPath()+"/uploadpic/clearPic.do",           
				dataType:"text",             
				data:"id="+picFileId+"&tempParm="+Math.random(),        
				success:function(data){
					f_tip("<font color='#006633'><strong>操作成功</strong></font>");	
					getEleById(imgId).value = "";
					getEleById(imgId+"Img").src = getRootPath() + "/images/uppic.jpg";
				}
			});
		}
	});
}

/**
 * 删除图片
 */
function delPicFile(imgId,picFileId,closeWinMethod) {
	window.location.href = getRootPath() + "/uploadpic/delPicFile.do?imgId="+imgId+"&picFileId="+picFileId+"&closeWinMethod="+closeWinMethod+"&tempParam="+Math.random();
}

/**
 * 查看原图
 */
function showOriPic(url) {
	window.open (url, 'showPic', 'height='+window.parent.document.documentElement.clientHeight+', width='+window.parent.document.documentElement.clientWidth+', top=20,left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no')
}

var aboutWin;
function aboutLinkWin() {
	aboutWin = $.ligerDialog.open(
		{title:'关于', url: getRootPath()+'/desc.jsp',height: document.documentElement.clientHeight, width: document.documentElement.clientWidth, name:'dmpFileListWinIframe',isResize: false,isDrag:false}
	);
}

function aboutLinkWinClose() {
	aboutWin.close();
}