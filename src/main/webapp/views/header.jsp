<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Soutien ISTV</title>
		<link href="<c:url value="include/css/bootstrap.css" />" rel="stylesheet">
		<link href="<c:url value="include/css/bootstrap-theme.css" />" rel="stylesheet">
		<link href="<c:url value="include/css/agency.css" />" rel="stylesheet">
		<!-- <link href="<c:url value="include/css/font-awesome.css" />" rel="stylesheet"> -->
		<link href="<c:url value="include/css/style.css" />" rel="stylesheet">
		
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
	    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
	    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
		
		<script src="<c:url value="include/js/jquery-3.1.1.js" />"></script>
		<script src="<c:url value="include/js/bootstrap.js" />"></script>
		<script src="<c:url value="include/js/agency.js" />"></script>
	</head>
	
	<body id="page-top" class="index">
		<!-- Navigation -->
	    <nav id="mainNav" class="navbar navbar-default navbar-custom navbarCustom navbar-fixed-top">
	        <div class="container">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header page-scroll">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
	                </button>
	                <a class="navbar-brand page-scroll" href="#page-top">Cours en ligne ISTV</a>
	            </div>
	
	            <!-- Collect the nav links, forms, and other content for toggling -->
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav navbar-right">
	                    <li class="hidden">
	                        <a href="#page-top"></a>
	                    </li>
	                    
	                    <c:choose>
						    <c:when test="${uId==null}">
						        <li>
			                        <c:url value="/" var="hiAction" />
 									<a href="${hiAction}">Connexion</a>
			                    </li>
			                    
			                    <li>
			                        <c:url value="/createUserForm" var="hiAction" />
 									<a href="${hiAction}">Inscription</a>
			                    </li>
						    </c:when>    
						    <c:otherwise>
						        
						        <li>
	                        		<c:url value="/myCourses" var="profilLink" />
	                        		<a class="page-scroll" href="${profilLink}"><span class="glyphicon glyphicon-education" aria-hidden="true"></span> Cours</a>
			                    </li>
			                    <li>
			                        <c:url value="/forum" var="forumLink" />
			                        <a class="page-scroll" href="${forumLink}"><span class="glyphicon glyphicon-leaf"></span> Forum</a>
			                    </li>
			                    
			                    <li class="dropdown">
					              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> ${uLogin} <span class="caret"></span></a>
					              <ul class="dropdown-menu">
					                
					                <c:choose>
								    <c:when test="${uStatut=='admin'}">
								        
										<li>					                	
						                	<c:url value="/allUsers" var="profilLink" />
						                	<a href="${profilLink}" style="font-size:14px;"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Membres</a>
					                	</li>
						                
						                <li>				                        
					                        <c:url value="/allCourses" var="hiAction" />
		 									<a href="${hiAction}" style="font-size:14px;"><span class="glyphicon glyphicon-education" aria-hidden="true"></span> Cours</a>
					                    </li>
					                    
					                    <li>				                        
					                        <c:url value="/allForum" var="hiAction" />
		 									<a href="${hiAction}" style="font-size:14px;"><span class="glyphicon glyphicon-leaf"></span> Forum</a>
					                    </li>	
								        <li role="separator" class="divider"></li>
								    </c:when>    
								</c:choose>
					                
					                <li>					                	
					                	<c:url value="/profil" var="profilLink" ><c:param name="id" value="${uId}"/></c:url>
					                	<a href="${profilLink}" style="font-size:14px;"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Profil</a>
				                	</li>
					                
					                <li>				                        
				                        <c:url value="/logout" var="hiAction" />
	 									<a href="${hiAction}" style="font-size:14px;"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> Déconnexion</a>
				                    </li>
					              </ul>
					            </li>		                    
						    </c:otherwise>
						</c:choose>
	                    
	                </ul>
	            </div>
	            <!-- /.navbar-collapse -->
	        </div>
	        <!-- /.container-fluid -->
	    </nav>
		
		
			