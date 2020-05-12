package com.codify.mongodb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codify.mongodb.constant.APIStatusCode;
import com.codify.mongodb.domain.Request;
import com.codify.mongodb.domain.Restaurant;
import com.codify.mongodb.exception.ResourceNotFoundException;
import com.codify.mongodb.service.IRestaurantService;
import com.codify.mongodb.util.Response;

import lombok.RequiredArgsConstructor;

/**
 * @author upadhyaybs
 *
 */
@RestController
@RequestMapping("/restaurant/api")
@RequiredArgsConstructor
public class RestaurantController {

	private final IRestaurantService service;

	@PostMapping
	public Response<Restaurant> insertRestaurant(@RequestBody Restaurant restaurant) {
		return Response.<Restaurant>builder().data(service.insertRestaurant(restaurant))
				.status(APIStatusCode.RESOURCE_CREATED).build();
	}

	@PutMapping
	public Response<Restaurant> update(@RequestBody Restaurant restaurant) {
		return Response.<Restaurant>builder().data(service.updateRestaurant(restaurant))
				.status(APIStatusCode.RESOURCE_UPDATED).build();
	}
	
	@DeleteMapping("/{id}")
	public Response<String> deleteById(@PathVariable String id) {
		service.deleteRestaurant(id);
		return Response.<String>builder().status(APIStatusCode.RESOURCE_DELETED).build();
	}

	@GetMapping("/all")
	public Response<List<Restaurant>> findAll() throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder().data(service.findAll()).status(APIStatusCode.RESOURCE_FOUND)
				.build();
	}

	@GetMapping("/id/{id}")
	public Response<Restaurant> findById(@PathVariable String id) throws ResourceNotFoundException {
		return Response.<Restaurant>builder().data(service.findById(id)).status(APIStatusCode.RESOURCE_FOUND).build();
	}

	@GetMapping("/zipCode/{zipCode}")
	public Response<List<Restaurant>> findByZip(@PathVariable long zipCode) throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder().data(service.findAllByZip(zipCode))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}

	@GetMapping("/type/{type}")
	public Response<List<Restaurant>> findAllByType(@PathVariable String type) throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder().data(service.findAllByType(type))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}

	@GetMapping("/typeExcept/{type}")
	public Response<List<Restaurant>> findAllByOtherThanType(@PathVariable String type)
			throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder().data(service.findAllByTypeIsNot(type))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}

	@GetMapping("/state/{state}")
	public Response<List<Restaurant>> findByState(@PathVariable String state) throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder().data(service.findAllByState(state))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}

	@GetMapping("/rating/{rating}")
	public Response<List<Restaurant>> findByRating(@PathVariable int rating) throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder().data(service.findAllByRating(rating))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}
	
	@PostMapping("/name")
	public Response<List<Restaurant>> findByName(@RequestBody Request request)
			throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder().data(service.findAllByName((String) request.getParameters().get("name")))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}

	@PostMapping("/city")
	public Response<List<Restaurant>> findByCity(@RequestBody Request request)
			throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder()
				.data(service.findAllByCity(request.getParameters().get("city").toString()))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}

	@PostMapping("/filter")
	public Response<List<Restaurant>> filterByFields(@RequestBody Request request)
			throws ResourceNotFoundException { 
		return Response.<List<Restaurant>>builder().data(service.filterByFields(request))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}

	
	@PostMapping("/fullTextSearch")
	public Response<List<Restaurant>> fullTextSearch(@RequestBody Request request)
			throws ResourceNotFoundException {
		return Response.<List<Restaurant>>builder().data(service.fullTextSearch(request))
				.status(APIStatusCode.RESOURCE_FOUND).build();
	}
	

}
