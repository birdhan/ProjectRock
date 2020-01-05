<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglibs/cloud.tld" prefix="cloud"%>
<%@ taglib uri="/WEB-INF/taglibs/id2name.tld" prefix="id2name"%>
<%@ taglib uri="/WEB-INF/taglibs/struts-tags.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/taglibs/datadict.tld" prefix="datadict"%>

<!-- 设置根路径 -->
<c:set var="ctx" value="${pageContext.request.contextPath}"/>