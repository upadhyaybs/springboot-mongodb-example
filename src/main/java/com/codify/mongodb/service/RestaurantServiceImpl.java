package com.codify.mongodb.service;

import java.util.Collection;
import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import com.codify.mongodb.domain.Request;
import com.codify.mongodb.domain.Restaurant;
import com.codify.mongodb.exception.ResourceNotFoundException;
import com.codify.mongodb.repository.IRestaurantRepository;
import com.codify.mongodb.util.QueryBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;

/**
 * @author upadhyaybs
 *
 */
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements IRestaurantService {

	private final IRestaurantRepository repository;

	@Override
	public Collection<Restaurant> insertAll(Collection<Restaurant> restaurants) {
		return repository.saveAll(restaurants);
	}

	@Override
	public Restaurant insertRestaurant(Restaurant restaurant) {
		return repository.save(restaurant);
	}

	@Override
	public Restaurant findById(String id) throws ResourceNotFoundException {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id:" + id + " Not found"));
	}

	@Override
	public List<Restaurant> findAllByName(String name) throws ResourceNotFoundException {
		List<Restaurant> restaurants = repository.findAllByNameContains(name);
		if (restaurants == null || restaurants.isEmpty()) {
			throw new ResourceNotFoundException("Resource(s) by :"+ name+" not found");
		}
		return restaurants;
	}

	@Override
	public List<Restaurant> findAllByZip(long zipCode) throws ResourceNotFoundException {
		List<Restaurant> restaurants = repository.findAllByZipCode(zipCode);
		if (restaurants == null || restaurants.isEmpty()) {
			throw new ResourceNotFoundException("Resource(s) by :"+ zipCode+" not found");
		}
		return restaurants;
	}

	@Override
	public List<Restaurant> findAllByCity(String city) throws ResourceNotFoundException {
		List<Restaurant> restaurants = repository.findAllByCity(city);
		if (restaurants == null || restaurants.isEmpty()) {
			throw new ResourceNotFoundException("Resource(s) by :"+ city+" not found");
		}
		return restaurants;
	}

	@Override
	public List<Restaurant> findAllByState(String state) throws ResourceNotFoundException {
		List<Restaurant> restaurants = repository.findAllByState(state);
		if (restaurants == null || restaurants.isEmpty()) {
			throw new ResourceNotFoundException("Resource(s) by :"+ state+" not found");
		}
		return restaurants;
		
	}

	@Override
	public List<Restaurant> findAllByRating(int rating) throws ResourceNotFoundException {
		List<Restaurant> restaurants = repository.findAllByRating(rating);
		if (restaurants == null || restaurants.isEmpty()) {
			throw new ResourceNotFoundException("Resource(s) by :"+ rating+" not found");
		}
		return restaurants;
	}
	
	@Override
	public List<Restaurant> findAllByType(String type) throws ResourceNotFoundException {
		List<Restaurant> restaurants = repository.findAllByType(type);
		if (restaurants == null || restaurants.isEmpty()) {
			throw new ResourceNotFoundException("Resource(s) by :"+ type+" not found");
		}
		return restaurants;
	}
	
	@Override
	public List<Restaurant> findAllByTypeIsNot(String type) throws ResourceNotFoundException {
		List<Restaurant> restaurants = repository.findAllByTypeIsNot(type);
		if (restaurants == null || restaurants.isEmpty()) {
			throw new ResourceNotFoundException("Resource(s) by :"+ type+" not found");
		}
		return restaurants;
	}

	@Override
	public List<Restaurant> findAll() throws ResourceNotFoundException {
		Sort sortName=Sort.by("name").ascending();
		List<Restaurant> restaurants = repository.findAll(sortName);
		if (restaurants == null || restaurants.isEmpty()) {
			throw new ResourceNotFoundException("Resource(s) not found");
		}
		return restaurants;
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) {
		return repository.save(restaurant);

	}

	@Override
	public void deleteRestaurant(String id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteCollection() {
		repository.deleteAll();
	}

	@Override
	public List<Restaurant> findAllByZipAndType(long zipCode) throws ResourceNotFoundException {
		return null;
	}

	@Override
	public List<Restaurant> filterByFields(Request request) throws ResourceNotFoundException {
		Predicate fieldFilter=QueryBuilder.multiFieldRestaurantSearchFilter(request.getParameters());
		Iterable<Restaurant> restaurants=this.repository.findAll(fieldFilter);
		if (restaurants == null ) {
			throw new ResourceNotFoundException("Resource(s) not found");
		}
		return (List<Restaurant>) restaurants;
	}

	@Override
	public List<Restaurant> fullTextSearch(Request request) throws ResourceNotFoundException {
		String searchText=(String) request.getParameters().get("searchText");
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchText);
		return repository.findAllBy(criteria);
	}

	

}
