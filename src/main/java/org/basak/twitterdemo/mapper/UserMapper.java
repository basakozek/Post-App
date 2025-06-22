package org.basak.twitterdemo.mapper;

import org.basak.twitterdemo.model.dto.request.UserRegisterRequestDto;
import org.basak.twitterdemo.model.dto.response.UserRegisterResponseDto;
import org.basak.twitterdemo.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//Güncelleme işleminde null değerlerin değişmemesi için:
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserRegisterResponseDto toResponseDto(User user);
    User toEntity(UserRegisterRequestDto dto);

//	void updateMusteriFromDto(MusteriUpdateRequestDto dto, @MappingTarget Musteri musteri);
}
