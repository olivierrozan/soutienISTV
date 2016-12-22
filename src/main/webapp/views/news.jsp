<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>NEWS</title>
	</head>
	
	<body>
		<h1>News : ${uid}</h1>
		
		<c:url value="/logout" var="hiAction" />
    	<a href="${hiAction}">Logout</a>
    	
    	<c:url value="/news2" var="hiAction" />
    	<a href="${hiAction}">News2</a>
		
		<p>${ connected }</p>
	</body>
</html>