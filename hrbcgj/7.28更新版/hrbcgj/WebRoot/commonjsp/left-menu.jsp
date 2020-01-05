<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="left" id="left">
	<div class="fold_nav" id="fold_nav">
		<h2>${sc.leftName}</h2>
		${menuTree}
	</div>
	<!--fold_nav_end-->
	
	<div class="left_btn">
		<div class="arrow">
			<a href="#" onclick="showHiddenMenu()">
				<img id='newImg' src="${ctx}/images/left_btn.jpg" width="10" height="42" />
			</a>
		</div>
	</div>
</div>
<!--left_end-->