<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<div class="jumbotron">
	<table class="table table-striped">
        <h4>Users</h4>
        <thead>
            <tr>
                <th><spring:message code="colonne.identifiant"/></th>
                <th><spring:message code="colonne.nom"/></th>
                <th><spring:message code="colonne.prenom"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listUsers}" var="course">
                <tr>
                    <td>
                    	<img src="./include/images/users/${ course.avatar }" width="75px" height="75px" alt="${ course.avatar }"/>
                   	</td>
                    <td><c:out value="${course.nom}"/></td>
                    <td><c:out value="${course.prenom}"/></td>
                    <td>
                        <c:url value="/detailsUser" var="url">
                            <c:param name="id" value="${course.id}"/>
                        </c:url>
                        <a href="${url}">
                            <spring:message code="details.users" />
                        </a>
                    </td>
                    <td>
                        <c:url value="/deleteUser" var="url">
                            <c:param name="id" value="${course.id}"/>
                        </c:url>
                        <a href="${url}">
                            <spring:message code="supprimer.libelle" />
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    </div>
		</section>
<jsp:include page="footer.jsp" />