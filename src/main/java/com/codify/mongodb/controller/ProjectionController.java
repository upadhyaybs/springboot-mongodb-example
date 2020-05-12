/**
 * 
 */
package com.codify.mongodb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codify.mongodb.constant.APIStatusCode;
import com.codify.mongodb.domain.AvgRatingModel;
import com.codify.mongodb.service.IProjectionService;
import com.codify.mongodb.util.Response;

import lombok.RequiredArgsConstructor;

/**
 * @author upadhyaybs
 *
 */
@RestController
@RequestMapping("/restaurant/api")
@RequiredArgsConstructor
public class ProjectionController {

	private final IProjectionService projectionService;

	@GetMapping("/projection/all")
	public Response<List<AvgRatingModel>> getAllRestaurants() {
		return Response.<List<AvgRatingModel>>builder().data(projectionService.getAllRestuarants())
				.status(APIStatusCode.RESOURCE_FOUND).build();

	}

}
