package com.berk.rabbitmq.producer;

import com.berk.rabbitmq.model.SaveAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    /**
     * Belli bir bilginin rabbitmq uzerınden ıletılme ıslemı
     */
    private final RabbitTemplate rabbitTemplate;

    public void convertAndSend(SaveAuthModel model){
        //config packageından geldı. ıcındekı bılgıler.Bu model nesnesını bu yolu ızleyerek gonder.
        rabbitTemplate.convertAndSend("direct-exchange-auth","save-auth-key",model);
    }
}
