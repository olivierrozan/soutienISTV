<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<h1>Modification Profil</h1>
					<c:url value="/profil" var="url"></c:url>
	    			<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
	    			<c:url value="/updatePassword" var="url"></c:url>
   					<a href="${url}"><button class="btn btn-lg bouton">Modifier Mot de passe</button></a>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<form:form method="post" class="form col-md-5 center-block" modelAttribute="modification" action="updateUserSubmit">
	            
					<input type="hidden" name="id" value="${user.id}"/>
					
					<b><i><form:errors path="nom" /></i></b>
					<input type="text" class="form-control input-lg" name="nom" value="${user.nom}" placeholder="Nom" /><br/>
					
					<b><i><form:errors path="prenom" /></i></b>
					<input type="text" class="form-control input-lg" name="prenom" value="${user.prenom}" placeholder="Prenom" /><br/>
					
					<b><i><form:errors path="login" /></i></b>
					<input type="text" class="form-control input-lg" name="login" value="${user.login}" placeholder="Login" /><br/>
					
					<b><i><form:errors path="email" /></i></b>
					<input type="text" class="form-control input-lg" name="email" value="${user.email}" placeholder="Email" /><br/>
					
					<img src="./include/images/users/${user.avatar}" alt="${user.avatar}" width="50" height="50" />
					<input type="file" class="form-control input-lg" name="avatar" value="${user.avatar}" placeholder="Avatar" /><br/>
					
					<b><i><form:errors path="formation" /></i></b>
					<input type="text" class="form-control input-lg" name="formation" value="${user.formation}" placeholder="Formation" /><br/>
					        
					<input type="submit" class="btn btn-primary btn-lg btn-block" value="Modifier"/>
				</form:form>
			</div>
		</div>
	</section>
<jsp:include page="footer.jsp" />