<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
			<div class="container">
	<!--  <div class="col-sm-6">-->
		<c:url value="/home" var="url"></c:url>
    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
    	
    	<button id="ajouterParagraphe" class="btn btn-sm btn-primary" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> P</button>
    	<button id="supprimerParagraphe" class="btn btn-sm btn-primary" type="button"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span> P</button>
    	
    	<button id="ajouterImage" class="btn btn-sm btn-primary" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  <span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>
    	<button id="supprimerImage" class="btn btn-sm btn-primary" type="button"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>  <span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>
    	
		<h1>AJOUTER CONTENU ${ idCours }</h1>
		
	<form:form method="post" modelAttribute="creationCoursContenu" action="addCourseContent" class="form center-block">
         <!--<spring:message code="creation.users.login" />-->
         
		<div class="paragraphes">
			
		</div><br />
         
         <div class="form-group">
         	<input type="submit" class="btn btn-primary btn-lg btn-block"/>
         </div>
         
     </form:form>
		<!-- </div> -->
	
    </div>
		</section>
<jsp:include page="footer.jsp" />

<script>
	var order = 0;

	$("#ajouterParagraphe").on("click", function() {
		order++;

	    var ram = '<div class="paragraphe">';
	    ram += '<label>Paragraphe :</label><br />';
	    ram += '<c:forEach items="${creationCoursContenu.paragraphes}" var="contact" varStatus="status">';
    	ram += '<input type="hidden" name="paragraphes[' + (order-1) + '].ordre" value="' + order + '"/>';
    	ram += '<input type="hidden" name="fk_idCours" value="' + ${ idCours } + '"/>';
	    ram += '<textarea style="width:1080px; height:320px;" class="form-control input-lg" name="paragraphes[' + (order-1) + '].texte"></textarea>';
	    ram += '</c:forEach>';
	    ram += '</div><br />';
	
	    $(".paragraphes").append(ram);
	    
	});
	
	$("#supprimerParagraphe").on("click", function() {
		order--;

	    if ($(".paragraphe").length > 0) {
	        $(".paragraphe").last().remove();
	    }
	});

	$("#ajouterImage").on("click", function() {
		order++;

	    var ram = '<div class="image">';
	    ram += '<label>Image :</label><br />';
	    ram += '<c:forEach items="${creationCoursContenu.paragraphes}" var="contact" varStatus="status">';
	    ram += '<input type="hidden" name="paragraphes[' + (order-1) + '].ordre" value="' + order + '"/>';
	    ram += '<input type="hidden" name="fk_idCours" value="' + ${ idCours } + '"/>';
	    ram += '<input type="file" class="form-control input-lg" name="paragraphes[' + (order-1) + '].imageLocation" placeholder="Image"/>';
	    ram += '</c:forEach>';
	    ram += '</div><br />';
	
	    $(".paragraphes").append(ram);
	    
	});
	
	$("#supprimerImage").on("click", function() {
		order--;

	    if ($(".image").length > 0) {
	        $(".image").last().remove();
	    }
	});
</script>