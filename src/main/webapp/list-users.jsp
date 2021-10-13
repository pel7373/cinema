<%@ include file="jspf/header.jspf"%> 

<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='list-users' scope='session' />

<div id="caption"><fmt:message key='label.listOfUsers'/></div>



<a href="user-form.jsp"><fmt:message key='label.addNewUser'/></a>
<p>
<table align="center" border="1" cellpadding="15" cellspacing="0">

	<thead>
		<tr>
			<th>Id</th>
			<th>Email</th>
			<th><fmt:message key='label.name'/></th>
			<th><fmt:message key='label.userRole'/></th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="user" items="${listOfUsers}">
			<tr>
				<td><c:out value="${user.id}"/></td>
				<td><c:out value="${user.email}"/></td>
				<td><c:out value="${user.name}"/></td>
				<td>
					<c:if test="${user.role == 1}">
						<fmt:message key='label.admin'/>
					</c:if>
					<c:if test="${user.role == 2}">
						<fmt:message key='label.user'/>
					</c:if>
				</td>
				<td>
<!--					<c:if test="${user != null}">
					
					</c:if>
-->
				<form method="Post" action="goToEditUser">
				        <input type="hidden" name="id" value="${user.id}" />
				        <input type='submit' value="Edit" />
				</form>
				</td>
				<td>
				<c:if test="${user.id != sessionScope['id']}">
					<form method="Post" action="deleteUser">
					        <input type="hidden" name="id" value="${user.id}" />
					        <input type='submit' value="Delete" />
					</form>
				</c:if>

				</td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>


<%@ include file="jspf/footer.jspf"%>