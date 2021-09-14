<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

	${result}
	
	<hr>
	
	<c:if test="${role == 'admin'}">
		<a href="admin.jsp">Admin page</a>
	</c:if>

	<c:if test="${role == 'client'}">
		<a href="client.jsp">Client page</a>
	</c:if>



</body>
</html>