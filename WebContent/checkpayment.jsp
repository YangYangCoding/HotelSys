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
<form action="controller" method="post">
<input type="hidden" name="action" value="checkpayment">
<table border="1px"> 
<tr>


<th>Hotel Name</th><th>HotelID</th><th>Room Type</th><th>Number of Room</th><th>Price per room per night</th><th>Check in Date</th><th>Check out Date</th><th>Add extra bed?</th></tr>
<c:forEach items="${hotel}" var="hotels"> 
<tr>
<c:choose>
<c:when test="${hotels.type eq 'Single Room'}">
			<td>${hotels.hotelname}</td><td>${hotels.hotelid}</td><td>${hotels.type}</td><td>${hotels.nbofroom}</td><td>${hotels.price}</td><td>${hotels.checkin}</td><td>${hotels.checkout}</td><td>Can add extra bed for Single Room</td>
</c:when>
<c:otherwise>
	<td>${hotels.hotelname}</td><td>${hotels.hotelid}</td><td>${hotels.type}</td><td>${hotels.nbofroom}</td><td>${hotels.price}</td><td>${hotels.checkin}</td><td>${hotels.checkout}</td><td><input type="checkbox" name="extrabed" value="${hotels.type}"/></td>
</c:otherwise>
</c:choose>
</tr>
</c:forEach> </table>
<br>
<br> 

If you want to book the room as you selected above, please press button 'confirm', <br>
else you can back to search page via button 'Back to search'.<br>
<input type='submit' name='button' value='Confirm'/>
<input type='submit' name='button' value='Back to search'/>
</form>

</body>
</html>
