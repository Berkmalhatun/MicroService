package com.berk.mapper;

import com.berk.dto.request.AddUserRequestDto;
import com.berk.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {
    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    @Mapping(target = "userprofileid",source = "id") //adduserdakı ID userprofiledaki userprofileid ile eşleştir.Yani dto dan ona aktar.
    UserProfile toProfile(final AddUserRequestDto dto);
    //targer Sol taraftakı olmalı , source finaldan sonrakı olmalı
}
