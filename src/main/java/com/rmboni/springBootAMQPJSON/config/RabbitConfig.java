package com.rmboni.springBootAMQPJSON.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

/**
 * Parâmetros da exchange / filas rabbit a serem utilizadas
 */
public class RabbitConfig {
	public static final String EXCHANGE = "appExchange";
	public static final String GENERIC_QUEUE = "appGenericQueue";
	public static final String SPECIFIC_QUEUE = "appSpecificQueue";
	public static final String ROUTING_KEY = "messages.key";
	
	@Bean
	public TopicExchange appExchange() { // declara tópico
		return new TopicExchange(EXCHANGE);
	}
	
	@Bean
	public Queue appQueueGeneric() { // declara fila
		return new Queue(GENERIC_QUEUE);
	}
	
	@Bean
	public Queue appQueueSpecific() {
		return new Queue(SPECIFIC_QUEUE);
	}
	
	@Bean
	public Binding declareBindingGeneric() {
		// inscreve a fila genérica na exchange do tipo topic, indicando routing key
		// exchange do tipo topico encaminham cópias das mensagens as filas inscritas, podendo opcionalmente definir routing key (senão envia a todas as filas)
		return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with(ROUTING_KEY);
	}
	
	@Bean
	public Binding declareBindingSpecific() {
		// inscreve a outra fila (specific) no mesmo exchange do tipo topic
		return BindingBuilder.bind(appQueueSpecific()).to(appExchange()).with(ROUTING_KEY);
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		// o objetivo deste método é sobrescrever o rabbit template default com um configurado para tratar json via jackson
		final var rabbitTemplate = new RabbitTemplate(connectionFactory);
		// configura rabbit template (p/ envio de msg) para utilizar o jackson na serialização / deserialização das mensagens
		// com este recurso não é necessário implementar a interface RabbitListenerConfigurer
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
