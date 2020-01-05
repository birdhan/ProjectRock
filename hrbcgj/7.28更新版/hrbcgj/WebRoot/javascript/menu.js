// JavaScript Document
/**
 * 菜单展开方法
 * @param oTarget
 */
function openMenu(oTarget){
	if($(oTarget).next().height()<=0){
		var a=$('.fold_nav>ul');
		for(i=0;i<a.length;i++){
			$(a[i]).animate({height:"0px"},'fast');
		}
		var b=$('li',$(oTarget).next()).length;
		$(oTarget).next().animate({height:b*28+"px"},'fast');
	} else {
	    $(oTarget).next().animate({height:"0px"},'fast');
	}
}

/**
 * 点击菜单后改变其样式
 */
$(function() {
	$('#child li a').click(function(){
		$('#child li a').removeClass('selected');
		$(this).addClass('selected');
	});
});

/**
 * 左侧菜单树展开收缩
 */
var a = 0;
function showHiddenMenu() {
	var ctx = getRootPath();
	if (a == 0) {
		$('#left').animate({
			"left" : -186
		}, "slow", function() {
			$("#right").width($(window).width() - 10);
			$('#newImg').attr('src', ctx + '/images/right_btn.jpg')
		});
		a = 1;
	} else {
		$('#left').animate({"left" : 0}, "slow");
		$("#right").width($(window).width() - 196);
		$('#newImg').attr('src', ctx + '/images/left_btn.jpg')
		a = 0;
	}
}