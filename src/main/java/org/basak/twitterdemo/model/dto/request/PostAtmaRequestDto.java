package org.basak.twitterdemo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostAtmaRequestDto(
        @NotBlank(message = "Content boş geçilemez")
        @Size(max=300,message = "Post contenti max=300 karakter olmalı")
        String content,
        String media,
        @NotBlank
        String token
) {
}
