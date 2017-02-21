<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


 
<div>
<h2>New Person Form</h2>
 
<sf:form method="POST" modelAttribute="connexion"
action="/soutienISTV/person/add"
enctype="multipart/form-data">
<fieldset>
<table cellspacing="0">
<tr>
<th><label for="name">Name:</label></th>
<td><sf:input path="login" id="name"/></td>
</tr>
<tr>
<th><label for="image">Image (in JPEG format only):</label></th>
<td><input name="avatar" type="file"/>
</tr>
<tr>
<th><input type="Submit" value="Submit"/></th>
<td></td>
</tr>
</table>
</fieldset>
</sf:form>
 
</div>

</body>
</html>