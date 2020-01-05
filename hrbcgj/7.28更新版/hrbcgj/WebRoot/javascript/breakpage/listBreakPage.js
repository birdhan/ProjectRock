/**
 * 通用的分页方法
 * @param url:执行分页方法的action请求
 * @param curPage:当前页码
 * @param pageSize:总共页数
 * @param formId:表单的id值，以便做查询和跳转的页操作相关
 * @param totalNum:每页显示的记录数
 */
function commonBreakPage(url,curPage,pageSize,formId,totalNum) {
	if(pageSize != "0") {//如果listSize不为0
		
		var paramEl = "?";
		if(url.indexOf("?") != -1) {
			paramEl = "&";
		}
		
		var page_div_html = "<div class=\"manu\">";
		
		//添加首页与上一页
		if(curPage == 1) {//如果当前页是第一页
			page_div_html += "<span class=\"disabled\" style='margin-left: 0px;'>首页</span>";
			page_div_html += "<span class=\"disabled\" style='margin-left: 0px;'>上一页</span>";
		} else {
			page_div_html += "<a href=\"javascript:searchDataList('"+url+paramEl+"page=1','"+formId+"')\">首页</a>";
			page_div_html += "<a href=\"javascript:searchDataList('"+url+paramEl+"page="+(curPage-1)+"','"+formId+"')\">上一页</a>";
		}
		
		
		//中间部分
		if(pageSize > 8) {//如果大于8
			if(curPage > 4) {
				if((pageSize - curPage) >= 3) {//向后取3个
					for(var i=(curPage-4);i<=(curPage+3);i++) {
						if(i == curPage) {//表示选中了当前页
							page_div_html += "<span class=\"current\" style='margin-left: 0px;'>"+i+"</span>";
						} else {
							page_div_html += "<a href=\"javascript:searchDataList('"+url+paramEl+"page="+i+"','"+formId+"')\">"+i+"</a>";
						}
					}
				} else {//如果不足3个，就是说向后取不到3个
					for(var i = curPage-4-(3-(pageSize - curPage));i<=pageSize;i++) {
						if(i == curPage) {//表示选中了当前页
							page_div_html += "<span class=\"current\" style='margin-left: 0px;'>"+i+"</span>";
						} else {
							page_div_html += "<a href=\"javascript:searchDataList('"+url+paramEl+"page="+i+"','"+formId+"')\">"+i+"</a>";
						}
					}
				}
			} else {//如果不大于4
				for(var i = 1;i<=8;i++) {
					if(i == curPage) {//表示选中了当前页
						page_div_html += "<span class=\"current\" style='margin-left: 0px;'>"+i+"</span>";
					} else {
						page_div_html += "<a href=\"javascript:searchDataList('"+url+paramEl+"page="+i+"','"+formId+"')\">"+i+"</a>";
					}
				}
			}
		} else {
			for(var i = 1;i<=pageSize;i++) {
				if(i == curPage) {//表示选中了当前页
					page_div_html += "<span class=\"current\" style='margin-left: 0px;'>"+i+"</span>";
				} else {
					page_div_html += "<a href=\"javascript:searchDataList('"+url+paramEl+"page="+i+"','"+formId+"')\">"+i+"</a>";
				}
			}
		}
		
		
		//添加尾页与下一页
		if(curPage == pageSize) {
			page_div_html += "<span class=\"disabled\" style='margin-left: 0px;'>下一页</span>";
			page_div_html += "<span class=\"disabled\" style='margin-left: 0px;'>尾页</span>";
		} else {
			page_div_html += "<a href=\"javascript:searchDataList('"+url+paramEl+"page="+(curPage+1)+"','"+formId+"')\">下一页</a>";
			page_div_html += "<a href=\"javascript:searchDataList('"+url+paramEl+"page="+pageSize+"','"+formId+"')\">尾页</a>";
		}
		//总页数及页面相应记录数
		page_div_html += " <font color='#5E5E5E' style=\"font-size:13px;\">共"+pageSize+"页 每页"+totalNum+"条 <input type='hidden' id='toPage' name='toPage' value='"+curPage+"' style='width:25px;height:20px;'/>"; // 跳到第<input type='text' id='toPage' name='toPage' value='"+curPage+"' style='width:25px;height:20px;'/>页</font> <a href='javascript:goToPage(\""+url+"\",\""+formId+"\");'>GO</a>
		//page_div_html += " <font color='#036cb4'>共"+pageSize+"页 每页<input type='text' id='totalNum' name='totalNum' value='"+totalNum+"' style='width:25px;height:20px;'/>条 跳到第<input type='text' id='toPage' name='toPage' value='"+curPage+"' style='width:25px;height:20px;'/>页</font> <a href='javascript:goToPage(\""+url+"\",\""+formId+"\");'>GO</a>";
		
		page_div_html += "</div>";
		page_div_html += "<input type='hidden' id='tempPageSize' value='"+pageSize+"'/>";//存放总页数
		page_div_html += "<input type='hidden' id='totalNum' value='"+totalNum+"'/>";
		var page_div = $(document.createElement('div')).addClass('page').html(page_div_html);//将拼好的串
		document.write(page_div.context.outerHTML);
	}	
}

/**
 * 跳转到固定的页面
 * @param url
 */
function goToPage(url,formId) {
	var toPage = document.getElementById("toPage").value;
	var paramFlag = "?";
	if(url.indexOf("?") != -1) {
		paramFlag = "&";
	}
	searchDataList(url+paramFlag+"page="+toPage,formId);
}

/**
 * 分页查询方法
 * @param url
 * @param formId
 */
function searchDataList(url,formId) {
	document.getElementById(formId).action = url;
	if(totalNumIsInt() &&  toPageIsInt()) {//如果验证没有问题
		document.getElementById(formId).submit();
	}
}

/**
 * 判断每页条数是不是整数
 * @param str
 * @returns {Boolean}
 */
function totalNumIsInt() {
	var totalNum = document.getElementById("totalNum").value;
    var result=totalNum.match(/^(-|\+)?\d+$/);
    if(result==null) {
    	alert("每页条数应该输入整数！");
    	document.getElementById("totalNum").select();
    	return false;
    }
    return true;
}

/**
 * 判断跳转页是不是整数
 * @param str
 * @returns {Boolean}
 */
function toPageIsInt() {
	var toPage = document.getElementById("toPage").value;
    var result=toPage.match(/^(-|\+)?\d+$/);
    if(result==null) {
    	alert("跳转页应该输入整数！");
    	document.getElementById("toPage").select();
    	return false;
    } else {
    	if(parseInt(toPage) < 1) {
    		alert("跳转页应该输入大于0的整数！");
    		return false;
    	}
    	var tempPageSize = parseInt(document.getElementById("tempPageSize").value);
    	if(parseInt(toPage) > tempPageSize) {//如果要跳转的页面大于总共的页数返回错误
    		alert("要跳转的页数不能大于总页数，当前总页数为:"+tempPageSize);
    		document.getElementById("toPage").select();
    		return false;
    	}
    }
    return true;
}
