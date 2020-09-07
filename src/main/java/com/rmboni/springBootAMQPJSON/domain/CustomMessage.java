package com.rmboni.springBootAMQPJSON.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Bean / POJO para exemplificar mensagem a ser enviada
 */
public class CustomMessage implements Serializable {
	private final String text;
	private final int priority;
	private final LocalDateTime timestamp;
	private final boolean secret;
	
	// jsonProprerty é uma das alternatias p/ mapear atributos da classe x payload json
	// o correto mapeamento das propriedades json x atributo da class é o segredo para a conversão implicita no envio ou recebimento das mensagens
	public CustomMessage(@JsonProperty("text") final String text,
	                     @JsonProperty("priority") final int priority,
	                     @JsonProperty("timestamp") final LocalDateTime timestamp,
	                     @JsonProperty("secret") final boolean secret) {
		this.text = text;
		this.priority = priority;
		this.timestamp = timestamp;
		this.secret = secret;
	}
	
	public String getText() {
		return text;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public boolean isSecret() {
		return secret;
	}
	
	@Override
	public String toString() {
		return "CustomMessage{" +
				"text='" + text + '\'' +
				", priority=" + priority +
				", timestamp=" + timestamp +
				", secret=" + secret +
				'}';
	}
}
