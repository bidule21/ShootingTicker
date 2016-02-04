<%@page import="de.janbrodda.shootingticker.server.Settings"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>ShootingTicker Debug</title>
</head>
<body>
<hr>
<b>Insert/Update Competition</b>
<form action="/api/put" method="post">
	Competition Data:<br>
	<textarea name="competition" style="width:500px; height:500px"></textarea>
	<br>	
	API Key:
	<input name="apikey" value="<%=Settings.apiKey %>">
	<br>
	<input type="submit" value="Save">
</form>

<hr>
<b>Delete Competition</b>
<form action="/api/delete" method="post">
	Competition Id:
	<input name="competitionId">
	<br>
	API Key:
	<input name="apikey" value="<%=Settings.apiKey %>">
	<br>
	<input type="submit" value="Delete">
</form>

</body>
</html>