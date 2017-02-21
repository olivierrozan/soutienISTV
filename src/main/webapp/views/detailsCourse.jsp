<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
		<div class="container">
			<div class="col-sm-12">
				<a><button class="btn btn-lg bouton" onclick="history.back(-1)">Retour</button></a>
				
				<h1>DETAILS COURS</h1>
				
				<div class="jumbotron">
					
					<h3>${ courseDetail.libelleCours }</h3>
					<h4>Ecrit par : ${ courseDetail.user.login }</h4>
					<h4>Le <fmt:formatDate value="${courseDetail.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></h4>
					<h4>Vu ${ courseDetail.nbVues } fois</h4><br />
					
					<c:forEach items="${paragraphes}" var="p">	            	            
		    			<c:choose>
		    				<c:when test="${ p.texte!=null }">
		    					<p>${ p.texte }</p>
		    				</c:when>
		    				<c:when test="${ p.imageLocation!=null }">
		    					<img src="./include/images/cours/${ p.imageLocation }" alt="${ p.imageLocation }" /><br />
		    				</c:when>
		    			</c:choose>   			
		            </c:forEach> 
				</div>
			</div>
	    </div>
		</section>
<jsp:include page="footer.jsp" />