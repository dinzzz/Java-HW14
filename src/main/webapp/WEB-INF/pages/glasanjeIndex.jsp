<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<h1>${poll.title}</h1>
	<p>${poll.message}</p>
	<ol>
		<c:forEach var="u" items="${options}">
			<li><a href="glasanje-glasaj?id=${u.id}">${u.title}</a></li>
		</c:forEach>
	</ol>

	<a href="index.html"> Back to Home</a>
</body>
</html>