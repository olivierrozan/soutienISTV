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
		<c:url value="/home" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	
		<h1>AJOUTER COURS</h1>
		
	<form:form method="post" modelAttribute="creationCours" action="addCourse" enctype="multipart/form-data" class="form center-block">
         <!--<spring:message code="creation.users.login" />-->
         <div class="form-group col-sm-6">
         	<label for="libelle">Titre du cours</label><br />
         	<input type="text" id="libelle" name="LibelleCours" placeholder="libelleCours" class="form-control input-lg" style="width:400px;" value="Initiation � la BDD"/>
         	<b><i><form:errors path="libelleCours" cssclass="error"/></i></b>
         </div>
         
         <div class="form-group col-sm-6">
	         <label for="img">Logo du cours</label><br />
	         <input type="file" id="img" name="ImageTitre" placeholder="ImageTitre" class="form-control input-lg" style="width:400px;"/>
	         <b><i><form:errors path="libelleCours" cssclass="error"/></i></b>
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