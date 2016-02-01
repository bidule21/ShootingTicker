package de.janbrodda.shootingticker.client.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Shooter {
	public String firstName;
	public String lastName;
	public int laneNumber;
	public String teamName;
	public double result;
	public int shotNumber;
	public List<Shot> shots = new ArrayList<>();
	public List<Double> series = new ArrayList<>();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
