package com.codify.mongodb.domain;


import org.springframework.data.mongodb.core.index.TextIndexed;

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
public class Address {
	@TextIndexed
	private String street;
	private String city;
	private String state;
	private long zipCode;
	private String country;

}
