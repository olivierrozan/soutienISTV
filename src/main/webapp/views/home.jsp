<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp" />
		
		<div class="header-bottom">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
          <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <div class="item active">
            <h1 style="text-align:center; margin-top:100px;">Bienvenue !</h1>
            <h2 style="text-align:center;">Cette application va t'aider</h2>
            <h4 style="text-align:center;">pour tes projets</h4>
            <h4 style="text-align:center;">pour tes cours</h4>
          </div>
          <div class="item">
            <h1 style="text-align:center; margin-top:100px;">Sur cette page, tu peux accéder</h1>
            <h4 style="text-align:center;">aux derniers cours publiés</h4>
            <h4 style="text-align:center;">et aux sujets de discussion récents</h4>
          </div>
          <div class="item">
            <h1 style="text-align:center; margin-top:100px;">L'onglet cours</h1>
            <h4 style="text-align:center;">te permettra de voir tous les cours</h4>
            <h4 style="text-align:center;">et de rédiger tes propres cours</h4>
            <h4 style="text-align:center;">pour aider les étudiants en difficultés</h4>
          </div>
          <div class="item">
            <h1 style="text-align:center; margin-top:100px;">Dans le forum</h1>
            <h4 style="text-align:center;">tu pourras demander de l'aide dans tes études</h4>
            <h4 style="text-align:center;">et aider ceux qui le demandent</h4>
          </div>
          
        </div>
        
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
        
	</div>

	<section class="main">
		<h3 class="ghj">Les derniers cours</h3><br />
		<ul class="ch-grid">
			<c:forEach items="${cours}" var="cours">	  
			<li>
				<div class="ch-item">				
					<div class="ch-info-wrap">
						<div class="ch-info">
							<div class="ch-info-front">
								<img src="./include/images/cours/${ cours.imageTitre }" width="180px" height="180px" alt="${ cours.imageTitre }" style="border-radius:5%;"/>
							</div>
							<div class="ch-info-back">
								<h4>${cours.libelleCours}</h4>
								<p> ${cours.user.login}</p>
								
								<form:form method="post" modelAttribute="seeCours" action="seeSearchedCourse" class="form center-block">
					        	<input type="hidden" name="idCours" value="${ cours.idCours }"/>
					        	<input type="hidden" name="libelleCours" value="${ cours.libelleCours }"/>
					        	<div class="more">
					        		<input type="submit" class="btn btn-md btn-primary" value="Voir le cours"/><br />							         		
					        	</div>
					  			</form:form>
							</div>	
						</div>
					</div>
				</div>
			</li>
			</c:forEach>
			
		</ul>
	</section>
										
        <h3>Les derniers sujets</h3><br />
        <c:forEach items="${sujets}" var="sujets">	                            	                    
               <div class="col-md-4">
                <div class="about-text-info">   
                 <h3 class="ghj"><c:out value="${sujets.titre}"/></h3>
                 <h5>${sujets.user.login}</h5>				                    
                 <p>
                 	<form:form method="post" modelAttribute="onePost" action="post" class="form center-block">
	         	<input type="hidden" name="id" value="${ sujets.id }"/>
	         	<input type="hidden" name="titre" value="${ sujets.titre }"/>
	         	<input type="submit" class="btn btn-primary" value="Voir le sujet"/><br />
    			</form:form>
                 </p>
               	</div>	                    	
           	</div>			                
        </c:forEach>
       	<div class="clearfix"></div>
        </div>
	            		            	
<jsp:include page="footer.jsp" />