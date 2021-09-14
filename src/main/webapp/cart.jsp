<%@ include file="jspf/header.jspf"%> 

<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='cart' scope='session' />

<h3>Cart!</h3>

<%@ include file="jspf/footer.jspf"%>