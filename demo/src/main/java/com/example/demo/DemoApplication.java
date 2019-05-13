package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Component
	class AppStartupRunner implements ApplicationRunner{
	
		@Autowired
		FakerProducer fakerProducer;
		@Autowired
		KafkaProducer kafkaProducer;
		
		public void run (ApplicationArguments args) throws Exception{
			
			while (true) {
				kafkaProducer.send(fakerProducer.producir());
				Thread.sleep(1000);
			}
//			System.out.println(fakerProducer.producir());
		}
	}

}
