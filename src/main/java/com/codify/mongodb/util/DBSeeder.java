package com.codify.mongodb.util;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.codify.mongodb.constant.RestaurantType;
import com.codify.mongodb.domain.Address;
import com.codify.mongodb.domain.CommunicationChannel;
import com.codify.mongodb.domain.Restaurant;
import com.codify.mongodb.domain.RestaurantReview;
import com.codify.mongodb.service.IRestaurantService;

import lombok.RequiredArgsConstructor;

/**
 * @author upadhyaybs
 *
 */
@Service
@RequiredArgsConstructor
public class DBSeeder implements CommandLineRunner {

	private final IRestaurantService service;
	private final MongoTemplate template;

	@Override
	public void run(String... args) throws Exception {
		
		CommunicationChannel channel1=new CommunicationChannel( "email@pizzhut.com", "999-000-2020", "", "999-00-2020");
		CommunicationChannel channel2=new CommunicationChannel( "email@kaveri.com", "999-000-2021", "", "999-00-2021");
		CommunicationChannel channel3=new CommunicationChannel( "email@thaithumbz.com", "999-000-2022", "", "999-00-2022");
		CommunicationChannel channel4=new CommunicationChannel( "email@casamama.com", "999-000-2023", "", "999-00-2023");
		CommunicationChannel channel5=new CommunicationChannel( "email@tacos.com", "999-000-2024", "", "999-00-2024");
		CommunicationChannel channel6=new CommunicationChannel( "email@finemexican.com", "999-000-2025", "", "999-00-2025");
		
		template.dropCollection("communication_channel");
		
		template.insert(channel1);
		template.insert(channel2);
		template.insert(channel3);
		template.insert(channel4);
		template.insert(channel5);
		template.insert(channel6);

		Restaurant italian = new Restaurant("", "Pizza Hut", RestaurantType.ITALIAN,
				Arrays.asList(new RestaurantReview(4), new RestaurantReview(4)),
				Arrays.asList(new Address("1521 Preston Road", "Plano", "TX", 75093, "USA")),channel1);

		Restaurant indian = new Restaurant("", "Kaveri", RestaurantType.INDIAN,
				Arrays.asList(new RestaurantReview(4), new RestaurantReview(4)),
				Arrays.asList(new Address("1520 Preston Road", "Plano", "TX", 75093, "USA")),channel2);

				
		Restaurant thai = new Restaurant("", "Thai Thumbs", RestaurantType.THAI,
				Arrays.asList(new RestaurantReview(4), new RestaurantReview(4)),
				Arrays.asList(new Address("3610 Shire Blvd #112", "Richardson", "TX", 75082, "USA")),channel3);

		Restaurant mexican1 = new Restaurant("", "Casa Mama", RestaurantType.MEXICAN,
				Arrays.asList(new RestaurantReview(4), new RestaurantReview(4)),
				Arrays.asList(new Address("1445 Los Rios Blvd #301", "Plano", "TX", 75074, "USA")),channel4);
		
		
		Restaurant mexican2 = new Restaurant("", "Tacos El Guero", RestaurantType.MEXICAN,
				Arrays.asList(new RestaurantReview(4), new RestaurantReview(4)),
				Arrays.asList(new Address("4100 E Park Blvd", "Plano", "TX", 75074, "USA")),channel5);
		
		Restaurant mexican3 = new Restaurant("", "Cristina's Fine Mexican Restaurant", RestaurantType.MEXICAN,
				Arrays.asList(new RestaurantReview(4), new RestaurantReview(4)),
				Arrays.asList(new Address("109 W Farm to Market Rd 544 #101", "Murphy", "TX", 75094, "USA")),channel6);

		service.deleteCollection();
		
		Collection<Restaurant> restaurants = Arrays.asList(indian, italian,thai,mexican1,mexican2,mexican3);
		service.insertAll(restaurants);
	}

}
