<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />

	<div class="header-bottom">
		
	<h3>${ libelleTheme } : Les cours</h3><br />
		<c:forEach items="${cours}" var="cours">	                            		                    
           <div class="col-md-4">
                <div class="jumbotron">   
                  <img src="./include/images/cours/${ cours.imageTitre }" width="75px" height="75px" alt="${ cours.imageTitre }"/>
                  <p><c:out value="${cours.libelleCours}"/></p>
                  <p><fmt:formatDate value="${cours.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></p>
                  <p>${cours.user.login}</p>
                  <p>${cours.nbVues} fois</p>				                    
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
        <div class="clearfix"></div>
           
       </div>
    		
<jsp:include page="footer.jsp" />