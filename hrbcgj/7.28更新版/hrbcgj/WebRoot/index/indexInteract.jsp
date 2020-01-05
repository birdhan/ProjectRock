<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--互动交流-->
<div class="hudongjiaoliu">
        <div class="hudongjiaoliu_title">互动交流</div>
        <div style="margin-top:10px;">
      		<c:choose>
				<c:when test="${fn:length(interactionList) >= 7}">
						<c:forEach items="${interactionList}" var="interactionList" varStatus="status" begin="0" end="6">
						 <li>
						 <a href="javascript:void(0)" onclick="openDetail('${interactionList.id }')" >
						 <c:choose>
								<c:when test="${fn:length(interactionList.title)>18}">
									${fn:substring(interactionList.title, 0, 18)  }...
								</c:when>
								<c:otherwise>
									${interactionList.title}
								</c:otherwise>
							</c:choose>
						 <span class="hudongjiaoliu_date"><fmt:formatDate value="${interactionList.reqtime }" pattern="yyyy-MM-dd"/></span></a></li>
						</c:forEach>
				</c:when>
			<c:otherwise>
						<c:forEach items="${interactionList}" var="interactionList" varStatus="status" begin="0" end="6">
						 <li>
						<a href="javascript:void(0)" onclick="openDetail('${interactionList.id }')" >
						<c:choose>
								<c:when test="${fn:length(interactionList.title)>18}">
									${fn:substring(interactionList.title, 0, 18)  }...
								</c:when>
								<c:otherwise>
									${interactionList.title}
								</c:otherwise>
							</c:choose>
						 <span class="hudongjiaoliu_date"><fmt:formatDate value="${interactionList.reqtime }" pattern="yyyy-MM-dd"/>
						 </span></a></li>
						</c:forEach>
			</c:otherwise>
      </c:choose>
      <script type="text/javascript">
      function openDetail(id){
		var url = "${ctx}/interactionDetail?sectionId=index&secondSectionId=default&id="+id;
		window.open(url);
	}
      </script>
        </div>
    </div>
</div>