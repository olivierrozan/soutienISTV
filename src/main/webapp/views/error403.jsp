<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<h1><span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span> OUPS !</h1>
	<h2>Accès interdit !</h2>
	<p>Vous n'êtes pas autorisé à accéder à la page demandée.</p>
	
	<c:url value="/" var="profilLink" />
    <a href="${profilLink}">Retour Connexion</a>
    </div>
		</section>
<jsp:include page="footer.jsp" />

<script>
    //setTimeout("window.location.href='/soutienISTV/';", 3000); // after 2 minutes
</script>