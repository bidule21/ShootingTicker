package de.janbrodda.shootingticker.server.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.Gson;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Competition implements Serializable {
	private static final long serialVersionUID = 7568085078179785935L;

	@Index
	public String name;

	@Id
	public Long id;

	@Index
	public Date date;

	public Integer remainingSeconds;

	public Integer numShots;

	@Index
	public long timestamp;

	public List<Team> teams = new ArrayList<>();

	public Status status;

	public static enum Status {
		Planned,
		Running,
		Finished
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
