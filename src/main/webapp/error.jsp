<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isErrorPage="true"%>

<%@ include file="jspf/header.jspf"%> 

<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='error' scope='session' />

<div id="caption"><fmt:message key='label.errorPageHeader'/></div>

<h2><%=exception.getMessage() %></h2>
<p><i>Ваша страница сгенерировала ошибку : <%= exception.toString() %> </i>


<%@ include file="jspf/footer.jspf"%>