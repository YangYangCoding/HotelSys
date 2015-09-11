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
<table border="1px"> 
<tr>


<th>Hotel Name</th><th>Location</th><th>Number of Occupied Room</th><th>Number of Available Room</th></tr>
<c:forEach items="${ownerroominfo}" var="owners"> 
<tr>
<td>${owners.hotelname}</td><td>${owners.location}</td><td>${owners.occproom}</td><td>${owners.avairoom}</td>
</tr>
</c:forEach> </table>
 
 
 
<form action="owner" method="post">
<input type="hidden" name="action" value="ownerroom">
<input type="submit" name="button" value="refresh">
<input type="submit" name="button" value="set discount">
</form>
</body>
</html>
