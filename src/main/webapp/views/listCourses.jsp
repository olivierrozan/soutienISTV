<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
		<div class="container">
			<h1>Tous les cours</h1>
			<div class="jumbotron">
			
			    <table class="table table-striped">
			        
			        <thead>
			            <tr>
			                <th>LIBELLE</th>
			                <th>DATE DERNIERE MODIFICATION</th>
			                <th>ETAT</th>
			                <th>VU</th>
			                <th>&nbsp;</th>
		                	<th>&nbsp;</th>
			            </tr>
			        </thead>
			        <tbody>
			            <c:forEach items="${listCours}" var="cours">
			                <tr>               
			                    <td><c:out value="${cours.libelleCours}"/></td>
			                    <td><fmt:formatDate value="${cours.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></td>
			                    <td><c:out value="${cours.etat}"/></td>
			                    <td><c:out value="${cours.nbVues} fois"/></td>
			                    <td>
			                        <c:url value="/detailsCourse" var="url">
			                            <c:param name="id" value="${cours.idCours}"/>
			                        </c:url>
			                        <a href="${url}" title="Détails">
			                            <spring:message code="details.users" />
			                        </a>
			                    </td>
			                    								        
								<td class="dropdown">			                        
					                        
							        <div class="form-group">
							        	
								     	<c:choose>
			                            	<c:when test="${cours.etat == 'Validé'}">				                            		
			                            		<form:form method="post" modelAttribute="deactivateCourse" action="deactivateCourse" class="form center-block"> 
				                            		<input type="hidden" name="idCours" value="${ cours.idCours }" />
				                            		<button type="submit" name="input" class="btn btn-md btn-danger" >
				                            			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Désactiver
				                            		</button>
			                            		</form:form>
			                            	</c:when>
			                            	<c:otherwise>
			                            		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
												<button class="btn btn-md btn-success">
												<span class="caret"></span>
												<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Valider
												</button>
												</a>
												<ul class="dropdown-menu">						                
									                
			                        				<c:forEach items="${listThemes}" var="theme">
			                        				<li>
			                        					<form:form method="post" modelAttribute="validateCourse" action="validateCourse" class="form center-block">         
													    	<input type="hidden" name="idCours" value="${ cours.idCours }" />
													    	<input type="hidden" name="theme.idTheme" value="${ theme.idTheme }" />											     					                            		
						                            		<button type="submit" name="input" class="btn btn-md btn-info" >
						                            			${ theme.libelleTheme }
						                            		</button>						                        											         
													    </form:form>	
			                        				</li>
			                        				</c:forEach>                
									            </ul>
					            
			                            	</c:otherwise>
			                            </c:choose> 
							        </div>											         
								    							    
			                    </td>
			                    
			                </tr>
			                
			            </c:forEach>
			        </tbody>
			    </table>
		    </div>
    	</div>
		</section>
<jsp:include page="footer.jsp" />

