package org.basak.twitterdemo.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;



@AllArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(500,"SUNUCUDA BEKLENMEYEN HATA.",HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(400,"Girilen parametreler hatalıdır. Kontrol ediniz.",HttpStatus.BAD_REQUEST),
    JSON_CONVERT_ERROR(300,"Girilen parametreler hatalıdır. Json Dönüşüm Hatası.",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(4000, "Parametre hatası", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH_ERROR(4301, "Passwordler uyuşmuyor.", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME_OR_PASSWORD(4302,"Username veya Password hatalı", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4303,"Token geçersiz", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4001, "User bulunamadı", HttpStatus.NOT_FOUND),
    MUSTERI_ALREADY_EXISTS(4002, "Bu TCKN ile kayıtlı müşteri zaten mevcut", HttpStatus.BAD_REQUEST),
    INVALID_ACTIVATION_CODE(4015, "Aktivasyon kodu hatalı", HttpStatus.BAD_REQUEST),
    ACTIVATION_CODE_EXPIRED(4016, "Aktivasyon kodunun süresi dolmuş", HttpStatus.BAD_REQUEST),
    ALREADY_ACTIVE(4017, "Hesap zaten aktif", HttpStatus.BAD_REQUEST),;


    int code;
    String message;
    HttpStatus httpStatus;
}

