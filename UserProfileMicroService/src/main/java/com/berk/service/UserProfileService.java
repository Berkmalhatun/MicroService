package com.berk.service;

import com.berk.dto.request.UserProfileSaveRequestDto;
import com.berk.manager.IElasticServiceManager;
import com.berk.mapper.IUserProfileMapper;
import com.berk.rabbitmq.model.SaveAuthModel;
import com.berk.repository.IUserProfileRepository;
import com.berk.repository.entity.UserProfile;
import com.berk.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository repository;
    private final IElasticServiceManager elasticServiceManager;

    public UserProfileService(IUserProfileRepository repository, IElasticServiceManager elasticServiceManager) {
        super(repository);
        this.repository = repository;
        this.elasticServiceManager = elasticServiceManager;
    }

    public Boolean saveDto(UserProfileSaveRequestDto dto) {
    save(IUserProfileMapper.INSTANCE.toUserProfile(dto));
        return true;
    }

    public void saveRabbit(SaveAuthModel model){
        UserProfile profile = IUserProfileMapper.INSTANCE.toUserProfile(model);
        save(profile);
        elasticServiceManager.addUser(profile);
    }

    /**
     * Bu uzun suren br ıslemı sımule etmek ıcın thread kullanılarak bekletılmıs bır methoddur.
     * Bu method, Kağıt kelımesı ıcın her seferınde aynı sonucu mu uretır?
     * -> berk => BERK
     * @param name
     * @return
     */
    @Cacheable(value = "getUpperName")
    public String getUpper(String name){
        try{
            Thread.sleep(3000L);
        }catch (Exception e){

        }
        return name.toUpperCase();
    }
    @CacheEvict(value = "getUpperName", allEntries = true)
    public void clearCache(){
        System.out.println("Tum kayıtları temizledik.");
    }
}
