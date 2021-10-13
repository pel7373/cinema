<%@page import="com.cinema.dao.PersonDAO"%>
<%@page import="com.cinema.entity.Person"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<html>
<head>

</head>
<body>

	You are logged as ${currentUser.login}
	<p>Password ${currentUser.password}
	<p>Text ${text}
<p>
<a href="index.html">Вернуться на главную страницу</a>

</body>
</html>