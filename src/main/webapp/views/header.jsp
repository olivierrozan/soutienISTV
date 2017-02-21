<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Soutien ISTV</title>
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
		<link href="<c:url value="include/css/bootstrap.css" />" rel="stylesheet">
		<link href="<c:url value="include/css/common.css" />" rel="stylesheet">
		<link href="<c:url value="include/css/style.css" />" rel="stylesheet">
		<link href="<c:url value="include/css/style5.css" />" rel="stylesheet">
		<link href="<c:url value="include/css/swipebox.css" />" rel="stylesheet">
		
		<script src="<c:url value="include/js/jquery-1.11.1.min.js" />"></script>		
		<script src="<c:url value="include/js/bootstrap.js" />"></script>		
		<script src="<c:url value="include/js/easyResponsiveTabs.js" />"></script>
		<script src="<c:url value="include/js/jquery.flexisel.js" />"></script>
		<script src="<c:url value="include/js/jquery.swipebox.min.js" />"></script>
		<script src="<c:url value="include/js/responsiveslides.min.js" />"></script>
	</head>
	
	<body>
		
	    <div class="banner-body logn">
	        <div class="container">
	            <div class="header">
	                <div class="header-nav">
						<nav class="navbar navbar-default">
	                		<div class="navbar-header">
							  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							  </button>
							   <a class="navbar-brand" href="/soutienISTV/home">Cours en ligne ISTV</a>
							</div>	                	            	
	
				            <div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
				                <ul class="nav navbar-nav">
				                    <li class="hidden">
				                        <c:url value="/home" var="hiAction" />
										<a href="${hiAction}"></a>
				                    </li>
				                    
				                    <c:choose>
									    <c:when test="${uId==null}">
									        <div class="sign-in">
												<ul>
											        <li class="hvr-bounce-to-bottom active">
								                        <c:url value="/" var="hiAction" />
					 									<a href="${hiAction}">Connexion</a>
								                    </li>
								                    <li>/</li>
								                    <li class="hvr-bounce-to-bottom">
								                        <c:url value="/createUserForm" var="hiAction" />
					 									<a href="${hiAction}">Inscription</a>
								                    </li>			                    										
												</ul>
											</div>
									    </c:when>    
									    <c:otherwise>
									        <li>	                    
							                    <form method="post" modelAttribute="search" action="searchCourse" class="form center-block">         
											         <div class="form-group">						         
												     	<input type="text" name="input" placeholder="recherche..." class="form-control input-lg" />
											         </div>				         				         
											     </form>	                    
						                    </li>
									        <li class="hvr-bounce-to-bottom">
				                        		<c:url value="/themeCourses" var="profilLink" />
				                        		<a class="page-scroll" href="${profilLink}"><span class="glyphicon glyphicon-education" aria-hidden="true"></span>
				                        		 Cours
			                        		    </a>
						                    </li>
						                    <li class="hvr-bounce-to-bottom">
						                        <c:url value="/themeForum" var="forumLink" />
						                        <a class="page-scroll" href="${forumLink}"><span class="glyphicon glyphicon-leaf"></span> 
						                        Forum
						                        </a>
						                    </li>
						                    
						                    <li class="hvr-bounce-to-bottom dropdown">
								               <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
								               ${uLogin} 
								               <span class="caret"></span></a>
								                <ul class="dropdown-menu">
								                
									                <!-- dropdown -->
						                    		<c:choose>
													    <c:when test="${uStatut=='admin'}">									        
															<li class="dropdown-header">admin</li>
															<li>					                	
											                	<c:url value="/allUsers" var="profilLink" />
											                	<a href="${profilLink}" style="font-size:14px;"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
											                	 Membres
											                	 </a>
										                	</li>
											                
											                <li>				                        
										                        <c:url value="/allCourses" var="hiAction" />
							 									<a href="${hiAction}" style="font-size:14px;"><span class="glyphicon glyphicon-education" aria-hidden="true"></span>
							 									 Cours
							 									 </a>
										                    </li>
										                    
										                    <li>				                        
										                        <c:url value="/allForum" var="hiAction" />
							 									<a href="${hiAction}" style="font-size:14px;"><span class="glyphicon glyphicon-leaf"></span>
							 									 Forum
							 									 </a>
										                    </li>	
													        <li role="separator" class="divider"></li>
													    </c:when>    
													</c:choose>
								                
									                <li>					                	
									                	<c:url value="/myCourses" var="profilLink" ></c:url>
									                	<a href="${profilLink}" style="font-size:14px;"><span class="glyphicon glyphicon-education" aria-hidden="true"></span>
									                	 Mes cours
									                	 </a>
								                	</li>
									                
									                <li>					                	
									                	<c:url value="/profil" var="profilLink" ></c:url>
									                	<a href="${profilLink}" style="font-size:14px;"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>
									                	 Profil
									                	 </a>
								                	</li>
									                
									                <li>				                        
								                        <c:url value="/logout" var="hiAction" />
					 									<a href="${hiAction}" style="font-size:14px;"><span class="glyphicon glyphicon-off" aria-hidden="true"></span>
					 									 Déconnexion
					 									 </a>
								                    </li>
							                    <!-- fin dropdown -->
									                
								             	</ul>
								            </li>	                    
									    </c:otherwise>
									</c:choose>
				                   
				                </ul>
				                
				            </div>
	            		</nav>
	            	</div>
	        	</div>
	        