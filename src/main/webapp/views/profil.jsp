<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<div class="header-bottom">
		
		
    	<c:choose>
			<c:when test="${ not empty ok }">
				<br /><br /><b><i class="alert alert-success"><span class="glyphicon glyphicon-ok"></span> ${ok}</i></b>	
			</c:when>
		</c:choose>	
		
		<h1>PROFIL</h1>
		
		<div class="comments-top-top">
			<c:url value="/home" var="url"></c:url>
	    	<a href="${url}"><button class="btn btn-lg btn-primary bouton">Retour</button></a>
	    	
	    	<c:url value="/updateUser" var="url"></c:url>
	    	<a href="${url}"><button class="btn btn-lg btn-primary bouton">Modifier Profil</button></a><br /><br />
			
			<h3 class="ghj">Informations personnelles</h3>
			
			<img src="./include/images/users/${ user.avatar }" width="75px" height="75px" alt="${ user.avatar }"/>
			<p>${ user.nom } ${ user.prenom } <span>(${uStatut})</span></p>
			<p>${ user.login }</p>
			<p>Formation : ${ user.formation }</p><br />
			
			<c:choose>
   				<c:when test="${ not empty no }">
   					<h3 class="ghj">${ no }</h3>
   				</c:when>
   				<c:otherwise>
   					<h3 class="ghj">Cours rédigés</h3>	
					<c:forEach items="${cours}" var="cours">								
						<div class="list-group list-group-alternate"> 
								
							<form:form method="post" modelAttribute="seeCours" action="seeSearchedCourse" class="form center-block">
						         <input type="hidden" name="idCours" value="${ cours.idCours }"/>
						         <input type="hidden" name="libelleCours" value="${ cours.libelleCours }"/>
						         <div class="list-group-item">
						         	<img src="./include/images/cours/${ cours.imageTitre }" width="75px" height="75px" alt="${ cours.imageTitre }"/>
						         	<span>${ cours.libelleCours }</span>
						         </div>
						         
						         <input type="submit" class="btn btn-primary btn-lg btn-block" value="Voir le cours"/><br />
				     		</form:form>
					     		
						</div> 									
					</c:forEach>
   				</c:otherwise>
   			</c:choose> 
			
			
		</div>
	</div>
<jsp:include page="footer.jsp" />