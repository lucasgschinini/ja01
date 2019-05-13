package com.example.demo;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

@Component
public class FakerProducer {
	
	Faker fk;
	
	public Usuario producir() {
		Usuario user = new Usuario(
				UUID.randomUUID().toString(),
				fk.name().fullName(),
				fk.address().city(),
				fk.address().country());
		
		return user;
		
	}

	public FakerProducer() {
		this.fk = new Faker();
	}

}
