package de.janbrodda.shootingticker.client;

import com.google.gson.Gson;

import de.janbrodda.shootingticker.client.data.Competition;
import de.janbrodda.shootingticker.client.files.FileWatcher;
import de.janbrodda.shootingticker.client.files.shooters.ShooterFileLister;
import de.janbrodda.shootingticker.client.files.shooters.ShooterFileParser;


public class Main {

	public static void main(String[] args) {
		//final String filePath = "/home/jan/Dokumente/gdrive/Projekte/ShootingTicker/SampleData/15_09_23_Hamm_vs_Hamm_3/15_09_23_SG_Hamm_-_SG_Hamm_III";
		final String filePath = "C:/tmp";

		FileWatcher watcher = new FileWatcher();
		watcher.addFileHandler(new Runnable() {
			@Override
			public void run() {
				Competition c = ShooterFileParser.parseCompetition(ShooterFileLister.getUniqueShooterFiles(filePath));
				c.remainingSeconds = 0;
				c.numShots = 40;
				c.id = 5629499534213120L;

				Gson gson = new Gson();

				System.out.println(c.toJson());
			}
		});
		watcher.addDirectoryHandler(new Runnable() {
			@Override
			public void run() {
				System.out.println("dirs changed");

			}
		});
		watcher.watchDirectory(filePath);
	}
}
