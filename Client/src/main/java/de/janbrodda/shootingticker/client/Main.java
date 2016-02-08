package de.janbrodda.shootingticker.client;

import de.janbrodda.shootingticker.client.settings.Settings;
import de.janbrodda.shootingticker.client.api.API;
import de.janbrodda.shootingticker.client.app.App;
import de.janbrodda.shootingticker.client.data.Competition;
import de.janbrodda.shootingticker.client.gui.GUI;

public class Main {

	public static void main(String[] args) {
		// final String filePath =
		// "/home/jan/Dokumente/gdrive/Projekte/ShootingTicker/SampleData/15_09_23_Hamm_vs_Hamm_3/15_09_23_SG_Hamm_-_SG_Hamm_III";
		final String filePath = "C:/tmp";

		Settings s = Settings.get();

		s.useProxy = false;
		s.proxyHost = "proxy";
		s.proxyPort = 8080;
		s.apiKey = "fdhdfdrshger";
		s.apiUrl = "http://localhost:8080";
		// s.competitionBasePath = "C:/Custom
		// Apps/Github_Data/ShootingTicker/SampleData";
		// s.competitionBasePath = "C:/tmp";
		s.competitionBasePath = "/home/jan/git/ShootingTicker/SampleData/15_09_23_Hamm_vs_Hamm_3";
		s.save();

		/*App p = App.get();
		p.selectCompetition(
				new Competition()
				.withId(5629499534213120L)
				.withNumShots(30)
				.withName("test")
				.withRemainingSeconds(0));
		p.selectCompetitionFolder(p.getAvailableFolders().get(0).file);
		//p.startCompetitionUpload();
		//p.stopCompetitionUpload();
		//p.startCompetitionUpload();
                        */
		
		GUI.main(args);

		// Check if upload is threaded..
		while (true) {
			try {
				Thread.sleep(2000000000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
