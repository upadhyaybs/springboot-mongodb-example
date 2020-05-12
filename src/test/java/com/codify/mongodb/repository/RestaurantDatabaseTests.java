/**
 * 
 */
package com.codify.mongodb.repository;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.test.context.ActiveProfiles;

import com.codify.mongodb.constant.RestaurantType;
import com.codify.mongodb.domain.Address;
import com.codify.mongodb.domain.CommunicationChannel;
import com.codify.mongodb.domain.Restaurant;
import com.codify.mongodb.domain.RestaurantReview;

/**
 * @author upadhyaybs
 *
 */
@ActiveProfiles("test")	
@DataMongoTest
@TestInstance(Lifecycle.PER_CLASS)
public class RestaurantDatabaseTests {
	
	@Autowired
	private IRestaurantRepository restaurantRepository;
	
	@Autowired
	private  MongoTemplate template;
	
	@BeforeAll
	public void setup() {
		
		CommunicationChannel channel1=new CommunicationChannel("email@pizzhut.com", "999-000-2020", "", "999-00-2020");
		CommunicationChannel channel2=new CommunicationChannel("email@kaveri.com", "999-000-2021", "", "999-00-2021");
		CommunicationChannel channel3=new CommunicationChannel( "email@thaithumbz.com", "999-000-2022", "", "999-00-2022");
		CommunicationChannel channel4=new CommunicationChannel( "email@casamama.com", "999-000-2023", "", "999-00-2023");
		CommunicationChannel channel5=new CommunicationChannel("email@tacos.com", "999-000-2024", "", "999-00-2024");
		CommunicationChannel channel6=new CommunicationChannel("email@finemexican.com", "999-000-2025", "", "999-00-2025");

		template.dropCollection("communication_channel");
		template.insertAll(Arrays.asList(channel1,channel2,channel3,channel4,channel5,channel6));
		
		restaurantRepository.deleteAll();
		
		Restaurant italian = new Restaurant("", "Pizza Hut", RestaurantType.ITALIAN,
				Arrays.asList(new RestaurantReview(4), new RestaurantReview(4)),
				Arrays.asList(new Address("1521 Preston Road", "Plano", "TX", 75093, "USA")),channel1);

		Restaurant indian = new Restaurant("", "Kaveri", RestaurantType.INDIAN,
				Arrays.asList(new RestaurantReview(4), new RestaurantReview(4)),
				Arrays.asList(new Address("1520 Preston Road", "Plano", "TX", 75093, "USA")),channel2);

				
		Restaurant thai = new Restaurant("", "Thai Thumbs", RestaurantType.THAI,
				Arrays.asList(new RestaurantReview(5), new RestaurantReview(5)),
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

		
		Collection<Restaurant> restaurants = Arrays.asList(indian, italian,thai,mexican1,mexican2,mexican3);
		restaurantRepository.insert(restaurants);
	}
	
	
	@Test
	public void test_findAllRestaurant() {
		List<Restaurant> restaurants=restaurantRepository.findAll();
		assertNotNull(restaurants);
		assertTrue(!restaurants.isEmpty());
		assertEquals(6, restaurants.size());
	}
	
	@Test
	public void test_findAllByNameContains_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByNameContains("Kav");
		assertNotNull(restaurants);
		assertTrue(!restaurants.isEmpty());
		assertEquals(1, restaurants.size());
	}
	
	@Test
	public void test_find_All_ByNameContains_No_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByNameContains("Kav1");
		assertNotNull(restaurants);
		assertTrue(restaurants.isEmpty());
		assertEquals(0, restaurants.size());
	}
	
	@Test
	public void test_findAllByType_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByType("INDIAN");
		assertNotNull(restaurants);
		assertTrue(!restaurants.isEmpty());
		assertEquals(1, restaurants.size());
	}
	
	@Test
	public void test_findAllByType_No_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByType("CHINESE");
		assertNotNull(restaurants);
		assertTrue(restaurants.isEmpty());
		assertEquals(0, restaurants.size());
	}
	
	@Test
	public void test_findAllByCity_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByCity("Plano");
		assertNotNull(restaurants);
		assertTrue(!restaurants.isEmpty());
		assertEquals(4, restaurants.size());
	}
	
	@Test
	public void test_findAllByCity_No_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByCity("Frisco");
		assertNotNull(restaurants);
		assertTrue(restaurants.isEmpty());
		assertEquals(0, restaurants.size());
	}
	
	
	@Test
	public void test_findAllByState_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByState("TX");
		assertNotNull(restaurants);
		assertTrue(!restaurants.isEmpty());
		assertEquals(6, restaurants.size());
	}
	
	@Test
	public void test_findAllByState_No_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByState("WA");
		assertNotNull(restaurants);
		assertTrue(restaurants.isEmpty());
		assertEquals(0, restaurants.size());
	}
	
	@Test
	public void test_findAllByZip_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByZipCode(75093);
		assertNotNull(restaurants);
		assertTrue(!restaurants.isEmpty());
		assertEquals(2, restaurants.size());
	}
	
	@Test
	public void test_findAllByZip_No_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByZipCode(75091);
		assertNotNull(restaurants);
		assertTrue(restaurants.isEmpty());
		assertEquals(0, restaurants.size());
	}
	
	@Test
	public void test_findAllByRating_Match() {
		List<Restaurant> restaurants=restaurantRepository.findAllByRating(5);
		assertNotNull(restaurants);
		assertTrue(!restaurants.isEmpty());
		assertEquals(1, restaurants.size());
	}
	
	@Test
	public void test_findAllBy_Full_Text_Search_Match() {
		TextCriteria criteria=TextCriteria.forDefaultLanguage().matching("Mama");
		List<Restaurant> restaurants=restaurantRepository.findAllBy(criteria);
		assertNotNull(restaurants);
		assertTrue(!restaurants.isEmpty());
		assertEquals(1, restaurants.size());
	}
	
	
	@Test
	public void test_findAllBy_Full_Text_Search_No_Match() {
		TextCriteria criteria=TextCriteria.forDefaultLanguage().matching("Kav");
		List<Restaurant> restaurants=restaurantRepository.findAllBy(criteria);
		assertNotNull(restaurants);
		assertTrue(restaurants.isEmpty());
		assertEquals(0, restaurants.size());
	}

}
