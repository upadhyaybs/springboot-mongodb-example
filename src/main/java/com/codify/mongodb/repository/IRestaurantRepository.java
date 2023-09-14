package com.codify.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.codify.mongodb.domain.Restaurant;

/**
 * @author upadhyaybs
 *
 */
@Repository
public interface IRestaurantRepository extends MongoRepository<Restaurant, String>{
	
	public List<Restaurant> findAllByNameContains(String name);
	
	public List<Restaurant> findAllByType(String type);
	
	@Query("{'addresses.city':{$eq : ?0}}")
	public List<Restaurant> findAllByCity(String city);
	
	@Query("{'addresses.state':{$eq : ?0}}")
	public List<Restaurant> findAllByState(String city);
	
	@Query("{'addresses.zipCode':{$eq : ?0}}")
	public List<Restaurant> findAllByZipCode(long zipCode);
	
	@Query("{'reviews.rating':{$eq : ?0}}")
	public List<Restaurant> findAllByRating(int rating);
	
	public List<Restaurant> findAllByTypeIsNot(String type);
	
	public List<Restaurant> findAllBy(TextCriteria criteria);
	
	
	

}
