<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shop</title>
</head>
<body>

	<h1>Welcome to Shoping page!</h1>
	<h2>Leave us your email and go shoping</h2>
	
	<br>
	
	<form action="./Login" method="post">
		Email: <br>
		<input type="text" style="width: 300px; height: 30px;" required name="email">
		<br><br>
		<input type="submit" name="action" value="Login">
	</form>
	<br><br>
	<span style="color: red">${requestScope.msg}</span>
	
	

</body>
</html>