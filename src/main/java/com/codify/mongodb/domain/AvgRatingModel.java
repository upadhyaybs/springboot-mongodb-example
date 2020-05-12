package com.codify.mongodb.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author upadhyaybs
 *
 */
@Getter
@Setter
public class AvgRatingModel {
	
	private String id;
	private String name;
	private String type;
	private int avgRating;
	

}
