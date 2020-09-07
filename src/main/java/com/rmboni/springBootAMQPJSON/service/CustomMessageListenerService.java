package com.rmboni.springBootAMQPJSON.service;

import com.rmboni.springBootAMQPJSON.config.RabbitConfig;
import com.rmboni.springBootAMQPJSON.domain.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Serviço para recebimento de mensagens
 */
@Service
public class CustomMessageListenerService {
	private static final Logger log = LoggerFactory.getLogger(CustomMessageListenerService.class);
	
	@RabbitListener(queues = RabbitConfig.GENERIC_QUEUE) //define p/ que a aplicação se inscreva na fila indicada no parâmetro
	public void receiveMessage(final Message message) {
		// recebe mensagem genérica, utilizando a class amqp.core.Message - sem conversão implicita
		log.info("Received message as generic: {}", message.toString());
	}
	
	@RabbitListener(queues = RabbitConfig.SPECIFIC_QUEUE)
	public void receiveMessage(final CustomMessage customMessage) {
		// recebe mensagem com formato específico - COM conversão implícita
		log.info("Received message as specific class: {}", customMessage.toString());
	}
}
