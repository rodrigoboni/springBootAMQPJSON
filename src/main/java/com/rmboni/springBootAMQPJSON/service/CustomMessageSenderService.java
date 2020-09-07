package com.rmboni.springBootAMQPJSON.service;

import com.rmboni.springBootAMQPJSON.config.RabbitConfig;
import com.rmboni.springBootAMQPJSON.domain.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Serviço para envio de mensagens
 *
 * Utiliza o rabbit template, injetado pelo spring
 */
@Service
public class CustomMessageSenderService {
	private static final Logger log = LoggerFactory.getLogger(CustomMessageSenderService.class);
	
	// helper class do spring-amqp
	private final RabbitTemplate rabbitTemplate;
	
	public CustomMessageSenderService(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Scheduled(fixedDelay = 3000L) // enviar mensagem a cada 3s, para fins didáticos
	public void sendMessage() {
		// cria a mensagem a partir da classe / bean da msg
		final var message = new CustomMessage("Hello!", new Random().nextInt(50), LocalDateTime.now(), false);
		
		log.info("Sending message: {}", message.toString());
		
		// converte o obj / bean e envia a msg para a exchange especifica, definido também a routing key como header
		// para que o rabbit distribua a msg para as queues inscritas na exchange, que atendam a routing key
		rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, message);
	}
}
