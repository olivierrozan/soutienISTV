<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<div class="header-bottom">
	
		<c:url value="/home" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>   	
    	
		<h1>COURS - Themes</h1>
		
		<c:forEach items="${themes}" var="theme">	                            	                    
               <div class="col-md-4">
                <div class="about-text-info">   
                 		                    
                 <p>
                 	<form:form method="post" modelAttribute="coursesByThemeForm" action="cours" class="form center-block">
			         	<input type="hidden" name="idTheme" value="${ theme.idTheme }"/>
			         	<input type="hidden" name="libelleTheme" value="${ theme.libelleTheme }"/>
			         	<input type="submit" class="btn btn-lg btn-primary" value="${theme.libelleTheme}"/><br />
	     			</form:form>
                 </p>
               	</div>	                    	
           	</div>			                
        </c:forEach>
       	<div class="clearfix"></div>
       	
		</div>
<jsp:include page="footer.jsp" />