<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
		<div class="container">
	
			<c:url value="/themeForum" var="url"></c:url>
	    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
	    	
			<h1>${ titreSujet }</h1>
			
			<c:forEach items="${posts}" var="p">	            	            
				<div class="post">
					<div class="col-sm-2 postUser">
						<p><img src="./include/images/users/${p.user.avatar}" width="75px" height="75px" alt="${p.user.avatar}"/></p>
						<p>écrit par : ${p.user.login}</p>
						<p>le <fmt:formatDate value="${p.datePost}" type="both" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></p>
					</div>
					
					<div class="col-sm-9 postContenu">
						<p>${p.contenu}</p>
					</div>
					<div class="clearfix"></div>
				</div>
	        </c:forEach>
			
			<div class="post">
				<div class="col-sm-2 postUser">
					<h4>Laisser un message</h4>
				</div>
				
				<div class="col-sm-9 postContenu">
					<form:form method="post" modelAttribute="postReply" action="post" class="form center-block">
			         	<input type="hidden" name="id" value="${ idSujet }"/>
			         	<input type="hidden" name="titre" value="${ titreSujet }"/>
			         	
			         	<textarea class="textareaPost" name="contenu" required></textarea>
			         	<input type="submit" class="btn btn-primary" value="Envoyer"/><br />
		   			</form:form>
				</div>
				<div class="clearfix"></div>
				
				
   			</div>
	    </div>
	</section>
<jsp:include page="footer.jsp" />