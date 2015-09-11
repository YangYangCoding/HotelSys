<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Welcome</title>
</head>
<body>
	<H1>Please Search Hotels Below</H1>
	<form action="PinLogin" method="post">
	<input type="hidden" name="action" value="searchhotelagain">
	<br>
		<c:if test="${not empty invalidinput}">
			<font color ="red">Oops the ${input} is not valid</font>
		
		</c:if>
	<br>
	
	
	maximum price per room per night you would like to pay
	<input type="text" name="price"/><br>
	
	<input type='submit' name='button' value='Search'/>
	</form>
</body>
</html>
