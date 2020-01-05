/**
 * 计算页面布局宽高度
 */
function claLayoutWidthHeight() {
	if(document.getElementById('left')) document.getElementById('left').style.height = (document.documentElement.clientHeight - 88) + "px";
	if(document.getElementById('fold_nav')) document.getElementById('fold_nav').style.height = (document.documentElement.clientHeight - 40) + "px";
	if(document.getElementById('right')) document.getElementById('right').style.width = (document.documentElement.clientWidth - 198) + "px";	
	if(document.getElementById('right')) document.getElementById('right').style.height = (document.documentElement.clientHeight - 88) + "px";
	if(document.getElementById('right_iframe')) document.getElementById('right_iframe').style.height = (document.documentElement.clientHeight - 88) + "px";
//	document.getElementById('window').style.height=(document.documentElement.clientHeight)+"px";
}

window.onload = function() {
	claLayoutWidthHeight();
};// 窗体的总宽度;

function close1(){
	$('.diag').hide();
	$('.window').hide();
}