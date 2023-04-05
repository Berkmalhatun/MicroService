package com.berk.mapper;

import com.berk.dto.request.AddUserRequestDto;
import com.berk.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-05T14:41:34+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IUserProfileMapperImpl implements IUserProfileMapper {

    @Override
    public UserProfile toProfile(AddUserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.userprofileid( dto.getId() );
        if ( dto.getId() != null ) {
            userProfile.id( String.valueOf( dto.getId() ) );
        }
        userProfile.authid( dto.getAuthid() );
        userProfile.username( dto.getUsername() );
        userProfile.email( dto.getEmail() );
        userProfile.ad( dto.getAd() );
        userProfile.adres( dto.getAdres() );
        userProfile.telefon( dto.getTelefon() );
        userProfile.avatar( dto.getAvatar() );

        return userProfile.build();
    }
}
