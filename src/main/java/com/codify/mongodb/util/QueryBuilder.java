package com.codify.mongodb.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.codify.mongodb.domain.QRestaurant;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * @author upadhyaybs
 *
 */
public final class QueryBuilder {

	private QueryBuilder() {}

	/**
	 * 
	 * @param parameters
	 * @return
	 */
	public static Predicate multiFieldRestaurantSearchFilter(Map<String, Object> parameters) {
		QRestaurant query = new QRestaurant("multiFieldRestaurantSearchFilter");
		Predicate filter = null;

		Map<String, BooleanExpression> criteriaMap = new HashMap<>();
		appendNameCriteria(query, parameters, criteriaMap);
		appendAddressCriteria(query, parameters, criteriaMap);
		appendReviewCriteria(query, parameters, criteriaMap);

		if (criteriaMap.isEmpty()) {
			throw new IllegalArgumentException("Not enough filter criteria specified");
		}

		BooleanExpression filtereExpression = null;
		Iterator<String> iterator = criteriaMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			BooleanExpression expression = criteriaMap.get(key);
			if (expression != null && filtereExpression == null) {
				filtereExpression = expression;
				continue;
			}
			if (filtereExpression != null) {
				filtereExpression = filtereExpression.and(expression);
			}
		}
		filter = filtereExpression;
		return filter;
	}

	/**
	 * 
	 * @param query
	 * @param parameters
	 * @param criteriaMap
	 */
	private static void appendNameCriteria(QRestaurant query, Map<String, Object> parameters,Map<String, BooleanExpression> criteriaMap) {
		BooleanExpression nameFilter = null;
		if (parameters.containsKey("name")) {
			String name = (String) parameters.get("name");
			nameFilter = query.name.contains(name);
		}
		if (nameFilter != null) {
			criteriaMap.put("name", nameFilter);
		}
	}

	/**
	 * 
	 * @param query
	 * @param parameters
	 * @param criteriaMap
	 */
	private static void appendAddressCriteria(QRestaurant query, Map<String, Object> parameters,Map<String, BooleanExpression> criteriaMap) {
		BooleanExpression filter = null;
		BooleanExpression zipFilter = null;
		BooleanExpression cityFilter = null;
		BooleanExpression stateFilter = null;

		if (parameters.containsKey("zipCode")) {
			Integer zipCode = (Integer) parameters.get("zipCode");
			zipFilter = query.addresses.any().zipCode.eq(Long.valueOf(zipCode));
			filter = zipFilter;
		}
		if (parameters.containsKey("street")) {
			String street = (String) parameters.get("street");
			BooleanExpression streetFilter = query.addresses.any().street.eq(street);
			if (zipFilter != null) {
				filter = zipFilter.and(streetFilter);
			}else {
				filter=streetFilter;
			}
		}
		if (parameters.containsKey("city")) {
			String city = (String) parameters.get("city");
			cityFilter = query.addresses.any().city.eq(city);
			if (filter != null) {
				filter = filter.and(cityFilter);
			}else {
				filter = cityFilter;
			}
		}
		if (parameters.containsKey("state")) {
			String state = (String) parameters.get("state");
			stateFilter = query.addresses.any().state.eq(state);
			if (filter != null) {
				filter = filter.and(stateFilter);
			}else {
				filter =stateFilter;
			}
		}
		if (filter != null) {
			criteriaMap.put("address", filter);
		}
	}

	/**
	 * 
	 * @param query
	 * @param parameters
	 * @param criteriaMap
	 */
	private static void appendReviewCriteria(QRestaurant query, Map<String, Object> parameters,Map<String, BooleanExpression> criteriaMap) {
		BooleanExpression reviewFilter = null;
		if (parameters.containsKey("rating")) {
			Integer rating = (Integer) parameters.get("rating");
			reviewFilter = query.reviews.any().rating.eq(rating);
		}

		if (reviewFilter != null) {
			criteriaMap.put("review", reviewFilter);
		}
	}
}
