package de.janbrodda.shootingticker.client.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Response {
	public String status;
	public ResponseData data;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
