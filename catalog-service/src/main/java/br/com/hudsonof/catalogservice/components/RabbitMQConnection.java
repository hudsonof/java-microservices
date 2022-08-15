package br.com.hudsonof.catalogservice.components;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hudsonof.constants.RabbitMQConstants;

@Component
public class RabbitMQConnection {
    private static final String DIRECT_EXCHANGE_NAME = "amq.direct";

    @Autowired
    private AmqpAdmin amqpAdmin;

    private Queue createQueue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange createDirectExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    private Binding createBinding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), exchange.getName(), null);
    }

    @PostConstruct
    private void bindQueue() {
        Queue catalogQueue = createQueue(RabbitMQConstants.CATALOG);

        DirectExchange exchange = createDirectExchange();

        Binding binding = createBinding(catalogQueue, exchange);

        amqpAdmin.declareQueue(catalogQueue);
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareBinding(binding);
    }
}
