// JavaScript Document

function removeImg() {
	$(".tool .li_1").removeClass("li_1_hover");
	$(".tool .li_2").removeClass("li_2_hover");
	$(".tool .li_3").removeClass("li_3_hover");
	$(".tool .li_4").removeClass("li_4_hover");
	$(".tool .li_5").removeClass("li_5_hover");
	$(".tool .li_6").removeClass("li_6_hover");

}

$(function() {
	$(".tool .li_1").click(function() {
		removeImg();
		$(".tool .li_1").addClass("li_1_hover");
	});
});

$(function() {
	$(".tool .li_2").click(function() {
		removeImg();
		$(".tool .li_2").addClass("li_2_hover");
	});
});

$(function() {
	$(".tool .li_3").click(function() {
		removeImg();
		$(".tool .li_3").addClass("li_3_hover");
	});
});

$(function() {
	$(".tool .li_4").click(function() {
		removeImg();
		$(".tool .li_4").addClass("li_4_hover");
	});
});

$(function() {
	$(".tool .li_5").click(function() {
		removeImg();
		$(".tool .li_5").addClass("li_5_hover");
	});
});

$(function() {
	$(".tool .li_6").click(function() {
		removeImg();
		$(".tool .li_6").addClass("li_6_hover");
	});
});