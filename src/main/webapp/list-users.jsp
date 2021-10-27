<%@ page import="java.util.*" %>
<%@ include file="jspf/header.jspf"%> 

<%-- Set session-scoped variable to track the view user is coming from.
     This is used by the language mechanism in the Controller so that
     users view the same page when switching between English and Russian. --%>
<c:set var='view' value='list-users' scope='session'/>


<%  
int listOfUsersLinesPerPage = 5;  
int listOfUsersStartLine = 1; //listOfUsers is displayed from (pageid) to (pageid + linesPerPage - 1)  

if (session.getAttribute("pageListOfUsers") != null) {
	String s = (String)session.getAttribute("pageListOfUsers");
	if (s != null) {
		listOfUsersStartLine = Integer.parseInt(s);
	}
} 

if (request.getParameter("pageListOfUsers") != null) {
	listOfUsersStartLine = Integer.parseInt(request.getParameter("pageListOfUsers"));
	session.setAttribute("pageListOfUsers", request.getParameter("pageListOfUsers"));
} 
  
listOfUsersStartLine = --listOfUsersStartLine * listOfUsersLinesPerPage + 1;  

int sizeListOfUsers = ((List)session.getAttribute("listOfUsers")).size();
int quantityOfPagesListOfUsers = sizeListOfUsers / listOfUsersLinesPerPage;

if (sizeListOfUsers % listOfUsersLinesPerPage > 0) {
	quantityOfPagesListOfUsers++;
}

session.setAttribute("listOfUsersStartLine", listOfUsersStartLine);
session.setAttribute("listOfUsersLinesPerPage", listOfUsersLinesPerPage);
session.setAttribute("quantityOfPagesListOfUsers", quantityOfPagesListOfUsers);
%>    

<div id="caption"><fmt:message key='label.listOfUsers'/>
	<c:if test="${quantityOfPagesListOfUsers > 1}">
		&nbsp;(<fmt:message key='label.page'/> #<c:out value="${pageListOfUsers}"/>)	 
	</c:if>
</div>


<a href="user-form.jsp"><fmt:message key='label.addNewUser'/></a>
<p>

<!-- Line with links for other pages of list of users: pagination -->
<c:if test="${quantityOfPagesListOfUsers > 1}">
	|&nbsp;<fmt:message key='label.page'/>  
	<c:forEach begin="1" end="${quantityOfPagesListOfUsers}" var="i">
		<a href="list-users.jsp?pageListOfUsers=${i}">${i}</a> &nbsp; | &nbsp;  
	</c:forEach>
</c:if>

<center>
<table class="table">

	<caption>
		<tr>
			<th>#</th>
			<th><fmt:message key='label.numberInDatabase'/></th>
			<th>Email</th>
			<th><fmt:message key='label.name'/></th>
			<th><fmt:message key='label.userRole'/></th>
			<th></th>
			<th></th>
		</tr>
	</caption>
	<tbody>
		<c:forEach var="user" items="${listOfUsers}" varStatus="i">
			<tr>
				<c:if test="${(i.count >= sessionScope['listOfUsersStartLine']) && (i.count <= sessionScope['listOfUsersStartLine'] + sessionScope['listOfUsersLinesPerPage'] - 1) }">
					<td>
					<c:out value="${i.count}"/></td>
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
						<form method="Post" action="goToEditUser">
						        <input type="hidden" name="id" value="${user.id}" />
						        <input type='submit' value="<fmt:message key='label.edit'/>"/>
						</form>
					</td>
					<td>
						<c:if test="${user.id != sessionScope['id']}">
							<form method="Post" action="deleteUser">
							        <input type="hidden" name="id" value="${user.id}" />
							        <input type='submit' value="<fmt:message key='label.delete'/>" onclick="return confirm('<fmt:message key='label.areYouSureDeleteUser'/>')";'/>
							</form>
						</c:if>
						
						
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</tbody>
	
</table>

<!-- Line with links for other pages of list of users: pagination -->
<c:if test="${quantityOfPagesListOfUsers > 1}">
	|&nbsp;<fmt:message key='label.page'/>  
	<c:forEach begin="1" end="${quantityOfPagesListOfUsers}" var="i">
		<a href="list-users.jsp?pageListOfUsers=${i}">${i}</a> &nbsp; | &nbsp;  
	</c:forEach>
</c:if>
</center>

<%@ include file="jspf/footer.jspf"%>