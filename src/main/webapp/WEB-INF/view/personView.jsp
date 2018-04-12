<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Spring MVC Form Handling</title>
	</head>
	<body>
	
		<h2>Submitted Person Information</h2>
		<table>
			<tr>
				<td>Id :</td>
				<td>${person.id}</td>
			</tr>
			<tr>
				<td>Name :</td>
				<td>${person.name}</td>
			</tr>
			<tr>
				<td>Date of birth :</td>
				<td>${person.dateOfBirth}</td>
			</tr>
			<tr>
				<td>Password :</td>
				<td>${person.password}</td>
			</tr>
			<tr>
				<td>Country :</td>
				<td>${person.country}</td>
			</tr>
			<tr>
				<td>Favourite Languages :</td>
				<td><c:forEach items="${person.favouriteLanguage}" var="current">[<c:out value="${current}" />]</c:forEach></td>
			</tr>
		</table>
		<a href="person">Add another person</a>
	</body>
</html>
