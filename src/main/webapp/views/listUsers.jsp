<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<div class="jumbotron">
	<table class="table table-striped">
        <h4>Users</h4>
        <thead>
            <tr>
                <th><spring:message code="colonne.identifiant"/></th>
                <th><spring:message code="colonne.nom"/></th>
                <th><spring:message code="colonne.prenom"/></th>
                <th>STATUT</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listUsers}" var="user">
                <tr>
                    <td>
                    	<img src="./include/images/users/${ user.avatar }" width="75px" height="75px" alt="${ user.avatar }"/>
                   	</td>
                    <td><c:out value="${user.nom}"/></td>
                    <td><c:out value="${user.prenom}"/></td>
                     <td><c:out value="${user.statut}"/></td>
                    <td>
                        <c:url value="/detailsUser" var="url">
                            <c:param name="id" value="${user.id}"/>
                        </c:url>
                        <a href="${url}">
                            <spring:message code="details.users" />
                        </a>
                    </td>
                    <td>
                        <c:url value="/deleteUser" var="url">
                            <c:param name="id" value="${user.id}"/>
                        </c:url>
                        <a href="${url}">
                                                       
                        </a>
                        
                        <form method="post" modelAttribute="ban" action="banUser" class="form center-block">         
					         <div class="form-group">						         
						     	<input type="hidden" name="id" value="${ user.id }" />
						     	<input type="hidden" name="statut" value="${ user.statut }" />
						     	<c:choose>
	                            	<c:when test="${user.statut == 'banni'}">
	                            		
	                            		<button type="submit" name="input" class="btn btn-md btn-success" >
	                            			<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Réactiver
	                            		</button>
	                            	</c:when>
	                            	<c:otherwise>
	                            		<button type="submit" name="input" class="btn btn-md btn-danger">
	                            			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Bannir
	                            		</button>
	                            	</c:otherwise>
	                            </c:choose> 						     	
					         </div>				         				         
					     </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    </div>
		</section>
<jsp:include page="footer.jsp" />