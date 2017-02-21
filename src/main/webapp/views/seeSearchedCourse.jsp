<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
		
		<div class="single-page-artical">
		<a><button class="btn btn-lg btn-primary" onclick="history.back(-1)">Retour</button></a>
		<div class="artical-content">
			<h3><c:out value="${cours.libelleCours}"/></h3>

				
					
					<div class="artical-links">
						<ul>
							<li><small> </small><span><fmt:formatDate value="${cours.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></span></li>
							<li><a href="#"><small class="admin"> </small><span>${cours.user.login}</span></a></li>
							<li>Vu ${cours.nbVues} fois</li>
						</ul>
					</div>					
					
					<c:forEach items="${paragraphes}" var="p">	            	            
		    			<c:choose>
		    				<c:when test="${ p.texte!=null }">
		    					<p>${ p.texte }</p>
		    				</c:when>
		    				<c:when test="${ p.imageLocation!=null }">
		    					<img src="./include/images/cours/${ p.imageLocation }" alt="${ p.imageLocation }" /><br />
		    				</c:when>
		    			</c:choose>   			
		            </c:forEach>
		            
		            <div class="artical-links">
						<ul>
							<li><small> </small><span><fmt:formatDate value="${cours.dateDerniereModif}" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></span></li>
							<li><a href="#"><small class="admin"> </small><span>${cours.user.login}</span></a></li>
							<li>Vu ${cours.nbVues} fois</li>
						</ul>
					</div>	
            </div>     
    	</div>
<jsp:include page="footer.jsp" />