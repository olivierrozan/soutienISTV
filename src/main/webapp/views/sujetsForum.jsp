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
    	
		<h3>${ libelleTheme } : Les sujets</h3>
		<form:form method="post" modelAttribute="sujet" action="createSujetSubmit" class="form center-block">         
	        <div class="form-group">
	        	<input type="hidden" name="fk_idTheme" value="${ idTheme }"/>
	        	<input type="hidden" name="theme.libelleTheme" value="${ libelleTheme }"/>
	        	
	        	<b><i class="error"><form:errors path="titre" cssclass="error"/></i></b>
	        	<input type="text" name="titre" placeholder="Titre Sujet" class="form-control input-lg"/>
	        	<button type="submit" class="btn btn-primary btn-sm">
	        		<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
	        		Ajouter sujet
				</button>
	        </div>											         
	    </form:form>
		<table class="table table-striped">
			<tr>
				<th>TITRE</th>
				<th>ETAT</th>
				<th>DATE DERNIER POST</th>
				<th>DERNIER POST ECRIT PAR</th>
			</tr>
			
			<c:forEach items="${sujets}" var="sujet">	            	            
				<tr>
					<td>
					
					<form:form method="post" modelAttribute="onePost" action="post" class="form center-block">
			         	<input type="hidden" name="id" value="${ sujet.id }"/>
			         	<input type="hidden" name="titre" value="${ sujet.titre }"/>
			         	<input type="hidden" name="etat" value="${ sujet.etat }"/>
			         	<input type="submit" class="btn btn-primary" value="${sujet.titre}"/><br />
	     			</form:form>
					</td>
					<td>${sujet.etat}</td>
					<td><fmt:formatDate value="${sujet.user.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></td>
					<td>${sujet.user.login}</td>
				</tr>
	        </c:forEach>
		</table>
		
	
    </div>
		</section>
<jsp:include page="footer.jsp" />