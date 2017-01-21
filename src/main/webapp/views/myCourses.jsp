<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<div class="col-sm-6">
		<c:url value="/home" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	
    	<c:url value="/addCourse" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Ajouter Cours</button></a>
		
		<h1>MES COURS</h1>
		
		<div class="jumbotron">
			<h3>Liste des cours : </h3>
			<c:forEach items="${cours}" var="cours">	            	            
    			<form:form method="post" modelAttribute="seeCours" action="seeCourse" class="form center-block">
		         	<input type="hidden" name="libelleCours" value="${ cours.libelleCours }"/>
		         	<input type="hidden" name="idCours" value="${ cours.idCours }"/>
		         	<input type="submit" class="btn btn-primary btn-lg btn-block" value="${cours.libelleCours}"/><br />
     			</form:form>
            </c:forEach>
		</div>
	</div>
    </div>
		</section>
<jsp:include page="footer.jsp" />