package de.janbrodda.shootingticker.client;

import de.janbrodda.shootingticker.client.api.API;
import de.janbrodda.shootingticker.client.app.App;
import de.janbrodda.shootingticker.client.data.Competition;


public class Main {

	public static void main(String[] args) {
		//final String filePath = "/home/jan/Dokumente/gdrive/Projekte/ShootingTicker/SampleData/15_09_23_Hamm_vs_Hamm_3/15_09_23_SG_Hamm_-_SG_Hamm_III";
		final String filePath = "C:/tmp";

		Settings s = Settings.get();

		API a = API.get();

		s.useProxy = false;
		s.proxyHost = "proxy";
		s.proxyPort = 8080;
		s.apiKey = "fdhdfdrshger";
		s.apiUrl = "http://localhost:8080";
		//s.competitionBasePath = "C:/Custom Apps/Github_Data/ShootingTicker/SampleData";
		s.competitionBasePath = "C:/tmp";
		s.save();


		/*Competition c = ShooterFileParser.parseCompetition(ShooterFileLister.getUniqueShooterFiles(filePath));
		c.numShots = 40;
		c.remainingSeconds = 0;
		c.name = "öäüÜÄÖß";
		c.id = 5629499534213120L;*/

		App p = App.get();
		p.selectCompetition(new Competition()
				.withId(5629499534213120L)
				.withNumShots(30)
				.withRemainingSeconds(0)
				);
		p.selectCompetitionFolder(p.getAvailableFolders().get(0).file);
		p.startCompetitionUpload();
		//p.stopCompetitionUpload();
		//p.startCompetitionUpload();

	}
}
