<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><spring:message code="titre.creation.elementUsers"/></title>
    </head>
    <body>
        <h3>${uid}</h3>
        
        <form:form method="post" modelAttribute="creation" action="createUserSubmit">
              <spring:message code="creation.users.nom" />
            <form:input path="nom"/>
            <b><i><form:errors path="nom" cssclass="error"/></i></b><br>
              <spring:message code="creation.users.prenom"/>
            <form:input path="prenom"/>
            <b><i><form:errors path="prenom" cssclass="error"/></i></b><br>
            <input type="submit"/>
        </form:form>
        <table border="1">
            <thead>
                <tr>
                    <th><spring:message code="colonne.identifiant"/></th>
                    <th><spring:message code="colonne.nom"/></th>
                    <th><spring:message code="colonne.prenom"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listUsers}" var="course">
                    <tr>
                        <td><c:out value="${course.id}"/></td>
                        <td><c:out value="${course.nom}"/></td>
                        <td><c:out value="${course.prenom}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>