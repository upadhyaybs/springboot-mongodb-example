/**
 * 
 */
package com.codify.mongodb.service;

import java.util.List;

import com.codify.mongodb.domain.AvgRatingModel;

/**
 * @author upadhyaybs
 *
 */
public interface IProjectionService {
	
	public List<AvgRatingModel> getAllRestuarants();

}
