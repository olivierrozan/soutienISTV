<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<div class="sign-up-form">
		<h3>Inscription</h3>
		<div class="sign-up">			
			<p>* champs obligatoires</p>
			<c:choose>
				<c:when test="${ not empty ok }">
					<b><i class="alert alert-success">${ok}</i></b>	
				</c:when>
			</c:choose>		
	<form:form method="post" modelAttribute="creation" action="createUserForm" class="form center-block">
         
         <div class="sign-u">
         	 <h6>Informations de connexion</h6>
         	 
         	 <div class="sign-up1">         	 
         	 <h4 class="a">Login*</h4>
         	 </div>
         	 <div class="sign-up2">
         	 <b><i class="error"><form:errors path="login" cssclass="error"/></i></b>	         
	         <form:input path="login" placeholder="Login" class="form-control input-lg" value="Moderateur"/>
	         </div>
         	 <div class="clearfix"></div>
         </div>
         
         <div class="sign-u">
	         <div class="sign-up1">
	         <h4 class="a">Mot de passe*</h4>
	         </div>
         	 <div class="sign-up2">
	         <b><i class="error"><form:errors path="password" cssclass="error"/></i></b>
	         <form:password path="password" placeholder="Mot de passe" class="form-control input-lg" value="modoPWD59"/>
	         </div>
         	 <div class="clearfix"></div>	         
         </div>
         
         <div class="sign-u">
	         <div class="sign-up1">
	         <h4 class="a">Confirmer le mot de passe*</h4>
	         </div>
         	 <div class="sign-up2">
	         <b><i class="error">${NotMatch}</i></b>
	         <form:password path="password2" placeholder="Confirmer Mot de passe" class="form-control input-lg" value="modoPWD59"/>
	         </div>
         	 <div class="clearfix"></div>         
         </div>
         
         <div class="sign-u">
	         <div class="sign-up1">
	         <h4 class="a">Email*</h4>
	         </div>
         	 <div class="sign-up2">
	         <b><i class="error"><form:errors path="email" cssclass="error"/></i></b>
	         <input type="text" name="email" placeholder="email" class="form-control input-lg" value="mail@mail.fr"/>
	         </div>
         	 <div class="clearfix"></div>	         
         </div>
         
         <input type="submit" class="btn btn-primary btn-lg btn-block"/>
         
         <div class="sign-up2">
	         <c:url value="/" var="url"></c:url>
    	 	<a href="${url}">Déjà inscrit? Connectez-vous!</a>
         </div>
         
         
     </form:form>
     </div>
     </div>
		
<jsp:include page="footer.jsp" />