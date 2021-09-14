<%@ include file="jspf/header.jspf"%> 

<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='signup' scope='session' />

<!-- Sign In Form  -->
<div id="caption"><fmt:message key='label.signUpWithEmail'/></div>
	<form action="signup" method="post">
		<label><fmt:message key='label.yourEmail'/></label>&nbsp;<input name="login" value=""><br>	
		<label><fmt:message key='label.yourPassword'/></label>&nbsp;<input name="password" type="password" value=""><br>	
		<label><fmt:message key='label.signUpName'/></label>&nbsp;<input name="name" value=""><br>	

		<input type="submit" value="<fmt:message key='label.enter'/>">
	</form>

<p>

<%@ include file="jspf/footer.jspf"%>