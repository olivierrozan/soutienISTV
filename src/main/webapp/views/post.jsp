<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<div class="header-bottom">
			<br />
			<div class="col-md-2">
				<c:url value="/themeForum" var="url"></c:url>
		    	<a href="${url}"><button class="btn btn-lg btn-primary">Retour</button></a>
	    	</div>
	    	<c:choose>
				<c:when test="${ uStatut=='admin' }">
			    	<div class="col-md-2">
				    	<form:form method="post" modelAttribute="resolu" action="postResolu" class="form center-block">
				         	<input type="hidden" name="id" value="${ sujet.id }"/>	      
				         	<button type="submit" class="btn btn-lg btn-success"><span class="glyphicon glyphicon-ok"></span> Marquer comme résolu</button>
			   			</form:form>
		   			</div>
	   			</c:when>
   			</c:choose>
	    	<div class="clearfix"></div>
	    	
			<h1>${ sujet.titre }</h1>
			<c:choose>
				<c:when test="${ not empty ok }">
					<br /><b><i class="alert alert-success"><span class="glyphicon glyphicon-ok"></span> ${ok}</i></b><br /><br /><br />
				</c:when>
			</c:choose>	
			<c:choose>
					<c:when test="${sujet.etat == 'Résolu'}">
						<div>
							<div class="alert alert-success">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
							La discussion est résolue
							</div>
						</div>
						
					</c:when>
				</c:choose>
			
			<c:forEach items="${posts}" var="p">	            	            
				<div class="comments-top-top">
					<div class="top-comment-left">
						<p><img src="./include/images/users/${p.user.avatar}" width="75px" height="75px" alt="${p.user.avatar}"/></p>
						
					</div>
					
					<div class="top-comment-right">
						<ul>
							<li><span class="left-at"><a>${p.user.login}</a></span></li>
							<li><span class="right-at"><fmt:formatDate value="${p.datePost}" type="both" pattern="EEEEE d MMMMM yyyy HH:mm:ss" /></span></li>
						</ul>
						<p>${p.contenu}</p>
					</div>
					<div class="clearfix"></div>
				</div>
				
	        </c:forEach>
			
			<div class="comments-top-top">
				<c:choose>
					<c:when test="${sujet.etat == 'Non résolu'}">
						
						<div class="artical-commentbox">
							<h3>Laisser un message</h3>
							<div class="table-form">
								<form:form method="post" modelAttribute="postReply" action="post" class="form center-block">
						         	<input type="hidden" name="id" value="${ sujet.id }"/>
						         	<input type="hidden" name="titre" value="${ sujet.titre }"/>
						         	<input type="hidden" name="etat" value="${ sujet.etat }"/>
						         	<textarea class="textareaPost" name="contenu" required></textarea>
						         	<input type="submit" class="btn btn-primary" value="Envoyer"/><br />
					   			</form:form>
							</div>
						</div>
					</c:when>
					
					<c:otherwise>
						<div class="col-sm-9">
							<div class="alert alert-success">
							<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
							La discussion est résolue
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				
				
				<div class="clearfix"></div>
				
				
   			</div>
	    </div>
<jsp:include page="footer.jsp" />