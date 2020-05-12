/**
 * 
 */
package com.codify.mongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codify.mongodb.domain.AvgRatingModel;
import com.codify.mongodb.repository.IProjectionRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author upadhyaybs
 *
 */
@Service
@RequiredArgsConstructor
public class ProjectionServiceImpl implements IProjectionService {

	private final IProjectionRepository repository;
	
	@Override
	public List<AvgRatingModel> getAllRestuarants() {
		return repository.getAllRestuarants();
	}

}
