<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	
		<c:url value="/home" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	
		<h1>FORUM</h1>
		<table class="table table-striped">
			<tr>
				<th>TITRE</th>
				<th>ETAT</th>
				<th>DATE DERNIER POST</th>
				<th>DERNIER POST ECRIT PAR</th>
			</tr>
			
			<c:forEach items="${sujets}" var="sujets">	            	            
				<tr>
					<td>
					<form:form method="post" modelAttribute="onePost" action="post" class="form center-block">
			         	<input type="hidden" name="id" value="${ sujets.id }"/>
			         	<input type="hidden" name="titre" value="${ sujets.titre }"/>
			         	<input type="submit" class="btn btn-primary" value="${sujets.titre}"/><br />
	     			</form:form>
					</td>
					<td>${sujets.etat}</td>
					<td>${sujets.user.dateDerniereModif}</td>
					<td>${sujets.user.login}</td>
				</tr>
				
	        </c:forEach>
		</table>
		
	
    </div>
		</section>
<jsp:include page="footer.jsp" />