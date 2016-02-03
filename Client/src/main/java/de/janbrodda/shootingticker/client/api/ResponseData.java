package de.janbrodda.shootingticker.client.api;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import de.janbrodda.shootingticker.client.data.Competition;

public class ResponseData {
	public long id;
	public long timestamp;
	public List<Competition> competitions;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
