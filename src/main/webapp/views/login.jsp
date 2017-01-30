<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<form:form method="post" modelAttribute="connexion" action="connexionSubmit" class="form col-md-5 center-block">
        
        <h3>Connexion</h3>
        <c:choose>
		    <c:when test="${param.error=='1'}">
		        <div class="alert alert-danger" role="alert">
				  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  <span class="sr-only">Error:</span>
				  Login ou mot de passe incorrect
				</div>
		        
		    </c:when>    
		    <c:otherwise>
		        ${ error } 
		        <br />
		    </c:otherwise>
		</c:choose>
         
         <!--<spring:message code="creation.users.login" />-->
         <div class="form-group">
         <form:input path="login" placeholder="Login" class="form-control input-lg" value="admin_"/>
	         <b><i><form:errors path="login" cssclass="error"/></i></b>
	         </div>
	         
	         <!--<spring:message code="creation.users.password"/>-->
	         <div class="form-group">
	         <form:password path="password" placeholder="Mot de passe" class="form-control input-lg" value="adminPWD59"/>
	         <b><i><form:errors path="password" cssclass="error"/></i></b>
	         </div>
	         
	         <input type="submit" class="btn btn-primary btn-lg btn-block"/>
	         
	         <c:url value="/createUserForm" var="url"></c:url>
	    	<a href="${url}">Pas encore inscrit?</a>
	     </form:form>
     
     </div>
		</section>
<jsp:include page="footer.jsp" />