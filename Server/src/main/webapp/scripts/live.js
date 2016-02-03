var Target = {
	settings : {
		// how often should we poll for new Data
		refreshInterval : 5000,
		// how many shots will be displayed on the target
		numShotsPerTarget : 5,
		// min time to block page while initial loading
		minLoadingTime : 1000,
		// color of the leading shooter
		winnerTargetColor : 'yellow',
		// display all shots if shooter is finished
		showFinishedTargets : true,

		// border around a single ring
		ringBorder : 5,
		// radius of the inner ring (10)
		innerRingRadius : 5,
		// radius of every other ring (1-9)
		ringRadius : 50,
		// radius of the shot (should be same as ringRadius)
		shotRadius : 50,

		// color of the outer rings
		lowRingColor : 'white',
		// color of the inner rings
		medRingColor : 'green',
		// fill color of the innermost ring
		highRingColor : 'black',
		// text color if the outer rings
		lowRingTextColor : 'black',
		// text color of the inner rings
		medRingTextColor : 'white',
		// inner rings start number
		medRingNumber : 4,
		// innermost ring start number
		highRingNumber : 10,
		// font size of ring number text
		ringNumberFontSize : 20,

		// color of an inactive shot
		inactiveShotColor : '#333',
		// color of a shot with low value
		lowShotColor : 'blue',
		// color of a shot with medium value
		medShotColor : 'yellow',
		// color of a shot with high value
		highShotColor : 'red',
		// everything higher is a medium shot
		medShotResult : 9.0,
		// everything higher is a high shot
		highShotResult : 10.0,

		// font size of the shot number
		shotNumberFontSize : 50,
		
		// color of the current shot
		shotnumberActiveColor : 'black',
		// color of shots in the background
		shotNumberInactiveColor : '#ccc',

		// value to divide the original shot coordinates for scaling
		originalShotScale : 4.9,
		// ssize of the canvas to calculate coordinates with
		canvasSize : 1000,

		// list of possible zoomfactors. Have to be same length as -zoomFactorLimits-
		zoomFactors : [ 1, 1.9, 2.2, 2.5, 2.8, 3.3, 4.2, 5, 6 ],
		// list of zoom factor limits. Values have to match factors defined in -zoomFactors-
		zoomFactorLimits : [ 900, 800, 700, 600, 450, 300, 150, 50, 0 ]
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

		var shotColor;
		if (!lastShot) {
			shotColor = this.settings.inactiveShotColor;
		} else if (shot.result < this.settings.medShotResult) {
			shotColor = this.settings.lowShotColor;
		} else if (shot.result < this.settings.highShotResult) {
			shotColor = this.settings.medShotColor;
		} else {
			shotColor = this.settings.highShotColor;
		}
		
		shot.x = shot.x / this.settings.originalShotScale * zoomFactor;
		shot.y = shot.y / this.settings.originalShotScale * zoomFactor;

		canvas.drawArc({
			fillStyle : shotColor,
			x : this.settings.canvasSize / 2 + shot.x,
			y : this.settings.canvasSize / 2 + shot.y,
			radius : this.settings.shotRadius,
			strokeStyle : 'black',
			strokeWidth : this.settings.ringBorder / zoomFactor,
			scale : zoomFactor
		});

		var textColor = lastShot ? this.settings.shotnumberActiveColor
				: this.settings.shotNumberInactiveColor;

		canvas.drawText({
			fillStyle : textColor,
			strokeWidth : 0,
			x : this.settings.canvasSize / 2 + shot.x,
			y : this.settings.canvasSize / 2 + shot.y,
			fontSize : this.settings.shotNumberFontSize * zoomFactor,
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
				
		var ringColor;
		var textColor;
		
		if (number < this.settings.medRingNumber) {
			ringColor = this.settings.lowRingColor;
			textColor = this.settings.lowRingTextColor;
		} else if (number < this.settings.highRingNumber){
			ringColor = this.settings.medRingColor;
			textColor = this.settings.medRingTextColor;
		} else {
			ringColor = this.settings.highRingColor;
		}

		var radius = number == 10 ? this.settings.innerRingRadius
				: this.settings.ringRadius * (10 - number);
		
		var ringWidth = number == 10 ? radius - this.settings.innerRingRadius
				: radius - this.settings.ringRadius * (10 - number-zoomFactor / 2);

		canvas.drawArc({
			fillStyle : ringColor,
			x : (this.settings.canvasSize / 2),
			y : (this.settings.canvasSize / 2),
			radius : radius,
			strokeStyle : 'black',
			strokeWidth : this.settings.ringBorder / zoomFactor,
			scale : zoomFactor
		});
		
		if (number < 9){
			var base = this.settings.canvasSize / 2;
			var plus = this.settings.canvasSize / 2 + radius * zoomFactor - ringWidth - 5;
			var minus = this.settings.canvasSize / 2 - radius * zoomFactor + ringWidth + 5;
			
			this.drawRingNumber(canvas, number, textColor, base, plus);
			this.drawRingNumber(canvas, number, textColor, plus, base);
			this.drawRingNumber(canvas, number, textColor, base, minus);
			this.drawRingNumber(canvas, number, textColor, minus, base);
		}
	},
	
	drawRingNumber : function(canvas, number, textColor, x, y){
		var zoomFactor = canvas.attr("data-zoom") != null ? canvas
				.attr("data-zoom") : 1;
		var radius = number == 10 ? this.settings.innerRingRadius
				: this.settings.ringRadius * (10 - number);
		var ringWidth = number == 10 ? radius - this.settings.innerRingRadius
				: radius - this.settings.ringRadius * (10 - number-zoomFactor / 2);
		
		canvas.drawText({
			fillStyle : textColor,
			strokeWidth : 0,
			y : y,
			x : x,
			fontSize : this.settings.ringNumberFontSize * zoomFactor,
			fontFamily : 'Arial',
			text : number
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

		var shotList;
		if (shooter.shots.length >= self.competition().numShots && Target.settings.showFinishedTargets){
			shotList =shooter.shots;
		} else {
			shotList = shooter.shots.slice(0 - Target.settings.numShotsPerTarget);
		}	
		
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
