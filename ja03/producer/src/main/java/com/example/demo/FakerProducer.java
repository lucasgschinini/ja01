package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class FakerProducer {
    
    Faker fk;

    public FakerProducer() {
        this.fk = new Faker();
    }
    
    public Usuario producir() {
        return new Usuario(
                java.util.UUID.randomUUID().toString(),
                fk.name().fullName(),
                fk.address().city(),
                fk.address().country()
        );
    }
}
