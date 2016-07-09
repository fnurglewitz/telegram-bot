package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a point on the map.
 *
 */
public final class Location {

	private final static String JSON_FIELD_LONGITUDE = "longitude";
	private final static String JSON_FIELD_LATITUDE = "latitude";

	/**
	 * Longitude as defined by sender
	 */
	@JsonProperty(JSON_FIELD_LONGITUDE)
	public final Float longitude;

	/**
	 * Latitude as defined by sender
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final Float latitude;

	/**
	 * 
	 * @param longitude
	 *          Longitude as defined by sender
	 * @param latitude
	 *          Latitude as defined by sender
	 */
	public Location(@JsonProperty(JSON_FIELD_LONGITUDE) Float longitude,
			@JsonProperty(JSON_FIELD_LATITUDE) Float latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public static class Util {

		private static final double earthRadius = 6372.8;

		private static double haversine(double lat1, double lon1, double lat2, double lon2) {
			double dLat = Math.toRadians(lat2 - lat1);
			double dLon = Math.toRadians(lon2 - lon1);
			lat1 = Math.toRadians(lat1);
			lat2 = Math.toRadians(lat2);

			double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
			double c = 2 * Math.asin(Math.sqrt(a));
			return earthRadius * c;
		}

		public static double distance(Location l1, Location l2) {
			return haversine(l1.latitude, l1.longitude, l2.latitude, l2.longitude);
		}

	}

}
