<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="titre"/></title>
	</head>
	
	<body>
		<h1><spring:message code="libelle" arguments="${personne}"/></h1>
		
		<h3>Connexion : ${uid}</h3>
		<p>${ connected }</p>
		<!--<c:url value="/createUserForm" var="hiAction" />
    	<a href="${hiAction}">Click here !!!</a>-->
		
		<form:form method="post" modelAttribute="connexion" action="connexionSubmit">
            <spring:message code="creation.users.nom" />
            <form:input path="login"/>
            <b><i><form:errors path="login" cssclass="error"/></i></b><br>
            <spring:message code="creation.users.prenom"/>
            <form:input path="password"/>
            <b><i><form:errors path="password" cssclass="error"/></i></b><br>
            <input type="submit"/>
        </form:form>
	</body>
</html>