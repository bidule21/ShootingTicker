<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<link type="text/css" rel="stylesheet" href="/stylesheets/index.css" />
<title>ShootingTicker</title>
</head>

<body>

	<div id="loading" data-bind="visible: !loaded()">
		<div id="text_loading" class="message" data-bind="visible: !loaded()">
			<img src="/images/loading.gif"> <br> <br>Wettkämpfe
			werden geladen. <br> Bite noch einen Moment Geduld..
		</div>
	</div>

	<div id="main">
		<!-- ko if: (competitions().length == 0) -->
		<b>Noch keine Wettkämpfe vorhanden</b>
		<!-- /ko -->

		<div id="competitionList" data-bind="foreach: competitions()">
			<div class="competition clearfix"
				data-bind="click: $parent.openCompetition">
				<div class="title" data-bind="text: name"></div>
				<div class="date"
					data-bind="text: $data.date != null ? date : 'Kein Datum bekannt'"></div>
				<div class="status" data-bind="text: statusText"></div>
			</div>
		</div>
	</div>

</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/knockout/3.4.0/knockout-min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script src="/scripts/index.js"></script>
</html>