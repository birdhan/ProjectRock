/**
 * 基础功能文件
 */

function resetForm(formId) {
	$("#"+formId).parsley("reset");
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
	if(projectName == "") return pathName;
	return projectName;
}

/**
 * 跳转url
 * @param url
 */
function goToUrl(url) {
	window.location.href = url;
}

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

/**
 * 模糊查询
 */
function isBlurQuery(formId) {
	if(!$("#"+formId).parsley().validate()) {					// 先验证表单数据
		return;
	}
	
	var actionUrl = getEleById(formId).action;
	var simbolFlag = "?";
	if(actionUrl.indexOf("?") != -1) {							//表示有问号
		simbolFlag = "&";
	}
	getEleById(formId).action = actionUrl + simbolFlag + "isBlurQuery=like";	
	getEleById(formId).submit();
}

//###########################################################################################
//##########################################本地存储(start)##################################
//###########################################################################################

//************************web sql database********************************
/**
 * 打开或创建websql数据库
 * @param dataBastName：数据库名称
 * @param version：版本
 * @param desc：描述
 * @param size：数据库大小（以字节为单位）
 */
function openWebSqlDataBase(dataBastName , version , desc , size) {
	var db = window.openDatabase(dataBastName,version,desc, size);
	return db;
}

/**
 * 更新数据表webSql(用于增删改)
 * @param db：数据库对象
 * @param createTableSql：创建数据库语句，插入前先创建数据库
 * @param updateSql：插入数据表语句
 */
function updateOrCreateTable(db , createTableSql , updateSql) {
	var flag = 0;												//默认失败
	db.transaction(function (tx) {
		tx.executeSql(createTableSql); 							//exmapl: 'CREATE TABLE IF NOT EXISTS LOGS (id unique, log)'
		tx.executeSql(updateSql);
		flag = 1;												//表示成功
	});
	return flag;
}

//************************IndexedDB********************************
var indexedDB = window.indexedDB || window.webkitIndexedDB || window.mozIndexedDB || window.msIndexedDB; 
var idb;
/**
 * 开启indexed数据库
 * @param dbName
 * @param dbVersion
 */
function openIndexedDB(dbName , dbVersion , createObjectStoreName , keyPath , indexArr) {
	dbVersion = dbVersion || 1;
	var request = indexedDB.open(dbName, dbVersion);
	request.onerror = function(event) {
		console.log("Database error: " + event.target.errorCode);
	};
	request.onsuccess = function(event) {
		console.log("Database created");
		idb = event.target.result;
	};
	request.onupgradeneeded = function (event) {
		idb = event.target.result;
		if(createObjectStoreName != null && keyPath != null) {
			createObjectStorage(createObjectStoreName , keyPath , indexArr);
		} 
	};
}

/**
 * 创建表或索引
 * @param createObjectStoreName
 * @param keyPath
 * @param indexsArr
 */
function createObjectStorage(createObjectStoreName , keyPath , indexArr) {
	var objectStore = idb.createObjectStore(createObjectStoreName, { keyPath: keyPath, autoIncrement: true });
	if(typeof indexArr == "object") {
		for(var i=0;i<indexArr.length;i++) {
			var inx = indexArr[i];
			objectStore.createIndex(createObjectStoreName + "_" + inx, inx, { unique: false });			
		}
		
		for(var i=0;i<5;i++) {
			var teD = {name:"name"+i,age:(i+1)};
			objectStore.add(teD);
		}
	} else {
		console.err("当前对象" + indexArr + "不是数组类型");
	}
}

/**
 * 清除数据
 * @param storageName
 */
function clearObjectStorage(storageName) {
	var tx = idb.transaction(storageName, 'readwrite');
    var store = tx.objectStore(storageName); 
    store.clear();
}

function deleteObjectStorage(storageName) {
	if(idb.objectStoreNames.contains(storageName)){
		idb.deleteObjectStore(storageName);
	}
}

//************************LocalStorage********************************
/**
 * 本地存储设置值
 * @param key
 * @param value
 */
function setLocalStorageItem(key , value) {
	localStorage.setItem(key,value);
}

/**
 * 本地存储获取值
 * @param key
 * @returns
 */
function getLocalStorageItem(key) {
	return localStorage.getItem(key);
}

/**
 * 删除本地存储
 * @param key
 */
function removeLocalStorageItem(key) {
	localStorage.removeItem(key);
}

//************************SessionStorage********************************
/**
 * 设置sessionStorage
 * @param key
 * @param value
 */
function setSessionStorageItem(key , value) {
	sessionStorage.setItem(key , value);
}

/**
 * 获取sessionStorage元素
 * @param key
 * @returns
 */
function getSessionStorageItem(key) {
	return sessionStorage.getItem(key);
}

/**
 * 删除内容
 * @param key
 * @returns
 */
function removeSessionStorageItem(key) {
	return sessionStorage.removeItem(key);
}

//###########################################################################################
//##########################################本地存储(end)####################################
//###########################################################################################

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
            if(objs[i].disabled == true) {
            	objs[i].checked = false;
    		}
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
		bootbox.confirm("确定删除选中的记录?", 
			function(result) { 
				if (result) { 											// 点击了确定按钮
					var symbolFlag = "?";
					if(delUrl.indexOf("?") != -1) {						// 表示有问号
						symbolFlag = "&";
					}
					window.location.href = delUrl + symbolFlag + "ids=" + ids;
				}
			}
		);
	} else {
		bb_alert("请选择要删除的记录！");
	}
}

/**
 * 删除某个记录
 * @param url
 */
function delById(url) {
	bootbox.confirm("确定删除选中的记录?", 
		function(result) { 
			if (result) { 											// 点击了确定按钮
				window.location.href = url;
			}
		}
	);
}

/**
 * 默认的bootbox弹出提示框
 * @param msg
 */
function bb_alert(msg) {
	bootbox.alert({  
        buttons: {  
            ok: {  
                 label: "朕知道了，退下吧！"  
             }  
         },  
         message: msg
     });
}

/**
 * 模态框随机效果
 */
function randomModal(title,subTitle,contentHTML) {
	var playeffectNum = (Math.ceil(Math.random()*16));					// 显示效果共16种特效
	var colors = ["greensea","redbrown","blue","drank","hotpink","dutch","slategray","amethyst","orange","green","red","cyan"];		// 显示颜色集合 
	var color = colors[Math.floor(Math.random()*colors.length)];		// 机选某一颜色
	getEleById("modal-defaultdiv").setAttribute("class","md-modal colorize-overlay md-effect-" + playeffectNum + " md-" + color);   // 为模态框div渲染颜色及特效
	
	if(title == "" || title == null || title == undefined) title = "请输入主标题";
	getEleById("modal-defaultdiv-title").innerText = title;
	if(subTitle == "" || subTitle == null || subTitle == undefined) subTitle = "请输入副标题";
	getEleById("modal-defaultdiv-subtitle").innerText = subTitle;
	if(contentHTML == "" || contentHTML == null || contentHTML == undefined) contentHTML = "请输入详细的内容呀，否则我怎么显示呀，你个笨蛋！";
	getEleById("modal-defaultdiv-content").innerHTML = contentHTML;
}

/**
 * 模态框定制
 */
function customModal(title,subTitle,contentHTML,colorNum,color) {
	getEleById("modal-defaultdiv").setAttribute("class","md-modal colorize-overlay md-effect-" + colorNum + " md-" + color);   // 为模态框div渲染颜色及特效	
	if(title == "" || title == null || title == undefined) title = "请输入主标题";
	getEleById("modal-defaultdiv-title").innerText = title;
	if(subTitle == "" || subTitle == null || subTitle == undefined) subTitle = "请输入副标题";
	getEleById("modal-defaultdiv-subtitle").innerText = subTitle;
	if(contentHTML == "" || contentHTML == null || contentHTML == undefined) contentHTML = "请输入详细的内容呀，否则我怎么显示呀，你个笨蛋！";
	getEleById("modal-defaultdiv-content").innerHTML = contentHTML;
}

/**
 * 关闭模态框
 */
function closeDefaultModal() {
	getEleById("modal-defaultdiv-title").innerText = "";
	getEleById("modal-defaultdiv-subtitle").innerText = "";
	getEleById("modal-defaultdiv-content").innerHTML = "";
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
 * 关闭模态窗口页面
 */
function closeModal() {
	$("#myModal").hide();
	window.location.reload();
}

/**
 * 验证表单基础方法
 * @param formId
 * @returns {Boolean}
 */
function valForm(formId) {
	if(!$("#"+formId).parsley().validate()) {					// 先验证表单数据
		return false;
	}
}

/**
 * 数据导出全选方法
 */
function chooseAllPro(e) {
	var proCheckbox = getElesByName("proCheckbox");
	if(e.checked) {					//表示为选中    			
		for(var i=0;i<proCheckbox.length;i++) {
			proCheckbox[i].checked = true;
		}
	} else {						//表示为非选中
		for(var i=0;i<proCheckbox.length;i++) {
			proCheckbox[i].checked = false;
		}
	}
}

/**
 * 数据导出开始导出数据 
 */
function beginExportData(form) {
	if(valForm(form) == false){
		return false;
	}
	getEleById("infoDiv").innerHTML = "&nbsp;";
	
	var columns = "";
	var proCheckbox = getElesByName("proCheckbox");
	for(var i=0;i<proCheckbox.length;i++) {
		if(proCheckbox[i].checked) {		//将选中的值拼出来
			columns += proCheckbox[i].value + ","; 
		}
	}
	if(columns != "") {
		columns = columns.substring(0,columns.length-1);
		getEleById(form).action += "?columns="+encodeURI(encodeURI(columns));
		getEleById(form).submit();
		getEleById("exportBtn").setAttribute("class","btn btn-primary disabled");
		getEleById("infoDiv").innerHTML = "<font color='#009966'>正在为您导出数据，稍后请检查下载的文件。</font>";
		return true;
	} else {
		getEleById("infoDiv").innerHTML = "<font color='red'>请选择要导出的列</font>";
		return false;
	}	
}

/**
 * 数据导入检查导入表单
 */
function uploadFile(formId) {
	var importFile = $("#importExcelFileAll").val().Trim();
	if(importFile.length != 0) {																	//表示文件不空
		var extendName = importFile.substring(importFile.indexOf(".")+1);							//得到文件扩展名
		if(extendName.toLowerCase() != "xls") {														//表示上传的不是xls文件
			getEleById("fileDivInfo").innerHTML = "<font color='red'>请上传正确的模板文件</font>";	
		} else {																					//表示上传了xls文件
			getEleById("fileDivInfo").innerHTML = "<font color='#009966'>数据上传中，请您耐心等候...</font>";
			getEleById(formId).submit();
			
			getEleById("importBtn").setAttribute("class","btn btn-primary disabled");
			getEleById("importBtn").title = "正在导入数据...";
		}   			    			
	} else {																						//表示文件为空
		getEleById("fileDivInfo").innerHTML = "<font color='red'>请选择要上传的文件</font>";
	}	
}

/**
 * 获取uri
 */
function getUrlRelativePath() {
　　var url = document.location.toString();
　　var arrUrl = url.split("//");

　　var start = arrUrl[1].indexOf("/");
　　var relUrl = arrUrl[1].substring(start);//stop省略，截取从start开始到结尾的所有字符

　　if(relUrl.indexOf("?") != -1){
　　　　relUrl = relUrl.split("?")[0];
　　}
　　return relUrl;
}

/**
 * 拼装选择器隐藏域值
 * @param e
 * @param hiddenId
 */
function packageSelectValues(e , hiddenId) {
	var selVal = getEleById(e.id);
	var finalValue = "";
	for(var i=0;i<selVal.options.length;i++) {
		if(selVal.options[i].selected) {
			if(finalValue.length != 0) {
				finalValue += ","+selVal.options[i].value;
			} else {
				finalValue += selVal.options[i].value;
			}
		}
	}
	getEleById(hiddenId).value = finalValue;
}

/**
 * 验证数据唯一性，仅供表单页面即含有id隐藏域控件使用
 * @param columnName
 * @param columnValue
 * @param tableName
 * @param callBackShowDiv
 */
function validateDataUnique(columnName , columnValue , tableName , callBackShowDivId) {
	if(columnValue.Trim().length == 0) return;
	getEleById(callBackShowDivId).innerHTML = "";
	var id = getEleById("id").value;
	$.ajax({   
		type:"post",     
		url:getRootPath()+"/validateDataUnique",           
		dataType:"text",             
		data:{"id":id,"columnName":columnName,"columnValue":columnValue,"tableName":tableName},        
		success:function(data){
			if(data == '1') 				// 表示重复
				getEleById(callBackShowDivId).innerHTML = "当前输入内容已经存在";
			else
				getEleById(callBackShowDivId).innerHTML = "";
		}
	});
}

/**
 * 在js 文件里import 闭包的时候使用，预留方法，如果以后做前段封装的时候，引用到闭包的概念时，在做使用
 */
var jsContainer = [];
function $importjs(_path){
	var flag = true;
	for(var i = 0; I < jsContainer.length; I++) {
		if(jsContainer[I] == _path) {
			flag = false;
		}
	}
	if(flag) {
		document.write("<script type='text/javascript' src='" + _path + "'></scr" + "ipt>");
		jsContainer.push(_path);
	}
}

function $importcss(_path){
	document.write("<link rel='STYLESHEET' type='text/css' href='" + _path + "'>");
}



//十六进制颜色值域RGB格式颜色值之间的相互转换  

//-------------------------------------  
//十六进制颜色值的正则表达式  
var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;  
/*RGB颜色转换为16进制*/  
String.prototype.colorHex = function(){  
  var that = this;  
  if(/^(rgb|RGB)/.test(that)){  
      var aColor = that.replace(/(?:||rgb|RGB)*/g,"").split(",");  
      var strHex = "#";  
      for(var i=0; i<aColor.length; i++){  
          var hex = Number(aColor[i]).toString(16);  
          if(hex === "0"){  
              hex += hex;   
          }  
          strHex += hex;  
      }  
      if(strHex.length !== 7){  
          strHex = that;    
      }  
      return strHex;  
  }else if(reg.test(that)){  
      var aNum = that.replace(/#/,"").split("");  
      if(aNum.length === 6){  
          return that;      
      }else if(aNum.length === 3){  
          var numHex = "#";  
          for(var i=0; i<aNum.length; i+=1){  
              numHex += (aNum[i]+aNum[i]);  
          }  
          return numHex;  
      }  
  }else{  
      return that;      
  }  
};  

//-------------------------------------------------  

/*16进制颜色转为RGB格式*/  
String.prototype.colorRgb = function(){  
  var sColor = this.toLowerCase();  
  if(sColor && reg.test(sColor)){  
      if(sColor.length === 4){  
          var sColorNew = "#";  
          for(var i=1; i<4; i+=1){  
              sColorNew += sColor.slice(i,i+1).concat(sColor.slice(i,i+1));     
          }  
          sColor = sColorNew;  
      }  
      //处理六位的颜色值  
      var sColorChange = [];  
      for(var i=1; i<7; i+=2){  
          sColorChange.push(parseInt("0x"+sColor.slice(i,i+2)));    
      }  
      return "RGB(" + sColorChange.join(",") + ")";  
  }else{  
      return sColor;    
  }  
}; 

String.prototype.startWith=function(str){     
	var reg=new RegExp("^"+str);     
	return reg.test(this);        
}  

String.prototype.endWith=function(str){     
	var reg=new RegExp(str+"$");     
	return reg.test(this);        
}

/**
 * 图片附件常用工具方法
 */
var curPicWinIndex;
/**
 * 关闭窗口方法
 */
function closeLayer(){
	layer.close(curPicWinIndex);
}