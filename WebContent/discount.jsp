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
<br>
		<c:if test="${not empty invalidinput}">
			<font color ="red">Oops the ${input} is not valid</font>
		
		</c:if>
	<br>
City you would like to set a discount
	<select name="cityoption">
	<option value="sydney">Sydney</option>
	<option value="brisbane">Brisbane</option>
	<option value="melbourne">Melbourne</option>
	<option value="adelaide">Adelaide</option>
	<option value="hobart">Hobart</option>	
	</select> <br>
Discount you want to set(Please follow this format:x.x)
<input type="text" name="dis"/> <br>
Room type you want to set a discount
<select name="roomtype">
	<option value="Single Room">Single Room</option>
	<option value="Twin Bed">Twin Bed</option>
	<option value="Queen">Queen</option>
	<option value="Executive">Executive</option>
	<option value="Suite">Suite</option>	
	</select> <br>
Start discount date(Please follow this format:yyyy-mm-dd)
	<input type="text" name="startdate"/> <br>
End	discount date(Please follow this format:yyyy-mm-dd)
	<input type="text" name="enddate"/> <br>

<br>
<input type="hidden" name="action" value="discount">

Confirm your discount infomartion by clicking button 'set',
If you want to back to view all information about room, click 'back to room info' button <br>
<input type="submit" name="button" value="set">
<input type="submit" name="button" value="back to room info">
</form>
</body>
</html>
