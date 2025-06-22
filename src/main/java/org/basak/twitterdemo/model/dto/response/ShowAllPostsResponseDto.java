package org.basak.twitterdemo.model.dto.response;

import java.time.LocalDateTime;

public record ShowAllPostsResponseDto(
        String content,
        String media,
        LocalDateTime publishedAt,
        Integer viewCount,
        Integer likeCount,
        Integer commentCount,
        String username,
        String avatar,
        String name
) {}
