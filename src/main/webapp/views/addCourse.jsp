<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<!--  <div class="col-sm-6">-->
		<c:url value="/myCourses" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	
		<h1>AJOUTER COURS</h1>
		
	<form:form method="post" modelAttribute="creationCours" action="addCourse" class="form center-block">
         <!--<spring:message code="creation.users.login" />-->
         <div class="form-group col-sm-6">
         	<label for="libelle">Titre du cours (8 à 64 caractères)</label><br />
         	<b><i><form:errors path="libelleCours" cssclass="error"/></i></b>
         	<input type="text" id="libelle" name="libelleCours" placeholder="libelleCours" class="form-control input-lg" style="width:400px;" value="Initiation à la BDD"/>         	
         </div>
         
         <div class="form-group col-sm-6">
	         <label for="img">Logo du cours</label><br />
	         <b><i><form:errors path="ImageTitre" cssclass="error"/></i></b>
	         <input type="file" id="img" name="imageTitre" placeholder="ImageTitre" class="form-control input-lg" style="width:400px;"/>
	         
         </div>
         
         <div class="clearfix"></div>
         <hr>
		
         <div class="form-group">
         	<input type="submit" class="btn btn-primary btn-lg btn-block"/>
         </div>
         
     </form:form>
		<!-- </div> -->
	
    </div>
		</section>
<jsp:include page="footer.jsp" />