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
		<c:url value="/profil" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	    	
		<h1>MODIFIER MOT DE PASSE</h1>
		<c:choose>
			<c:when test="${ not empty ok }">
				<b><i class="alert alert-success">${ok}</i></b>	
			</c:when>
		</c:choose>
			
		<div class="jumbotron">
			<div class="row">
				<form:form method="post" commandname="UpdatePasswordform" class="form col-md-12 center-block" modelAttribute="updatePWD" action="updatePasswordSubmit">
	            	
	            	<b><i class="error">${error}</i></b><br />
	            	<b><i><form:errors path="oldPassword" cssClass="error"/></i></b>
					<input type="password" class="form-control input-lg" name="oldPassword" placeholder="Ancien mot de passe" /><br/>
					
					<b><i><form:errors path="newPassword" cssClass="error"/></i></b>
					<input type="password" class="form-control input-lg" name="newPassword" placeholder="Nouveau mot de passe" /><br/>
					
					<b><i><form:errors path="newPassword2" cssClass="error"/></i></b>	
					<input type="password" class="form-control input-lg" name="newPassword2" placeholder="Retaper le nouveau mot de passe" /><br/>								
					    
					<input type="submit" class="btn btn-primary btn-lg btn-block" value="Modifier"/>
				</form:form>
			</div>
		</div>
	</div>
    </div>
		</section>
<jsp:include page="footer.jsp" />