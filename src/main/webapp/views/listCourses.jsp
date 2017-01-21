<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<div class="jumbotron">
	
    <table class="table table-striped">
        <h4>Cours</h4>
        <thead>
            <tr>
                <th>ID</th>
                <th>LIBELLE</th>
                <th>DATE DERNIERE MODIFICATION</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listCours}" var="cours">
                <tr>
                    <td><c:out value="${cours.idCours}"/></td>
                    <td><c:out value="${cours.libelleCours}"/></td>
                    <td><fmt:formatDate value="${cours.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></td>
                    <td>
                        <c:url value="/detailsCourse" var="url">
                            <c:param name="id" value="${cours.idCours}"/>
                        </c:url>
                        <a href="${url}">
                            <spring:message code="details.users" />
                        </a>
                    </td>
                    <td>
                        <c:url value="/updateCours" var="url">
                            <c:param name="id" value="${cours.idCours}"/>
                        </c:url>
                        <a href="${url}">
                            <spring:message code="modifier.libelle" />
                        </a>
                    </td>
                    <td>
                        <c:url value="/deleteCours" var="url">
                            <c:param name="id" value="${cours.idCours}"/>
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