package org.basak.twitterdemo.model.dto.request;
import jakarta.validation.constraints.*;


public record UserRegisterRequestDto(
        @NotBlank(message = "Username boş geçilemez")
        @Size(min=3,max=30,message = "Username min=3 max=30 karakter olmalı")
        String username,
        @NotBlank
        @Pattern(regexp = "^.*(?=.{8,64})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*.]).*$",
                message = "Şifre en az 8 karakter, en fazla 64 karakter, 1 Büyük harf 1 " +
                        "Küçük harf, 1 Rakam ve 1 Özel karakter olmalıdır.")
        String password,
        @NotBlank
        String rePassword,
        @Email
        @NotBlank
        String email

) {
}
