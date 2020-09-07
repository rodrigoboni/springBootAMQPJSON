# SpringBoot AMQP JSON

POC para demonstrar o envio e recebimento de mensagens utilizando o Spring AMQP + Rabbit MQ

### Configuração
Executar o rabbit via docker:

```sh
docker-compose up -d
```

### Info
* [Rabbit console](http://localhost:15672)
* Se necessário (erro do spring ao tentar criar exchanges e filas) configurar manualmente a exchange + filas + bindings pelo console do rabbit
* Iniciar mais de uma instância, para simular producer e listener - comentar o producer ou listener p/ alternar

### Links
* [AMQP / RabbitMQ concepts](https://www.rabbitmq.com/tutorials/amqp-concepts.html)
* [Sending and receiving JSON messages with Spring Boot AMQP and RabbitMQ](https://thepracticaldeveloper.com/2016/10/23/produce-and-consume-json-messages-with-spring-boot-amqp/)
* [Outra POC relacionada a spring + rabbit](https://github.com/rodrigoboni/springbootAMQPRabbitPOC)
