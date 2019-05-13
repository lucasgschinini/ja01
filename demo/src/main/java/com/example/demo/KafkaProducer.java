package com.example.demo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
	private final KafkaTemplate<Object, Usuario> kafkaTemplate;
	
	KafkaProducer(KafkaTemplate<Object, Usuario> kafkaTemplate){
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void send(Usuario message) {
		this.kafkaTemplate.send("dracarys",message);
		System.out.println("Sent sample message [ " + message + "]");
	}
}
