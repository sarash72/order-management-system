package org.example.order.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.ORDER_EXCHANGE,
                "order.created",
                event
        );
    }
}