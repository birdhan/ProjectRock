<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- jquery核心环境 -->
<script src="${ctx}/javascript/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<%--<script src="${ctx}/javascript/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>--%>

<!-- jquery验证 -->
<script src="${ctx}/javascript/jquery/validaform/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/javascript/jquery/validaform/jquery.validate.js" type="text/javascript"></script>

<!-- jqueryui核心环境 -->
<script src="${ctx}/javascript/jquery/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx}/javascript/jquery/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<!-- 单独引入菜单树 -->
<script src="${ctx}/javascript/jquery/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>

<!-- 日期控件 -->
<script type="text/javascript" src="${ctx}/javascript/My97DatePicker/WdatePicker.js"></script>

<!-- kindeditor -->
<script src="${ctx}/javascript/kindeditor/kindeditor-min.js" type="text/javascript"></script>
<script src="${ctx}/javascript/kindeditor/lang/zh_CN.js" type="text/javascript"></script>

<!-- 通用的js方法  -->
<script src="${ctx}/javascript/common/common_function.js" type="text/javascript"></script>
<script src="${ctx}/javascript/common/map.js" type="text/javascript"></script>
<script src="${ctx}/jscomponents/basefunction/ajax/baseAjax.js" type="text/javascript"></script>

<script language="javascript">
$.ligerDialog.info = function (content, title, type, callback)
{
    content = content || "";
    if (typeof (title) == "function")
    {
        callback = title;
        type = null;
    }
    else if (typeof (type) == "function")
    {
        callback = type;
    }
    var btnclick = function (item, Dialog, index)
    {
        Dialog.close();
        if (callback)
            callback(item, Dialog, index);
    };
    p = {
        content: content,allowClose:false
    };
    if (typeof (title) == "string" && title != "") p.title = title;
    if (typeof (type) == "string" && type != "") p.type = type;
    $.extend(p, {
        showMax: false,
        showToggle: false,
        showMin: false
    });
    return $.ligerDialog(p);
};

/**
 * 格式化钱
 */
function fmoney(s, n) {   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
} 
</script>