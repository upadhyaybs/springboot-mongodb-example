package com.codify.mongodb.util;

import java.util.UUID;

/**
 * @author upadhyaybs
 *
 */
public final class TrackingIDGenerator {
	
	private TrackingIDGenerator() {}
	
	public static String getTrackingID() {
		return UUID.randomUUID().toString();
	}

}
