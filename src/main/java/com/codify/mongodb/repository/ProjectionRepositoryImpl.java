package com.codify.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;

import com.codify.mongodb.domain.AvgRatingModel;
import com.codify.mongodb.domain.Restaurant;

import lombok.RequiredArgsConstructor;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

/**
 * @author upadhyaybs
 *
 */
@Service
@RequiredArgsConstructor
public class ProjectionRepositoryImpl implements IProjectionRepository {
	
	private final MongoTemplate template;

	@Override
	public List<AvgRatingModel> getAllRestuarants() {
		ProjectionOperation projectMatchModel=project()
				.andExpression("name").as("name")
				.andExpression("type").as("type")
				.andExpression("{$avg : '$reviews.rating'}").as("avgRating");
		
		Aggregation avgRatingAggression=newAggregation(Restaurant.class,projectMatchModel);
		
		return this.template.aggregate(avgRatingAggression, Restaurant.class,AvgRatingModel.class).getMappedResults();
	}

}
