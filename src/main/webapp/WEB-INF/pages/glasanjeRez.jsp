<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<style type="text/css">
table.rez td {
	text-align: center;
}
</style>
<meta charset="UTF-8">
</head>
<body
	style="background-color: <%=session.getAttribute("pickedBgColor")%>">

	<h1>Voting results</h1>
	<p>Results to the voting:</p>
	<table border="1" cellspacing="0" class="rez">
		<thead>
			<tr>
				<th>Item</th>
				<th>Number of votes</th>
			</tr>
		</thead>


		<tbody>
			<c:forEach var="u" items="${options}">
				<tr>
					<td>${u.title}</td>
					<td>${u.votes}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

	<h2>Result chart</h2>
	<img alt="Pie-chart" src="glasanje-grafika?id=${poll}" width="400" height="400" />

	 <h2>Results in excel format</h2>
	<p>
		Results are available <a href="glasanje-xls?id=${poll}">here</a>
	</p> 


	<h1>Links to the options</h1>
	<p>Here are the links to the winners:</p>
	<ol>
		<c:forEach var="u" items="${max}">
			<li><a href="${u.link}" target="_blank">${u.title}</a></li>
		</c:forEach>
	</ol>
	
	<a href="index.html"> Back to Home</a>


</body>
</html>