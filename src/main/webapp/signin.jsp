<%@ include file="jspf/header.jspf"%> 

<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='signin' scope='session' />

<!-- Sign In Form  -->
<div id="caption"><fmt:message key='label.signInWithEmail'/></div>
	<form action="signin" method="post">
		<label><fmt:message key='label.yourEmail'/></label>&nbsp;<input name="email" value=""><br>	
		<label><fmt:message key='label.yourPassword'/></label>&nbsp;<input name="password" type="password" value=""><br>	
		<input type="submit" value="<fmt:message key='label.enter'/>">
	</form>

<p>

<b><fmt:message key="label.newUser"/></b> &nbsp; <a href="signup.jsp"><fmt:message key="label.createAnAccount"/></a>




<%@ include file="jspf/footer.jspf"%>