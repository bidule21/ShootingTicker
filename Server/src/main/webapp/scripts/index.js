function AppViewModel() {
	var self = this;
	self.competitions = ko.observable([]);
	self.loaded = ko.observable(false);

	self.updateView = function() {
		$.getJSON("/api/get", function(data) {
			self.competitions(data.data.competitions);
			self.competitions().forEach(function(competition) {
				if (competition.status == "Running") {
					competition.statusText = "Wettkampf läuft";
				} else if (competition.status == "Planned") {
					competition.statusText = "Hat noch nicht begonnen";
				} else if (competition.status == "Finished") {
					competition.statusText = "Wettkampf beendet";
				} else {
					competition.statusText = "Kein Status verfügbar";
				}
			});
			
			self.loaded(true);
			ko.applyBindings(self);
		});
	};

	self.openCompetition = function(data) {
		document.location.href = "/live/" + data.id;
	};
}
var model = new AppViewModel();
model.updateView();