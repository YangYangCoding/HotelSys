<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Customer Pin Login</title>
</head>
<body>
	<h1>Customer BookingDetail Check</h1>
	<form action="PinLogin" method = "post">
	<c:if test="${not empty alarm}">
		<font color = "red">Sorry Sir,You are not able to double check your booking,because your all bookings
		are within 2 days!</font>
	</c:if>
	<c:if test="${not empty notfound}">
		<font color = "red">Sorry Wrong PIN</font>
	</c:if>
	
	<br>
	<input type="hidden" name="action" value="pinLogin">
	Please input your Pin
	<input type="text" name="pin">
	<br>
	<input type="submit" name="button" value="Login"/>
	</form>
</body>
</html>
