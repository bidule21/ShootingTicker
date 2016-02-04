<%@page import="de.janbrodda.shootingticker.server.Database"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="de.janbrodda.shootingticker.server.data.Competition"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	long competitionId = 0;
	try {
		competitionId = Long.parseLong(request.getPathInfo().substring(1));
	} catch (NumberFormatException e) {
	}

	Competition competition = Database.getCompetitionById(competitionId);
	if (competition == null){
		competition = new Competition();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<link type="text/css" rel="stylesheet" href="/stylesheets/live.css" />
<script>
	var competitionId =
<%=competitionId%>
	;
</script>
<title><%=competition.name%> | ShootingTicker</title>
</head>

<body>

	<!-- <div id="loading" data-bind="style: { 'opacity': !loaded() ? 100 : 0 }"></div> -->

	<div id="loading" data-bind="visible: (!loaded() || !active())">

		<div id="text_loading" class="message"
			data-bind="visible: (loaded() && !active())">
			<img src="/images/loading.gif"> <br> <br>Der Wettkampf
			hat noch nicht begonnen.<br>Die Seite aktualisiert automatisch..
		</div>

		<div id="text_loading" class="message" data-bind="visible: !loaded()">
			<img src="/images/loading.gif"> <br> <br>Der
			Wettkampf wird geladen.<br> Bite noch einen Moment Geduld..
		</div>
	</div>

	<div id="main">

		<h1 data-bind="text: competition().name"></h1>
		<button data-bind="click: backToIndex">Zur Übersicht</button>

		<div class="competition" data-bind="foreach: competition().teams">
			<div class="team clearfix">
				<h2 data-bind="text: name"></h2>
				<div class="shooters" data-bind="foreach: shooters">
					<div class="shooter">
						<div class="header">
							<p class="number"
								data-bind="text: laneNumber < 10 ? '0' + laneNumber.toString() : laneNumber"></p>
							<p class="name" data-bind="text: firstName+' '+lastName"></p>
						</div>
						<div class="target">
							<canvas height="1000" width="1000"
								data-bind="attr: { id: 'target_'+laneNumber }"></canvas>
						</div>
						<div class="footer">
							<table class="seriesList">
								<tr data-bind="foreach: series">
									<td class="series" data-bind="text: $data"></td>
								</tr>
								<tr>
									<td class="lastShot"
										data-bind="text: shots[shots.length - 1].result.toFixed(1)"></td>
									<!-- ko foreach: series.splice(0, 2) -->
									<td></td>
									<!-- /ko -->
									<td class="result" data-bind="text: result"></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/knockout/3.4.0/knockout-min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="/scripts/jcanvas.min.js"></script>
<script src="/scripts/live.js"></script>
</html>
