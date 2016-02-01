package de.janbrodda.shootingticker.server.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Shooter implements Serializable {
	private static final long serialVersionUID = 7857520490453452416L;

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
