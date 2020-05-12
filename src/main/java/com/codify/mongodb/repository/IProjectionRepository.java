package com.codify.mongodb.repository;

import java.util.List;


import com.codify.mongodb.domain.AvgRatingModel;

/**
 * @author upadhyaybs
 *
 */
public interface IProjectionRepository  {
	
	public List<AvgRatingModel> getAllRestuarants();
	
		
}
