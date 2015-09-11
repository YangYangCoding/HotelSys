<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Invalid Discount Set</title>
</head>
<body>
	<H1>Your discount set Conflicts !!!! </H1>
	<H1>If you still want to set this discount, please press 'set' button below </H1>
	<H1>If you don't want to set this discount, please press 'back to set discount' button below</H1>
<form action="owner" method="post">
<input type="hidden" name="action" value="invaliddiscount">
<input type="submit" name="button" value="set">
<input type="submit" name="button" value="back to set discount">
</form>
</body>
</html>
