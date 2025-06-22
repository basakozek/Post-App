package org.basak.twitterdemo.mapper;
import org.basak.twitterdemo.model.dto.request.PostAtmaRequestDto;
import org.basak.twitterdemo.model.dto.response.PostAtmaResponseDto;
import org.basak.twitterdemo.model.dto.response.ShowAllPostsResponseDto;
import org.basak.twitterdemo.model.entity.Post;
import org.basak.twitterdemo.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//Güncelleme işleminde null değerlerin değişmemesi için:
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    PostAtmaResponseDto toResponseDto(Post post);
    Post toEntity(PostAtmaRequestDto dto);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.avatar", target = "avatar")
    ShowAllPostsResponseDto toShowAllPostsDto(Post post, User user);

}
