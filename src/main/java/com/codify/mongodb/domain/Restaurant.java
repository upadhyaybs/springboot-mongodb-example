package com.codify.mongodb.domain;

import java.util.ArrayList;
import java.util.Collection;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.codify.mongodb.constant.RestaurantType;
import com.querydsl.core.annotations.QueryEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author upadhyaybs
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@QueryEntity
@Document(collection = "restaurants")
public class Restaurant {
	
	@Id
	private String id;
	@TextIndexed
	private String name;
	@Indexed
	private RestaurantType type;
	private Collection<RestaurantReview> reviews=new ArrayList<>();
	private Collection<Address> addresses=new ArrayList<>();
	
	@DBRef
	private CommunicationChannel communicationChannel;
	
}
