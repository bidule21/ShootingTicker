/* global ko, moment */

var Settings = {
	refreshInterval : 10000,
	minLoadingTime : 800
};

function AppViewModel() {
	var self = this;
	self.competitions = ko.observableArray();
	self.loadingTimeOver = ko.observable(false);
	self.loaded = ko.computed(function() {
		return self.loadingTimeOver()
				&& self.competitions() != null;
	});

	self.updateView = function() {
		self.fetchData(function() {
			ko.applyBindings(self);

			setInterval(function() {
				self.fetchData();
			}, Settings.refreshInterval);

		});
	};

	self.fetchData = function(callback) {
		$.getJSON("/api/get", function(data) {
			self.competitions.removeAll();
			
			data.data.competitions.forEach(function(competition) {
				if (competition.status == "Running") {
					competition.statusText = "Wettkampf läuft";
				} else if (competition.status == "Planned") {
					competition.statusText = "Hat noch nicht begonnen";
				} else if (competition.status == "Finished") {
					competition.statusText = "Wettkampf beendet";
				} else {
					competition.statusText = "";
				}
				
				var rawDate = new Date(competition.date);
				competition.parsedDate = moment(rawDate).format("DD.MM.YY - HH:mm [Uhr]");
								
				self.competitions.push(competition);
			});

			if (typeof callback === 'function')
				callback();

		});
	};

	self.openCompetition = function(data) {
		document.location.href = "/live/" + data.id;
	};
}
var model = new AppViewModel();
setTimeout(function() {
	model.loadingTimeOver(true);
}, Settings.minLoadingTime);
model.updateView();
