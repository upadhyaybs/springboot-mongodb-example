package com.codify.mongodb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author upadhyaybs
 *
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReview {
	
	@JsonProperty("rating")
	private int rating;

}
