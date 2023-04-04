package com.berk.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private String directExchangeAuth="direct-exchange-auth";
    private String queueAuth = "queue-auth";
    private String saveBindingKey = "save-auth-key";
//rabbitmq de ıslem yapılması ıcın exchange ve queue ye karar verılmesı lazım.Bunların baglantısı
    //bindingkey ıle yapılır

    @Bean //spring yonetsın dıye eklendı. ıhtıyac oldgunda kendısı ınject yapsın dıye.
    DirectExchange directExchangeAuth() {
return new DirectExchange(directExchangeAuth);
    }
    @Bean
    Queue queueAuth(){
    return new Queue(queueAuth);
    }

    @Bean
    public Binding bindingSaveDirectExchange(final Queue queueAuth , final  DirectExchange directExchangeAuth){ //bu method exchange ıle queue yı bırbırıne baglıcak
        return BindingBuilder.bind(queueAuth).to(directExchangeAuth).with(saveBindingKey);
        //bu yaptıgımız ıle rabbıtmq de queue kısmına bır kuyruk getırıyoruz. adı da queue-auth olcak
        // yani bindingkey uzerınden bırbırlerıne baglanacaklar.
    }

}
