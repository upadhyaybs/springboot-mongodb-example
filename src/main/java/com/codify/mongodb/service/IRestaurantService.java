package com.codify.mongodb.service;

import java.util.Collection;
import java.util.List;

import com.codify.mongodb.domain.Request;
import com.codify.mongodb.domain.Restaurant;
import com.codify.mongodb.exception.ResourceNotFoundException;

/**
 * @author upadhyaybs
 *
 */
public interface IRestaurantService {
	
	public Collection<Restaurant> insertAll(Collection<Restaurant> restaurant);
	
	public Restaurant insertRestaurant(Restaurant restaurant);
	
	public Restaurant findById(String id) throws ResourceNotFoundException;
	
	public List<Restaurant> findAllByName(String name)throws ResourceNotFoundException;
	
	public List<Restaurant> findAllByZip(long zipCode)throws ResourceNotFoundException;
	
	public List<Restaurant> findAllByCity(String city)throws ResourceNotFoundException;
	
	public List<Restaurant> findAllByState(String state)throws ResourceNotFoundException;
	
	public List<Restaurant> findAllByRating(int rating)throws ResourceNotFoundException;
	
	public List<Restaurant> findAllByType(String type)throws ResourceNotFoundException;
	
	public List<Restaurant> findAllByTypeIsNot(String type)throws ResourceNotFoundException;
	
	public List<Restaurant> findAllByZipAndType(long zipCode)throws ResourceNotFoundException;
	
	public List<Restaurant> findAll()throws ResourceNotFoundException;
	
	public List<Restaurant> filterByFields(Request request)throws ResourceNotFoundException;
	
	public List<Restaurant> fullTextSearch(Request request)throws ResourceNotFoundException;
	
	public Restaurant updateRestaurant(Restaurant restaurant);
	
	public void deleteRestaurant(String id);
	
	public void deleteCollection();

}
