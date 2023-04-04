package com.berk.service;

import com.berk.dto.request.DoLoginRequestDto;
import com.berk.dto.request.RegisterRequestDto;
import com.berk.exception.AuthServiceException;
import com.berk.exception.EErrorType;
import com.berk.manager.IUserProfileManager;
import com.berk.mapper.IAuthMapper;
import com.berk.rabbitmq.model.SaveAuthModel;
import com.berk.rabbitmq.producer.CreateUserProducer;
import com.berk.repository.IAuthRepository;
import com.berk.repository.entity.Auth;
import com.berk.utility.JwtTokenManager;
import com.berk.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager tokenManager;
    private final IUserProfileManager iUserProfileManager;

    private final CreateUserProducer createUserProducer;

    public AuthService(IAuthRepository repository, JwtTokenManager tokenManager, IUserProfileManager iUserProfileManager, CreateUserProducer createUserProducer) {
        super(repository);
        this.repository = repository;
        this.tokenManager = tokenManager;
        this.iUserProfileManager = iUserProfileManager;
        this.createUserProducer = createUserProducer;
    }

    public Auth register(RegisterRequestDto dto){
        if(repository.isUsername(dto.getUsername()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_USERNAME);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        /**
         * Repo -> repository.save(auth) -> bu bana kaydettıgı entıty ı doner
         * Servi-> save(auth) bana kaydettıgı entity doner
         * direkt -> auth bır sekılde kayıt edılen entıty nın bılgılerı ıslenır ve bunu doner.
         */
                save(auth);
       //  iUserProfileManager.save(IAuthMapper.INSTANCE.fromAuth(auth)); bu rabbitmqsuz kayıt gondermedır
        createUserProducer.convertAndSend(SaveAuthModel.builder().authid(auth.getId())
                .email(auth.getEmail()).username(auth.getUsername()).build());
        return auth;
    }
   public Optional<Auth> findOptionalByUsernameAndPassword(String username, String password){
        return repository.findOptionalByUsernameAndPassword(username,password);
    }

    /**
     * Kullanıcıyı dogrulayacak ve kullanıcının sıstem ıcınde hareket edebılmesı ıcın ona ozel bır
     * kımlık verecek.
     * Token -> JWT token
     */
    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(EErrorType.LOGIN_ERROR_USERNAME_PASSWORD);
            return tokenManager.createToken(auth.get().getId()).get();
    }
    public List<Auth> findAll(String token){
        Optional<Long> id =tokenManager.getByIdFromToken(token);
        if (id.isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);

        if( findById(id.get()).isEmpty())
            throw new AuthServiceException(EErrorType.INVALID_TOKEN);
        return findAll();
    }
}
