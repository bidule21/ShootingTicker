package de.janbrodda.shootingticker.client;

import com.google.gson.Gson;

import de.janbrodda.shootingticker.client.api.API;
import de.janbrodda.shootingticker.client.api.WebRequest;
import de.janbrodda.shootingticker.client.data.Competition;
import de.janbrodda.shootingticker.client.files.FileWatcher;
import de.janbrodda.shootingticker.client.files.shooters.ShooterFileLister;
import de.janbrodda.shootingticker.client.files.shooters.ShooterFileParser;


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
		s.save();

		System.out.println("Starting");
		//WebRequest r = new WebRequest(WebRequest.Method.POST, "https://shootingticker.appspot.com/api/put");
		WebRequest r = new WebRequest(WebRequest.Method.POST, "http://localhost:8080/api/put");

		Competition c = new Competition();
		c.numShots = 40;
		c.remainingSeconds = 0;
		c.name = "öäüÜÄÖß";
		c.id = 5629499534213120L;

		r.parameters.put("key", s.apiKey);
		r.parameters.put("competition", c.toJson());

		System.out.println(r.load());
		System.out.println("Finished");

		if (1 + 1 == 2) {
			return;
		}

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
