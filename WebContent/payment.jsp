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
<c:if test="${not empty alarm2}">
		<font color = "red">Sorry Sir,Your email is wrong!</font>
	</c:if>

<input type="hidden" name="action" value="payment">
<table border="1px"> 
<tr>


<th>Hotel Name</th><th>HotelID</th><th>Room Type</th><th>Number of Room</th><th>Price per room per night</th><th>Check in Date</th><th>Check out Date</th></tr>
<c:forEach items="${hotel}" var="hotels"> 
<tr>
<td>${hotels.hotelname}</td><td>${hotels.hotelid}</td><td>${hotels.type}</td><td>${hotels.nbofroom}</td><td>${hotels.price}</td><td>${hotels.checkin}</td><td>${hotels.checkout}</td>
</tr>
</c:forEach> </table>
<br>

<br> 
Total price includes extra bed fee if you selected it in forward page <br>
(Single Room can not add extra bed)<br>
Total Price: ${total} <br>
<br>
Enter your Personal details here:<br>

Your Email:<input type="text" name="email"/> <br>


If you want to book the room as you selected above and confirm your personal information, please press button 'confirm', <br>
else you can back to search page via button 'Back to search'.<br>
<input type='submit' name='button' value='Confirm'/>
<input type='submit' name='button' value='Back to search'/>
</form>

</body>
</html>
