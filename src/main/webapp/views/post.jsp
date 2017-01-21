<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	
		<c:url value="/forum" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	
		<h1>${ titreSujet }</h1>
		
		<c:forEach items="${posts}" var="p">	            	            
			<p>${p.contenu}, le ${p.datePost}</p>
			
        </c:forEach>
		
    </div>
		</section>
<jsp:include page="footer.jsp" />