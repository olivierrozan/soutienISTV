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
	
		<c:url value="/home" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>   	
    	
		<h1>FORUM - Themes</h1>
		<c:choose>
			<c:when test="${ not empty ok }">
				<br /><b><i class="alert alert-success"><span class="glyphicon glyphicon-ok"></span> ${ok}</i></b><br /><br />
			</c:when>
		</c:choose>	
		<table class="table table-striped">
			<tr>
				<th>TITRE</th>				
			</tr>
			
			<c:forEach items="${themes}" var="theme">	            	            
				<tr>
					<td>
						<form:form method="post" modelAttribute="oneTheme" action="sujetsForum" class="form center-block">
				         	<input type="hidden" name="idTheme" value="${ theme.idTheme }"/>
				         	<input type="hidden" name="libelleTheme" value="${ theme.libelleTheme }"/>
				         	<input type="submit" class="btn btn-primary" value="${theme.libelleTheme}"/><br />
		     			</form:form>
					</td>
				</tr>
	        </c:forEach>
		</table>
		
    </div>
		</section>
<jsp:include page="footer.jsp" />