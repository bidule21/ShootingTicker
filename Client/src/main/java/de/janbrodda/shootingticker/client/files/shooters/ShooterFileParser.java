package de.janbrodda.shootingticker.client.files.shooters;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.janbrodda.shootingticker.client.data.Competition;
import de.janbrodda.shootingticker.client.data.Shooter;
import de.janbrodda.shootingticker.client.data.Shot;
import de.janbrodda.shootingticker.client.data.Team;

public class ShooterFileParser {
	protected ShooterFileParser() {
	}

	public static Competition parseCompetition(List<File> files) {
		Map<String, Team> teams = new HashMap<>();

		for (File file : files) {
			Shooter shooter = parseShooter(file);
			Team team = null;

			if (! teams.containsKey(shooter.teamName)) {
				Team newTeam = new Team();
				newTeam.name = shooter.teamName;
				teams.put(newTeam.name, newTeam);
			}

			team = teams.get(shooter.teamName);
			team.shooters.add(shooter);
		}

		Competition competition = new Competition();

		for (Team team : teams.values()) {
			Collections.sort(team.shooters, new ShooterComparatorByLane());
			competition.teams.add(team);
		}

		competition.name = competition.teams.get(0).name + " : " + competition.teams.get(1).name;

		return competition;
	}

	public static Shooter parseShooter(File file) {
		if (file.isDirectory()) {
			throw new IllegalArgumentException("The File can't be a Directory.");
		}

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			doc.getDocumentElement().normalize();
			Shooter shooter = new Shooter();

			Element resultRecordElement = (Element) doc.getElementsByTagName("ResultRecord").item(0);
			shooter.laneNumber = Integer.parseInt(resultRecordElement.getAttribute("LaneNo"));

			Element shooterElement = (Element) doc.getElementsByTagName("Shooter").item(0);
			shooter.firstName = shooterElement.getElementsByTagName("GivenName").item(0).getTextContent();
			shooter.lastName = shooterElement.getElementsByTagName("FamilyName").item(0).getTextContent();

			Element teamElement = (Element) doc.getElementsByTagName("Team").item(0);
			shooter.teamName = teamElement.getElementsByTagName("Name").item(0).getTextContent();

			Element totalElement = (Element) doc.getElementsByTagName("Total").item(0);
			shooter.result = Double.parseDouble(totalElement.getElementsByTagName("Result").item(0).getTextContent()) / 10;

			Element shotNoTotalElement = (Element) doc.getElementsByTagName("ShotNoTotal").item(0);
			shooter.shotNumber = Integer.parseInt(shotNoTotalElement.getTextContent());

			Element aimingDataElement = (Element) doc.getElementsByTagName("AimingData").item(0);

			NodeList shotElements = aimingDataElement.getElementsByTagName("Shot");
			for (int i = 0; i < shotElements.getLength(); i++) {
				Element shotElement = (Element) shotElements.item(i);
				Shot shot = new Shot();

				Element coordinatesElement = (Element) shotElement.getElementsByTagName("Coordinate").item(0);
				Element innerCoordinatesElement = (Element) coordinatesElement.getElementsByTagName("CCoordinate").item(0);
				shot.x = Integer.parseInt(innerCoordinatesElement.getElementsByTagName("X").item(0).getTextContent());
				shot.y = Integer.parseInt(innerCoordinatesElement.getElementsByTagName("Y").item(0).getTextContent());

				Element ringValueElement = (Element) shotElement.getElementsByTagName("RingValue").item(0);
				shot.result = Double.parseDouble(ringValueElement.getElementsByTagName("Result").item(0).getTextContent()) / 10;

				shooter.shots.add(shot);
			}

			NodeList seriesElements = aimingDataElement.getElementsByTagName("Series");
			for (int i = 0; i < seriesElements.getLength(); i++) {
				Element seriesElement = (Element) seriesElements.item(i);

				Element valueSerieElement = (Element) seriesElement.getElementsByTagName("ValueSerie").item(0);
				Double serie = Double.parseDouble(valueSerieElement.getElementsByTagName("Result").item(0).getTextContent()) / 10;

				shooter.series.add(serie);
			}

			return shooter;

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
