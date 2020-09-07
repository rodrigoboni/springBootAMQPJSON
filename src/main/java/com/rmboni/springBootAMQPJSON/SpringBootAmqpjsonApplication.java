package com.rmboni.springBootAMQPJSON;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// não é necessário @EnableRabbit, já que o spring boot vai detectar pelas dependências e habilitar o que for necessário
@SpringBootApplication
@EnableScheduling
public class SpringBootAmqpjsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAmqpjsonApplication.class, args);
	}

}
