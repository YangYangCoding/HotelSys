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
<c:if test="${not empty alarm}">
		<font color = "red">Sorry,the number of room is not the same</font>
	</c:if>
<input type="hidden" name="action" value="hasroomres">
<table border="1px"> 
<tr>


<th>Hotel Name</th><th>HotelID</th><th>Number of Room Available</th><th>RoomType</th><th>Price per room per night</th><th>Select?</th><th>Number of room you want to book</th></tr>
<c:forEach items="${hotel}" var="hotels"> 
<tr>
<td>${hotels.hotelname}</td><td>${hotels.hotelid}</td><td>${hotels.nbofroom}</td><td>${hotels.type}</td><td>${hotels.price}</td><td><input type="checkbox" name="chose" value="${hotels.type}"/></td><td><input type="text" name="${hotels.type}"/></td>
</tr>
</c:forEach> </table>
 
<br> 
 


If you want to book the room as you selected above, please press button 'confirm', <br>
else you can back to search page via button 'Back to search'.
<input type='submit' name='button' value='Confirm'/>
<input type='submit' name='button' value='Back to search'/>
</form>

</body>
</html>
