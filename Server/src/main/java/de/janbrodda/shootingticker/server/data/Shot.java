package de.janbrodda.shootingticker.server.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Shot implements Serializable {
	private static final long serialVersionUID = 4590312302961592598L;

	public int x;
	public int y;
	public double result;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
