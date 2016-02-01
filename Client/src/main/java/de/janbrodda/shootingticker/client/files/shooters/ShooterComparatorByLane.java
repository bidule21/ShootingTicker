package de.janbrodda.shootingticker.client.files.shooters;

import java.util.Comparator;

import de.janbrodda.shootingticker.client.data.Shooter;

public class ShooterComparatorByLane implements Comparator<Shooter> {
	@Override
	public int compare(Shooter o1, Shooter o2) {
		Integer f1 = o1.laneNumber;
		Integer f2 = o2.laneNumber;

		return f1.compareTo(f2);
	}
}
