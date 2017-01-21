<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<h3>Inscription</h3>
	
	<br />
	
	<form:form method="post" modelAttribute="creation" action="createUserForm" class="form col-md-5 center-block">
         
        <c:choose>
		    <c:when test="${param.error=='1'}">
		        <h4 style="color:rgb(192,0,0);">Login ou mot de passe incorrect</h4>
		        
		    </c:when>    
		    <c:otherwise>
		        ${ error } 
		        <br />
		    </c:otherwise>
		</c:choose>
         
         <!--<spring:message code="creation.users.login" />-->
         <div class="form-group">
         <form:input path="login" placeholder="Login" class="form-control input-lg" value="V"/>
         <b><i><form:errors path="login" cssclass="error"/></i></b>
         </div>
         
         <!--<spring:message code="creation.users.password"/>-->
         <div class="form-group">
         <form:password path="password" placeholder="Mot de passe" class="form-control input-lg" value="poi"/>
         <b><i><form:errors path="password" cssclass="error"/></i></b>
         </div>
         
         <div class="form-group">
         <form:input path="nom" placeholder="nom" class="form-control input-lg" value="V"/>
         <b><i><form:errors path="nom" cssclass="error"/></i></b>
         </div>
         
         <!--<spring:message code="creation.users.password"/>-->
         <div class="form-group">
         <form:input path="prenom" placeholder="prenom" class="form-control input-lg" value="v"/>
         <b><i><form:errors path="prenom" cssclass="error"/></i></b>
         </div>
         
         <!--<spring:message code="creation.users.password"/>-->
         <div class="form-group">
         <form:input path="formation" placeholder="formation" class="form-control input-lg" value="BTS SIO"/>
         <b><i><form:errors path="formation" cssclass="error"/></i></b>
         </div>
         
         <input type="submit" class="btn btn-primary btn-lg btn-block"/>
     </form:form>
     </div>
		</section>
<jsp:include page="footer.jsp" />