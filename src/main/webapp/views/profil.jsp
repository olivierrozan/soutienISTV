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
    	
    	<c:url value="/updateUser" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Modifier Profil</button></a>
    	
		<h1>PROFIL <c:out value="${param.id}"/></h1>
		<div class="jumbotron">
			
			<img src="./include/images/users/${ user.avatar }" width="75px" height="75px" alt="${ user.avatar }"/>
			<p>${ user.nom } ${ user.prenom } <span>(${uStatut})</span></p>
			<p>${ user.login }</p>
			<p>Formation : ${ user.formation }</p>
			
			<c:url value="/profil" var="url"></c:url>
   			<a href="${url}"><button class="btn btn-lg bouton">Modifier Mot de passe</button></a>
		</div>
	</div>
    </div>
		</section>
<jsp:include page="footer.jsp" />