<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--通知搜索-->
<div class="inform">
	<div class="inform_width">
		<div class="gonggao_top">
			<marquee onmouseout=this.start() onmouseover=this.stop()>
				<span id="tongzhi" onmouseout=this.parent().stop()>通知公告：</span>
				<a href="#" style="color:black;">${gsggxw_articleList[0].name}</a>
			</marquee>
		</div>
		<div class="serach">
			<input type="text" placeholder="请输入搜索内容" /> <a><img
				src="${ctx }/images/index/serach.png" /> </a>
		</div>
	</div>
</div>