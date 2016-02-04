package de.janbrodda.shootingticker.server;

import java.util.List;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import de.janbrodda.shootingticker.server.data.Competition;

public class Database {
	private static MemcacheService CACHE = MemcacheServiceFactory.getMemcacheService();
	private static boolean CACHE_READY = false;

	private static enum CACHE_IDENTIFIERS {
		COMPETITION_LIST
	}

	public static Competition getCompetitionById(long competitionId) {
		Competition result = (Competition) CACHE.get(competitionId);

		if (result == null) {
			result = ObjectifyService.ofy().load().type(Competition.class).id(competitionId).now();
			CACHE.put(competitionId, result);
		}

		return result;
	}

	public static List<Competition> getAllCompetitions() {

		@SuppressWarnings("unchecked")
		List<Competition> result = (List<Competition>) CACHE.get(CACHE_IDENTIFIERS.COMPETITION_LIST);

		if (result == null) {
			result = ObjectifyService.ofy().load().type(Competition.class).order("-date").list();
			CACHE.put(CACHE_IDENTIFIERS.COMPETITION_LIST, result);
		}

		return result;
	}

	public static long saveCompetition(Competition competition) {
		Key<Competition> insertedKey = ObjectifyService.ofy().save().entity(competition).now();

		if (competition.id != null) {
			CACHE.delete(competition.id);
		}
		CACHE.delete(CACHE_IDENTIFIERS.COMPETITION_LIST);
		return insertedKey.getId();
	}

	public static void deleteCompetition(long competitionId) {
		ObjectifyService.ofy().delete().type(Competition.class).id(competitionId).now();

		CACHE.delete(competitionId);
		CACHE.delete(CACHE_IDENTIFIERS.COMPETITION_LIST);
	}

	public static void preloadCache() {
		if (!CACHE_READY) {
			List<Competition> competitions = Database.getAllCompetitions();
			for (Competition competition : competitions) {
				Database.getCompetitionById(competition.id);
				System.out.println(competition);
			}
		}
		CACHE_READY = true;
	}
}
