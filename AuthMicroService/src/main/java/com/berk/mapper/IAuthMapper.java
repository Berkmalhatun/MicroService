package com.berk.mapper;

import com.berk.dto.request.RegisterRequestDto;
import com.berk.dto.request.UserProfileSaveRequestDto;
import com.berk.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);

    /**
     * degısken ısımlerı bırbırınden farklı oldugu ıcın bu ıkısını bırbırıyle eslestır dedık.
     */
    @Mapping(target = "authid", source = "id")
    UserProfileSaveRequestDto fromAuth(final Auth auth);
}
