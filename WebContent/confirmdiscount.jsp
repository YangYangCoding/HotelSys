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
<H1>Your discount information is below, click 'confirm' button to confirm or you can cancel it via click 'back to set discount' button</H1>
<table border="1px"> 
<tr>


<th>Hotel Name</th><th>Discount</th><th>Type of Room</th><th>Start Date</th><th>End Date</th></tr>
<c:forEach items="${validdisc}" var="vadis"> 
<tr>
<td>${vadis.hotelname}</td><td>${vadis.discount}</td><td>${vadis.type}</td><td>${vadis.startdate}</td><td>${vadis.enddate}</td>
</tr>
</c:forEach> </table>
 
 
 
<form action="owner" method="post">
<input type="hidden" name="action" value="confirmdiscount">
<input type="submit" name="button" value="Confirm">
<input type="submit" name="button" value="back to set discount">
</form>
</body>

</html>
