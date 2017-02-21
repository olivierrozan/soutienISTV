<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h3>Résultats de la recherche :  ${ libelleRecherche }</h3>
								
				<div class="container">
					<h3>Cours</h3>
                    <div class="row">
						<c:choose>
							<c:when test="${tailleCours==0}">
								<p>Aucun cours n'a été trouvé</p>
							</c:when>
							<c:otherwise>
								<c:forEach items="${cours}" var="cours">	                            		                    
				                    <div class="col-md-4">
					                    <div class="jumbotron">   
						                    <p><c:out value="${cours.libelleCours}"/></p>
						                    <p><fmt:formatDate value="${cours.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></p>
						                    <p>${cours.user.login}</p>				                    
						                    <p>
						                    	<form:form method="post" modelAttribute="seeCours" action="seeSearchedCourse" class="form center-block">
										         	<input type="hidden" name="idCours" value="${ cours.idCours }"/>
										         	<input type="hidden" name="libelleCours" value="${ cours.libelleCours }"/>
										         	<input type="submit" class="btn btn-primary btn-lg btn-block" value="Voir le cours"/><br />
								     			</form:form>
						                    </p>
				                    	</div>	                    	
				                	</div>					                
					            </c:forEach>
				            </c:otherwise>
			            </c:choose>
	            	</div>
              	</div>
              	
              	<div class="container">
					<h3>Forum</h3>
                    <div class="row">
						<c:choose>
							<c:when test="${tailleSujets==0}">
								<p>Aucun sujet de discussion n'a été trouvé</p>
							</c:when>
							<c:otherwise>
								<c:forEach items="${sujets}" var="sujets">	                            	                    
				                    <div class="col-md-4">
					                    <div class="jumbotron">   
						                    <p><c:out value="${sujets.titre}"/></p>
						                    <p>${sujets.user.login}</p>				                    
						                    <p>
						                    	<form:form method="post" modelAttribute="seeSujets" action="seeSearchedSujet" class="form center-block">
										         	<input type="hidden" name="id" value="${ sujet.id }"/>
										         	<input type="submit" class="btn btn-primary btn-lg btn-block" value="Voir les posts"/><br />
								     			</form:form>
						                    </p>
				                    	</div>	                    	
				                	</div>			                
					            </c:forEach>
				            </c:otherwise>
			            </c:choose>
	            	</div>
              	</div>
				
			</div>
		</div>
   	</div>
   	</section>
<jsp:include page="footer.jsp" />