<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
		<div class="container">
			<h1>${ libelle }</h1>
			<c:url value="/myCourses" var="url"></c:url>
    		<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	
			<c:forEach items="${paragraphes}" var="p">	            	            
    			<c:choose>
    				<c:when test="${ p.texte!=null }">
    					<p>${ p.texte }</p>
    				</c:when>
    				<c:when test="${ p.imageLocation!=null }">
    					<img src="${ p.imageLocation }" alt="${ p.imageLocation }" /><br />
    				</c:when>
    			</c:choose>   			
            </c:forEach>         
    	</div>
	</section>
<jsp:include page="footer.jsp" />