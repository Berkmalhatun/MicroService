package com.berk.service;

import com.berk.dto.request.AddUserRequestDto;
import com.berk.dto.request.BaseRequestDto;
import com.berk.mapper.IUserProfileMapper;
import com.berk.repository.IUserProfileRepository;
import com.berk.repository.entity.UserProfile;
import com.berk.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void saveDto(AddUserRequestDto dto) {
        /**
         * Eger userid daha onceden kayıt edılmıs ıse kaydetme ıslemı yapma.
         */
            if (!repository.existsByUserprofileid(dto.getId()))
                save(IUserProfileMapper.INSTANCE.toProfile(dto));
        }

    /**
     * Sayfalama ıslemlerınde bellı bılgıler olmadan ıslem yapmak mumkun olmayacaktır.
     * -> Lıste gelmelı
     * -> Total kayıt mıktarı
     * -> Hangı sayfadayım
     * -> Kac sayfa var ?
     * -> Sonrakı sayfa var mı ?
     * -> Son sayfada mıyım ?
     */
    public Page<UserProfile> findAll(BaseRequestDto dto){
        /**
         * Sıralama ve sayfalama ıcın bıze nesneler ve ayarlamalar gereklı
         */
        Pageable pageable=null;
        Sort sort=null;
        /**
         * Eger kısı sıralamak ıstedıgı alanı yazmamıs ıse sıralama yapmak ıstemıyordur.
         */
        if (dto.getSortParameter()!=null){
            String direction = dto.getDirection()==null ? "ASC" : dto.getDirection();// belirtmedıysem sıralamayı kucukten buyuge olsun , eger belırttıysem direction olsun
            sort = Sort.by(Sort.Direction.fromString(direction),dto.getSortParameter());
        }
        /**
         * 1. durum -> sıralama yapmak ıster ve sayfalama yapmak ıster
         * 2. durum -> sıralama istemıyor ve sayfalama yapmak ıstıyor.
         * 3. durum -> sıralama ıstemıyor ve sayfalama ıstegınde bulunmuyor.
         */
        Integer pageSize = dto.getPageSize() == null ? 10 :
                           dto.getPageSize() < 1 ? 10 : dto.getPageSize();
        if (sort!=null && dto.getCurrentPage()!=null){
            pageable = PageRequest.of(dto.getCurrentPage(),pageSize,sort);
        }else if (sort==null && dto.getCurrentPage()!=null){
            pageable = PageRequest.of(dto.getCurrentPage(),pageSize);
        }else {
            pageable = PageRequest.of(0,pageSize);
        }
        return repository.findAll(pageable); // findall 2 sekılde calısır parametrelı olan pageable olandır.
    }

    public Optional<UserProfile> findByAuthId(Long authId){ //jwtuserdeatils icin yazıldı
        return repository.findOptionalByAuthid(authId);
    }
}
