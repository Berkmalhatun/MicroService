package com.berk.rabbitmq.consumer;

import com.berk.rabbitmq.model.SaveAuthModel;
import com.berk.repository.entity.UserProfile;
import com.berk.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;

    //Burdakı metotlar bır sey donmez. Calısır ve ısını bıtırır.
    @RabbitListener(queues = "queue-auth") // burada hangı kuyrugu dınleyecegımızı belırtırız.Bu da RabbitConfig ıcınde
    public void createUserFromHandleQueue(SaveAuthModel model){
        System.out.println("Gelen Data...: "+ model.getUsername());
        // Bunu aynı zamanda elasticsearch de kaydetmek bu kodlarla olmaz. cunku bırı jpa bırı nosql. o yuzden yorum satırı aldım.
  /*  userProfileService.save(UserProfile.builder().authid(model.getAuthid())
            .username(model.getUsername()).email(model.getEmail()).build());*/
        userProfileService.saveRabbit(model);
    }
}
