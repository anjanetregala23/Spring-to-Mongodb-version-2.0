package com.mkyong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee_collections")
public class Employee {

	@Id
	private String id;

	String name;

	String age;	
	//getter, setter, toString, Constructors

}