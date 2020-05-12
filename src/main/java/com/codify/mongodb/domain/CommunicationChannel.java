package com.codify.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@QueryEntity
@Document(collection = "communication_channel")
@NoArgsConstructor
public class CommunicationChannel {
	
	@Id
	private String id;
	private String emailAddress;
	private String mobile;
	private String homePhone;
	private String officePhone;
	
	public CommunicationChannel(String email,String mobile,String homePhone,String officePhone) {
		this.setEmailAddress(email);
		this.setMobile(mobile);
		this.setHomePhone(homePhone);
		this.setOfficePhone(officePhone);
	}

}
