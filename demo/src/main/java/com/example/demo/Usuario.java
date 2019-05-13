package com.example.demo;

public class Usuario {

	public final String id;
	public final String name;
	public final String city;
	public final String country;
	
	public Usuario(String id, String name, String city, String country) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
	}
	
	@Override
	public String toString() {
		return ("Usuario id: "+this.id+ " |name: " + this.name + " |city: " + this.city 
				+ " |country: " + this.country);
	}

	
	
	
	
	
	
}
