package com.codify.mongodb.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request {
	
	
	@JsonIgnore
	private Map<String,Object> parameters=new HashMap<>();
	
	@JsonAnyGetter
	public Map<String, Object> getParameters() {
	return this.parameters;
	}

	@JsonAnySetter
	public void setParameters(String name, Object value) {
	this.parameters.put(name, value);
	}

}
