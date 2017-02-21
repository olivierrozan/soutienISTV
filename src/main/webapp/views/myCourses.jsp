<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<div class="col-sm-12">
		<c:url value="/home" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	
    	<c:url value="/addCourse" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Ajouter Cours</button></a>
		
		<h1>MES COURS</h1>
		
		<div class="jumbotron">
			<table class="table table-striped">
			        
			        <thead>
			            <tr>
			                <th>LIBELLE</th>
			                <th>DATE DERNIERE MODIFICATION</th>
			                <th>ETAT</th>
			                <th>VU</th>
			                <th>&nbsp;</th>
			                		                
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${listCours}" var="cours">
			                <tr>               
			                    <td>
			                    	<img src="./include/images/cours/${ cours.imageTitre }" width="75px" height="75px" alt="${ cours.imageTitre }"/>
			                    	<c:out value="${cours.libelleCours}"/>			                    	
			                    </td>
			                    <td><fmt:formatDate value="${cours.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></td>
			                    <td><c:out value="${cours.etat}"/></td>
			                    <td><c:out value="${cours.nbVues}"/> fois</td>	
			                    <td>
			                        <c:url value="/detailsCourse" var="url">
			                            <c:param name="id" value="${cours.idCours}"/>
			                        </c:url>
			                        <a href="${url}" title="Détails">
			                            <spring:message code="details.users" />
			                        </a>
			                    </td>
			                    								        
								<td class="dropdown">
			                        
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									<span class="glyphicon glyphicon-cog" aria-hidden="true"></span><span class="caret">
									</span>
									</a>
									<ul class="dropdown-menu">						                
						                    								        	
	                        			<li>
	                        				<form:form method="post" modelAttribute="updateCourse" action="updateCourse" class="form center-block">         
										        <div class="form-group">
										        	<input type="hidden" name="idCours" value="${ cours.idCours }"/>
										        	<button type="submit" class="btn btn-primary btn-sm">
										        		<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
										        		Modifier
													</button>
										        </div>											         
										    </form:form>
	                        			</li>										    	
										    	
				                        <li>
	                        				<form:form method="post" modelAttribute="deleteCourse" action="deleteCourse" class="form center-block">         
										        <div class="form-group">
										        	<input type="hidden" name="idCours" value="${ cours.idCours }"/>										        	
										        	<button type="submit" class="btn btn-primary btn-sm" value="">
										        		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
										        		Supprimer
										        	</button>										        	
										        </div>											         
										    </form:form>
	                        			</li>
				                        			                
						            </ul>
						            
			                    </td>
			                    
			                </tr>
			                
			            </c:forEach>
			        </tbody>
			    </table>
		</div>
	</div>
    </div>
		</section>
<jsp:include page="footer.jsp" />