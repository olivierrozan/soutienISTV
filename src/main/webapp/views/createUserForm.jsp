<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<h3>Inscription</h3>
	
	<br />
	
	<form:form method="post" modelAttribute="creation" action="createUserForm" class="form col-md-5 center-block">
         
         <!--<spring:message code="creation.users.login" />-->
         <div class="form-group">
         	 <b><i class="error"><form:errors path="login" cssclass="error"/></i></b>
	         <form:input path="login" placeholder="Login" class="form-control input-lg" value="Moderateur"/>         
         </div>
         
         <!--<spring:message code="creation.users.password"/>-->
         <div class="form-group">
	         <b><i class="error"><form:errors path="password" cssclass="error"/></i></b>
	         <form:password path="password" placeholder="Mot de passe" class="form-control input-lg" value="modoPWD59"/>	         
         </div>
         
         <div class="form-group">
	         <b><i class="error"><form:errors path="password2" cssclass="error"/>${NotMatch}</i></b>
	         <form:password path="password2" placeholder="Confirmer Mot de passe" class="form-control input-lg" value="modoPWD59"/>	         
         </div>
         
         <div class="form-group">
	         <b><i class="error"><form:errors path="email" cssclass="error"/></i></b>
	         <input type="text" name="email" placeholder="email" class="form-control input-lg" value="mail@mail.fr"/>	         
         </div>
         
         <div class="form-group">
	         <b><i class="error"><form:errors path="nom" cssclass="error"/></i></b>
	         <form:input path="nom" placeholder="nom" class="form-control input-lg" value="POUTINE"/>	         
         </div>
         
         <!--<spring:message code="creation.users.password"/>-->
         <div class="form-group">
	         <b><i class="error"><form:errors path="prenom" cssclass="error"/></i></b>
	         <form:input path="prenom" placeholder="prenom" class="form-control input-lg" value="Vladimir"/>	         
         </div>
         
         <!--<spring:message code="creation.users.password"/>-->
         <div class="form-group">
	         <b><i class="error"><form:errors path="formation" cssclass="error"/></i></b>
	         <form:input path="formation" placeholder="formation" class="form-control input-lg" value="Doctorat Sociologie"/>	         
         </div>
         
         <input type="submit" class="btn btn-primary btn-lg btn-block"/>
         
         <c:url value="/" var="url"></c:url>
    	<a href="${url}">Déjà inscrit? Connectez-vous!</a>
         
     </form:form>
     </div>
		</section>
<jsp:include page="footer.jsp" />