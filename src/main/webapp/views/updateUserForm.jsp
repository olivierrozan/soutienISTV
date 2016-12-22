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
        <form:form method="post" modelAttribute="modification" action="updateUserSubmit">
            <table border="1">
                <thead>
                    <tr>
                        <th><spring:message code="colonne.identifiant"/></th>
                        <th><spring:message code="colonne.nom"/></th>
                        <th><spring:message code="colonne.prenom"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${modification.listUsers}" var="course" varStatus="status">
                        <tr>
                            <td>
                                <c:out value="${course.id}"/>
                                <input type="hidden" name="listUsers[${status.index}].id" value="${course.id}"/>
                            </td>
                            <td>
                                <c:out value="${course.nom}"/>
                                <input type="hidden" name="listUsers[${status.index}].nom" value="${course.nom}"/>
                            </td>
                            <td>
                                <input type="text" name="listUsers[${status.index}].prenom" value="${course.prenom}"/><br/>
                                <b><i><form:errors path="listUsers[${status.index}].prenom" /></i></b>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="submit"/>
        </form:form>
    </body>
</html>