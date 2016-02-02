var Target = {
	settings : {
		refreshInterval : 5000,
		numShotsPerTarget : 5,
		minLoadingTime : 1000,
		winnerTargetColor : 'yellow',

		innerRingRadius : 10,
		ringRadius : 50,
		ringBorder : 5,

		lowRingColor : 'white',
		highRingColor : 'green',
		highRingNumber : 4,

		inactiveShotColor : '#333',
		lowShotColor : 'blue',
		medShotColor : 'yellow',
		highShotColor : 'red',
		medShotResult : 9.0,
		highShotResult : 10.0,

		shotNumberFontSize : 50,
		shotnumberForegroundColor : 'black',
		shotNumberBackgroundColor : '#ccc',

		originalShotScale : 4.2,
		canvasSize : 1000,

		zoomFactors : [ 1, 1.9, 2.2, 2.5, 2.8, 3.3, 4.2, 5, 6 ],
		zoomFactorLimits : [ 900, 800, 700, 600, 450, 300, 150, 50, 0 ],

		shotRadius : 50
	},

	prepare : function(canvas) {
		this.drawBackground(canvas);
		this.drawRing(canvas, 1);
		this.drawRing(canvas, 2);
		this.drawRing(canvas, 3);
		this.drawRing(canvas, 4);
		this.drawRing(canvas, 5);
		this.drawRing(canvas, 6);
		this.drawRing(canvas, 7);
		this.drawRing(canvas, 8);
		this.drawRing(canvas, 9);
		this.drawRing(canvas, 10);
	},

	drawShot : function(canvas, shot, lastShot) {
		var zoomFactor = canvas.attr("data-zoom") != null ? canvas
				.attr("data-zoom") : 1;

		var color;
		if (!lastShot) {
			color = this.settings.inactiveShotColor;
		} else if (shot.result < this.settings.medShotResult) {
			color = this.settings.lowShotColor;
		} else if (shot.result < this.settings.highShotResult) {
			color = this.settings.medShotColor;
		} else {
			color = this.settings.highShotColor;
		}

		canvas.drawArc({
			fillStyle : color,
			x : this.settings.canvasSize / 2 + shot.x
					/ this.settings.originalShotScale * zoomFactor,
			y : this.settings.canvasSize / 2 + shot.y
					/ this.settings.originalShotScale * zoomFactor,
			radius : this.settings.shotRadius,
			strokeStyle : 'black',
			strokeWidth : this.settings.ringBorder / zoomFactor,
			scale : zoomFactor
		});

		var textColor = lastShot ? this.settings.shotnumberForegroundColor
				: this.settings.shotNumberBackgroundColor;

		canvas.drawText({
			fillStyle : textColor,
			strokeStyle : 'black',
			strokeWidth : 1,
			x : this.settings.canvasSize / 2 + shot.x
					/ this.settings.originalShotScale * zoomFactor,
			y : this.settings.canvasSize / 2 + shot.y
					/ this.settings.originalShotScale * zoomFactor,
			fontSize : this.settings.shotNumberFontSize * zoomFactor,
			fontFamily : 'Arial',
			text : shot.num
		});
	},

	drawBackground : function(canvas) {
		canvas.drawRect({
			fillStyle : 'darkblue',
			x : 0,
			y : 0,
			width : this.settings.canvasSize * 2,
			height : this.settings.canvasSize * 2
		});
	},

	drawRing : function(canvas, number) {
		var zoomFactor = canvas.attr("data-zoom") != null ? canvas
				.attr("data-zoom") : 1;
		var color;
		if (number < this.settings.highRingNumber) {
			color = this.settings.lowRingColor;
		} else {
			color = this.settings.highRingColor;
		}

		var radius = number == 10 ? this.settings.innerRingRadius
				: this.settings.ringRadius * (10 - number);

		canvas.drawArc({
			fillStyle : color,
			x : this.settings.canvasSize / 2,
			y : this.settings.canvasSize / 2,
			radius : radius,
			strokeStyle : 'black',
			strokeWidth : this.settings.ringBorder / zoomFactor,
			scale : zoomFactor
		});
	},

	calculateZoom : function(maxCoordinate) {
		var zoomFactor = 1;
		this.settings.zoomFactorLimits.some(function(limit, index) {
			if (maxCoordinate > limit) {
				zoomFactor = Target.settings.zoomFactors[index];
				return true;
			}
		});
		return zoomFactor;
	},
};

function AppViewModel() {
	var self = this;
	self.competition = ko.observable();
	self.init = false;

	self.loadingTimeOver = ko.observable(false);
	self.loaded = ko.computed(function() {
		return self.loadingTimeOver() && self.competition() != null;
	});
	self.active = ko.computed(function() {
		return self.competition() != null
				&& self.competition().teams[0] != null
				&& self.competition().teams[0].shooters[0] != null;
	});

	// Init the ViewModel. Only called on first Load!
	self.initView = function() {
		if (self.init)
			return;

		self.fetchData(function() {
			ko.applyBindings(self);
			self.init = true;

			setInterval(function() {
				self.updateView();
			}, Target.settings.refreshInterval);

			self.drawTargets();
		});
	};

	// Update the existing ViewModel
	self.updateView = function() {
		self.fetchData(function() {
			self.drawTargets();
		});
	};

	// Fetch new Data. Returns without executing the Callback when no new Data
	// is available
	self.fetchData = function(callback) {
		var timestamp = this.competition() != null ? this.competition().timestamp
				: 0;

		$.getJSON("/api/get/" + competitionId + "?t=" + timestamp, function(
				data) {
			if (data == null)
				return;

			competition = data.data.competitions[0];
			self.competition(competition);

			if (callback != null)
				callback();
		});
	};

	// Draw Targets for all Shooters
	self.drawTargets = function() {
		self.competition().teams.forEach(function(team) {
			team.shooters.forEach(function(shooter) {
				self.drawShooter(shooter);
			});
		});

	};

	// Draw the Target for one Shooter
	self.drawShooter = function(shooter) {
		var canvas = $("#target_" + shooter.laneNumber);

		var shotList = shooter.shots.length >= self.competition().numShots ? shooter.shots
				: shooter.shots.slice(0 - Target.settings.numShotsPerTarget);

		var maxCoordinate = 0;
		shotList.forEach(function(shot) {
			var posX = Math.abs(shot.x);
			var posY = Math.abs(shot.y);

			if (posX > maxCoordinate)
				maxCoordinate = posX;
			if (posY > maxCoordinate)
				maxCoordinate = posY;
		});

		canvas.attr("data-zoom", Target.calculateZoom(maxCoordinate));

		Target.prepare(canvas);

		shotList.forEach(function(shot, index) {
			var lastShot = index == shotList.length - 1 ? true : false;
			shot.num = shooter.shots.indexOf(shot) + 1;
			Target.drawShot(canvas, shot, lastShot);

		});
	};

	// Function to return to Competition List
	self.backToIndex = function() {
		document.location.href = "/";
	};
}

var model = new AppViewModel();
setTimeout(function() {
	model.loadingTimeOver(true);
}, Target.settings.minLoadingTime);
model.initView();
