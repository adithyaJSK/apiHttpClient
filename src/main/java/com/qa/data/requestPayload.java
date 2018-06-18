package com.qa.data;

//pojo class

public class requestPayload {

	// {
	// "name" : "adithya",
	// "city" : "bengaluru"
	// }

	String name;
	String city;

	public requestPayload() {

	}

	public requestPayload(String name, String city) {
		this.name = name;
		this.city = city;
	}

	// getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
