<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="owner" method="post">
	<H1>Your discount has been set, this is your discount information below: </H1>
	<table border="1px"> 
<tr>


<th>Hotel Name</th><th>Discount</th><th>Type of Room</th><th>Start Date</th><th>End Date</th></tr>
<c:forEach items="${validdisc}" var="vadis"> 
<tr>
<td>${vadis.hotelname}</td><td>${vadis.discount}</td><td>${vadis.type}</td><td>${vadis.startdate}</td><td>${vadis.enddate}</td>
</tr>
</c:forEach> </table>
<H1>You can back to view all room information via button below: </H1>
<input type="submit" name="button" value="back to room info">
<input type="hidden" name="action" value="setdone">
</form>
</body>
</html>
