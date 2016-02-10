package de.janbrodda.shootingticker.client;

import de.janbrodda.shootingticker.client.gui.GUI;
import de.janbrodda.shootingticker.client.settings.Settings;

public class Main {

    public static void main(String[] args) {
		Settings s = Settings.get();
		s.apiUrl = "http://localhost:8080";
		s.apiKey = "fdhdfdrshger";
		s.competitionBasePath = "C:/tmp";
		
        GUI.main(args);
		
		/**
		 * Notes:
		 * - upload GUI should show competition teams when selecting from localCompetitionsDropdown
		 *
		 */
    }
}
