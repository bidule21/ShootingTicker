package de.janbrodda.shootingticker.client.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Competition {
	public String name;
	public int remainingSeconds;
	public int numShots;
	public long id;
	public long timestamp;
	public List<Team> teams = new ArrayList<>();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Competition withId(long id) {
		this.id = id;
		return this;
	}

	public Competition withNumShots(int numShots) {
		this.numShots = numShots;
		return this;
	}

	public Competition withRemainingSeconds(int remainingSeconds) {
		this.remainingSeconds = remainingSeconds;
		return this;
	}
}
