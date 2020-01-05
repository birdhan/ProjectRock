// JavaScript Document

function common() {
	document.getElementById('login').style.height = document.documentElement.clientHeight-20 + "px";
	document.getElementById('l_middle').style.height = (document.documentElement.clientHeight - 86) + "px";
	if (document.documentElement.clientHeight - 86 - 520 < 0) {
		document.getElementById('blank').style.height = 0 + "px";
	} else {
		document.getElementById('blank').style.height = (document.documentElement.clientHeight - 86 - 520) / 2 + "px";
	}
}
window.onload = function() {
	common();
};