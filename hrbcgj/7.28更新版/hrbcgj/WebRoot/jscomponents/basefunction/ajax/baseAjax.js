 /**
     * ajax封装
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
     * successfn 成功回调函数
     * errorfn 失败回调函数
     */
    jQuery.ajaxText=function(url, data, successfn, errorfn) {
        data = (data==null || data=="" || typeof(data)=="undefined")? {} : data;
        $.ajax({
            type: "post",
            data: data,
            url: url,
            dataType: "text",
            success: function(d){
            	if(successfn != null && successfn != undefined)
            		successfn(d);
            },
            error: function(e){
            	if(errorfn != null && errorfn != undefined)
            		errorfn(e);
            }
        });
    }; 
    
    
    /**
     * ajax封装成功弹出提示，提示信息后台传递过来
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{date: 'abc', state: '1'}
     */
    jQuery.ajaxPostLoading=function(url, data , sucMsg) {
        data = (data==null || data=="" || typeof(data)=="undefined")? {} : data;
        var _load = layer.load(1, {
                shade: [0.1,'#fff']
              });
        $.ajax({
            type: "post",
            data: data,
            url: url,
            dataType: "text",
            success: function(d){
                setTimeout(function(){
                    layer.close(_load);
                    if(sucMsg != null && sucMsg != undefined)
                    	layer.msg(sucMsg);
                    else
                    	layer.msg(d);
                  }, 1000);
            },
            error: function(e){
                setTimeout(function(){
                    layer.close(_load);
                    layer.msg("操作失败");
                  }, 1000);
            }
        });
    };
    
    /**
     * ajax封装成功弹出提示，提示信息后台传递过来
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{date: 'abc', state: '1'}
     * callBack 可自定义回调函数
     */
    jQuery.ajaxPostLoadingCallBack=function(url, data , callBack) {
        data = (data==null || data=="" || typeof(data)=="undefined")? {} : data;
        var _load = layer.load(1, {
            shade: [0.1,'#fff']
        });
        $.ajax({
            type: "post",
            data: data,
            url: url,
            dataType: "text",
            success: function(d){
                setTimeout(function(){
                    layer.close(_load);
                    if(callBack) {
                    	callBack(d);
                    }                    
                  }, 1000);
                
            },
            error: function(e){
                setTimeout(function(){
                    layer.close(_load);
                    layer.msg("操作失败");
                  }, 1000);
            }
        });
    };

    /**
     * ajax封装
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
     * successfn 成功回调函数
     * errorfn 失败回调函数
     */
    jQuery.ajaxJSON=function(url, data, successfn, errorfn) {
        data = (data==null || data=="" || typeof(data)=="undefined")? {} : data;
        $.ajax({
            type: "post",
            data: data,
            url: url,
            dataType: "json",
            success: function(d){
                successfn(d);
            },
            error: function(e){
                errorfn(e);
            }
        });
    };
    
    /**
     * ajax封装
     * url 发送请求的地址
     * successfn 成功回调函数
     * errorfn 失败回调函数
     */
    jQuery.ajaxGetText=function(url, successfn, errorfn) {
        $.ajax({
            type: "get",
            url: url,
            dataType: "text",
            success: function(d){
            	if(successfn != null && successfn != undefined)
            		successfn(d);
            },
            error: function(e){
            	if(errorfn != null && errorfn != undefined)
            		errorfn(e);
            }
        });
    };
    
    /**
     * ajax封装成功弹出提示，提示信息后台传递过来
     * url 发送请求的地址
     * data 发送到服务器的数据，数组存储，如：{date: 'abc', state: '1'}
     * callBack 可自定义回调函数
     */
    jQuery.ajaxPostLoadingCallBackParam=function(url, data , callBack,dataType) {
        data = (data==null || data=="" || typeof(data)=="undefined")? {} : data;
        var _load = layer.load(1, {
            shade: [0.1,'#fff']
        });
        $.ajax({
            type: "post",
            data: data,
            url: url,
            dataType: dataType,
            success: function(d){
                setTimeout(function(){
                    layer.close(_load);
                    if(callBack) {
                    	callBack(d);
                    }                    
                  }, 1000);
                
            },
            error: function(e){
                setTimeout(function(){
                    layer.close(_load);
                    layer.msg("操作失败");
                  }, 1000);
            }
        });
    };
    
