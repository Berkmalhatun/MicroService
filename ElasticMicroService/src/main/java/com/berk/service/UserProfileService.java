package com.berk.service;

import com.berk.dto.request.AddUserRequestDto;
import com.berk.mapper.IUserProfileMapper;
import com.berk.repository.IUserProfileRepository;
import com.berk.repository.entity.UserProfile;
import com.berk.utility.ServiceManager;
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
    }
