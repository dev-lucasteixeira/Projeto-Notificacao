package com.lucasteixeira.notificacao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication(scanBasePackages = "com.lucasteixeira.notificacao")
public class NotificacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificacaoApplication.class, args);
	}

}
