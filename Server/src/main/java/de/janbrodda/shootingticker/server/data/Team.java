package de.janbrodda.shootingticker.server.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Team implements Serializable {
	private static final long serialVersionUID = -2177653580763584388L;

	public List<Shooter> shooters = new ArrayList<>();
	public String name;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
