<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Santwanivk Company Home Page</title>
</head>
<body>
	<h2>Santwanivk Company Home Page</h2>
	<br> Welcome to the santwanivk company Home Page
	<br> User :
	<security:authentication property="principal.username" />
	<br> Roles :
	<security:authentication property="principal.authorities" />
	<hr>
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				Meeting(For Manager Only)</a>
		</p>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/admin">Administrator
				Meeting(For Admin Only)</a>
		</p>
	</security:authorize>
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout">
	</form:form>
</body>
</html>