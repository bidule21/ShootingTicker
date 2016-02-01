package de.janbrodda.shootingticker.client.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Shot {
	public int x;
	public int y;
	public double result;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
