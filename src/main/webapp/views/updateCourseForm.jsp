<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
	<section>
		<div class="container">
			<c:url value="/myCourses" var="url"></c:url>
	    	<a href="${url}"><button class="btn btn-lg bouton">Retour</button></a>
	    	
			<h1>MODIFIER COURS ${ cours.idCours }</h1>
			
			<h2>Titre du cours</h2>
			<form:form method="post" modelAttribute="updateCourseForm" action="updateCourseSubmit" class="form center-block">
		         <!--<spring:message code="creation.users.login" />-->
		         <input type="hidden" name="idCours" value="${ cours.idCours }" />
		         <div class="form-group col-sm-6">
		         	<label for="libelle">Titre du cours (8 à 64 caractères)</label><br />
		         	<b><i><form:errors path="libelleCours" cssclass="error"/></i></b>
		         	<input type="text" id="libelle" name="libelleCours" placeholder="libelleCours" class="form-control input-lg" style="width:400px;" value="${ cours.libelleCours }" required/>         	
		         </div>
		         
		         <div class="form-group col-sm-6">
			         <label for="img">Logo du cours</label><br />
			         <b><i><form:errors path="ImageTitre" cssclass="error"/></i></b>
			         <img src="./include/images/cours/${ cours.imageTitre }" alt="${cours.imageTitre}" width="128" height="128" required/>
			         <input type="file" id="img" name="imageTitre" placeholder="ImageTitre" class="form-control input-lg" style="width:400px;"/>		         
		         </div>
		         
		         <div class="clearfix"></div>
		         		
		         <div class="form-group">
		         	<input type="submit" class="btn btn-primary btn-lg btn-block" value="Modifier Titre"/>
		         </div>	         
		     </form:form>
			<hr />	
			<h2>Contenu du cours</h2>
			<c:forEach items="${paragraphes}" var="p">	            	            
				
				<form:form method="post" modelAttribute="updateParagrapheForm" action="updateParagrapheSubmit" class="form center-block" enctype="multipart/form-data">				
			         <input type="hidden" name="idParagraphe" value="${ p.idParagraphe }" />
			         <c:choose>
						<c:when test="${ p.texte!=null }">		         
							<textarea style="width:1080px; height:320px;" class="form-control input-lg" name="texte" required>
								${ p.texte }
							</textarea><br />
						</c:when>
						<c:when test="${ p.imageLocation!=null }">
							<img src="./include/images/cours/${p.imageLocation}" alt="${p.imageLocation}" width="128" height="128" />
							<input type="file" class="form-control input-lg" name="imageLocation" placeholder="${ p.imageLocation }"/>
							<br />
						</c:when>
					</c:choose>  
					<div class="form-group">
			        	<input id="submit" type="submit" class="btn btn-primary btn-sm btn-block" value="Modifier Paragraphe"/>
			        </div> 
				</form:form>			
	        </c:forEach>  
			<hr />	
			<h2>Ajout de paragraphes</h2>
			<button id="ajouterParagraphe" class="btn btn-sm btn-primary" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> P</button>
		   	<button id="supprimerParagraphe" class="btn btn-sm btn-primary" type="button"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span> P</button>
		   	
		   	<button id="ajouterImage" class="btn btn-sm btn-primary" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>  <span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>
		   	<button id="supprimerImage" class="btn btn-sm btn-primary" type="button"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span>  <span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>
		   	
			<form:form method="post" modelAttribute="creationCoursContenu" action="addCourseContent" class="form center-block" enctype="multipart/form-data">
		         <b><i class="error"><form:errors path="fk_idCours" cssclass="error"/></i></b>
				<div class="paragraphes">
					
				</div><br />
		         
		         <div class="form-group">
		         	<input id="submit" type="submit" class="btn btn-primary btn-lg btn-block" value="Ajouter"/>
		         </div>        
		     </form:form>
		
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
	    ram += '<b><i class="error"><form:errors path="paragraphes[' + (order-1) + '].texte" cssclass="error"/></i></b>';
    	ram += '<input type="hidden" name="paragraphes[' + (order-1) + '].ordre" value="' + order + '"/>';
    	ram += '<input type="hidden" name="fk_idCours" value="' + ${ cours.idCours } + '"/>';
	    ram += '<textarea style="width:1080px; height:320px;" class="form-control input-lg" name="paragraphes[' + (order-1) + '].texte" required></textarea>';
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
	    ram += '<input type="hidden" name="fk_idCours" value="' + ${ cours.idCours } + '"/>';
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