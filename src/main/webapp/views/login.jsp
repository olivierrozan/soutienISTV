<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	
	<div class="login">
		<div class="login-grid">
			
	    	<div class="col-md-6 log">
					 <h3>Login</h3>
					 <p>Bienvenue sur l'application de soutien scolaire du campus.</p>
					 <p>Connecte-toi pour l'utiliser.</p>
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
					
					 <form:form method="post" modelAttribute="connexion" action="connexionSubmit" class="form center-block" id="connexion">
						 <h5>Login:</h5>	
						 <form:input path="login" placeholder="Login" class="form-control input-lg" value="admin_"/>
				         <b><i><form:errors path="login" cssclass="error"/></i></b>
						 <h5>Mot de passe:</h5>
						 <form:password path="password" placeholder="Mot de passe" class="form-control input-lg" value="adminPWD59"/>
				         <b><i><form:errors path="password" cssclass="error"/></i></b>				
						 <input type="submit" value="Login">
						  
					 </form:form>
					
			</div>
	    	
	    	<div class="col-md-6 login-right">
					<h3>Inscription</h3>
					<p>En t'inscrivant, tu pourras consulter les cours disponibles, rédiger un cours, et participer aux salons de discussion</p>
					<c:url value="/createUserForm" var="hiAction" />
					<a href="${hiAction}" class="hvr-bounce-to-bottom button">Inscription</a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
		
	
<jsp:include page="footer.jsp" />