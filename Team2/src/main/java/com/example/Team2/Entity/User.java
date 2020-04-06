package com.example.Team2.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class User {
	
	@ApiModelProperty(notes= "Name of User")
	String name;
	@ApiModelProperty(notes = "Email of User")
	@Id
	String email;
	@ApiModelProperty(notes = "City of User")
	String city;
	
	@ApiModelProperty(notes = "State of User")
	String state;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String email, String city, String state) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", city=" + city + ", state=" + state + "]";
	}
	
}
