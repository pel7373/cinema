<%@ include file="jspf/header.jspf"%> 

<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='user-form' scope='session' />

<c:if test="${!empty sessionScope['user']}">
	<div id="caption"><fmt:message key='label.editUser'/></div>

	<c:if test="${sessionScope['alert-message'] == 'userWithEnteredEmailAlreadyExists'}">	
		<p><fmt:message key='message.userWithEnteredEmailAlreadyExists'/>
		<p>
	</c:if>	
	
	<form action="updateUser" method="post">
		<input type="hidden" name="id" value="<c:out value='${user.id}'/>" />
		<label><fmt:message key='label.yourEmail'/></label>&nbsp;<input name="email" value="<c:out value='${user.email}' />" required="required">	
		<input type="hidden" name="previousEmail" value="<c:out value='${user.email}'/>" />
		<p><label><fmt:message key='label.yourPassword'/></label>&nbsp;<input name="password" type="password" value=""><br>
		<input type="hidden" name="previousPassword" value="<c:out value='${user.password}'/>" />
		<br><small><i>* <fmt:message key='label.leavePasswordEmpty'/></i></small>	
		<p><label><fmt:message key='label.signUpName'/></label>&nbsp;<input name="name" value="<c:out value='${user.name}' />" required="required"><br>
		<p><label><fmt:message key='label.UserRole'/></label>&nbsp;
			<INPUT TYPE="radio" name="role" value="1"/><fmt:message key='label.admin'/>
			<INPUT TYPE="radio" NAME="role" VALUE="2" checked="checked"/><fmt:message key='label.user'/>
		<br>	
		<input type="submit" value="<fmt:message key='label.enter'/>">
	</form>
</c:if>

<c:if test="${empty sessionScope['user']}">
	<div id="caption"><fmt:message key='label.addNewUser'/></div>

	<c:if test="${sessionScope['alert-message'] == 'userWithEnteredEmailAlreadyExists'}">	
		<p><fmt:message key='message.userWithEnteredEmailAlreadyExists'/>
		<p>
	</c:if>	

	<form action="addNewUser" method="post">
		<label><fmt:message key='label.yourEmail'/></label>&nbsp;<input name="email" value="<fmt:message key='label.yourEmail'/>" required="required"><br>	
		<label><fmt:message key='label.yourPassword'/></label>&nbsp;<input name="password" type="password" value="" required="required"><br>	
		<label><fmt:message key='label.signUpName'/></label>&nbsp;<input name="name" value="<fmt:message key='label.signUpName'/>" required="required"><br>
		<label><fmt:message key='label.UserRole'/></label>&nbsp;
			<INPUT TYPE="radio" name="role" value="1"/><fmt:message key='label.admin'/>
			<INPUT TYPE="radio" NAME="role" VALUE="2" checked="checked"/><fmt:message key='label.user'/>
		<br>	
		<input type="submit" value="<fmt:message key='label.enter'/>">
	</form>
</c:if>

<%@ include file="jspf/footer.jspf"%>