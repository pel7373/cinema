<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ page session="true" %>

<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="messages" />

<html lang="${param.lang}">
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta charset="utf-8">
</head>
<body>

 

<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">Назначение</th>
      <th scope="col">Подробности</th>
      <th scope="col">Выполнено</th>
    </tr>
  </thead>
  <tbody>
<form action="#" method="post" class="form">
  <input type="hidden" name="id" value="321321"/>
    <tr>
      <th scope="row">1</th>
      <td>Назначение</td>
      <td>Подробности</td>
      <td>
<button type="submit" name="command" value="confirmAppointment"
                    onclick='return confirm("Подтвердить выполнение")'>
                Удалить
            </button>
</td>
    </tr>
    </form>
  </tbody>
</table>


<div class="row">
	<aside class="col-sm-4">
<div class="card">
<article class="card-body">
<a href="" class="float-right btn btn-outline-primary">Sign up</a>
<h4 class="card-title mb-4 mt-1">Sign in</h4>
	 <form action="login" method="post">
    <div class="form-group">
    	<label>Your email</label>
        <input name="login" class="form-control" placeholder="Email" type="email">
    </div> <!-- form-group// -->
    <div class="form-group">
   <!--  	<a class="float-right" href="#">Forgot?</a> -->
    	<label>Your password</label>
        <input name="password" class="form-control" placeholder="******" type="password">
    </div> <!-- form-group// --> 
    <div class="form-group"> 
    <div class="checkbox">
    <!--   <label> <input type="checkbox"> Save password </label>-->
    </div> <!-- checkbox .// -->
    </div> <!-- form-group// -->  
    <div class="form-group">
        <button type="submit" class="btn btn-primary btn-block" value="Login"> Login  </button>
        
    </div> <!-- form-group// -->                                                           
</form>
</article>
</div> 
</aside>
</div>
<!-- card.// -->

<p>
<a href="index.jsp"><fmt:message key="label.back" /></a>
<%-- <a href="index.jsp">Возвратиться на главную страницу</a>
--%>
<p>

<form action="#" method="post">
    Name: <input name="name" />
    <br><br>
    Hours: <input name="hour" type="number" value = 9 min = 9 max = 22 />
    Minutes: <input name="minute" type="number" value=0 min = 0 max = 59 />
    <br></br>
    <input type="submit" value="Submit" />
</form>

<p>

<h2>Введем фильм:</h2>
	<form action="login" method="get">
		<input name="login" value="admin"><br>	
		<input name="password" type="password" value="adminpass"><br>	
		<input type="submit" value="Enter">
	</form>


 
<h2>Введем юзера:</h2>
	<form action="login" method="post">
		<input name="login" value="admin"><br>	
		<input name="password" type="password" value="adminpass"><br>	
		<input type="submit" value="Enter">
	</form>


<!-- 
<h2>Welcome!</h2>
	<form action="login" method="post">
		<input name="login" value="admin"><br>	
		<input name="password" type="password" value="adminpass"><br>	
		<input type="submit" value="Login">
	</form>
-->
</body>
</html>