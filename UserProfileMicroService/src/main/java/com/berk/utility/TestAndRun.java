package com.berk.utility;

import com.berk.manager.IElasticServiceManager;
import com.berk.repository.entity.UserProfile;
import com.berk.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component //uygulama ayaga kalkarken bundanda bı tane nesne yaratmasına yarıcak
@RequiredArgsConstructor
public class TestAndRun {

    private final UserProfileService userProfileService;
    private final IElasticServiceManager elasticServiceManager;

    @PostConstruct // her uygulama ayaga kalkarken  burayı 1kere calıstırcak
    public void init(){
        /**
         * Bu kısım kullanılacak ıse , zorunlu durumlar ıcın ısınız bıtınce bu kodu yorum satırına almak
         * dogru olacaktır.Calısması sıstemı etkılemeyen durumlarda thred ıcınde calıstırmak dogru
         * olacaktır.
         */
        //thread e almak ayrı bır threadde calıstırcagı ıcın uygulama acılısında gecıkmeye sebep vermez.
/*        new Thread(()->{
            run();
        });*/
        run();
    }
    public void run(){
        List<UserProfile> list = userProfileService.findAll();
        list.forEach(x->{
            elasticServiceManager.addUser(x);
        });
    }
}
