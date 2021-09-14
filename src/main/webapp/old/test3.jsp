<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

	<select name="language">
		<c:forEach var="locale" items="${applicationScope.locales}">
			<option value="${locale}">${locale}</option>
		</c:forEach> 
	</select>
	
	<hr>

	<select name="language">
		<c:forEach var="locale" items="${locales}">
			<option value="${locale}">${locale}</option>
		</c:forEach> 
	</select>

</body>
</html>