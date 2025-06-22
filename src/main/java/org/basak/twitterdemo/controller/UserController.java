package org.basak.twitterdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.basak.twitterdemo.constant.EndPoints;
import org.basak.twitterdemo.exception.ErrorType;
import org.basak.twitterdemo.exception.TwitterDemoException;
import org.basak.twitterdemo.model.dto.request.UserDoLoginRequestDto;
import org.basak.twitterdemo.model.dto.request.UserRegisterRequestDto;
import org.basak.twitterdemo.model.dto.response.BaseResponse;
import org.basak.twitterdemo.model.entity.User;
import org.basak.twitterdemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(EndPoints.USER)
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    @PostMapping(EndPoints.REGISTER)
    public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody @Valid UserRegisterRequestDto dto) {
        if(!dto.rePassword().equals(dto.password())) {
            throw new TwitterDemoException(ErrorType.PASSWORD_MISMATCH_ERROR);
        }
        userService.register(dto);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .code(200)
                .data(true)
                .success(true)
                .message("Kayıt başarılı")
                .build());
    }
    @PostMapping("/activate")
    public ResponseEntity<BaseResponse<Boolean>> activateAccount(@RequestParam Long userId,
                                                                 @RequestParam String code) {
        userService.activateAccount(userId, code);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder()
                .success(true)
                .message("Aktivasyon başarılı")
                .data(true)
                .code(200)
                .build());
    }

    @PostMapping(EndPoints.LOGIN)
    public ResponseEntity<BaseResponse<String>> doLogin(@RequestBody @Valid UserDoLoginRequestDto dto){
        String token=userService.doLogin(dto);
        return ResponseEntity.ok(BaseResponse.<String>builder()
                .code(200)
                .data(token)
                .success(true)
                .message("Giriş başarılı")
                .build());
    }
    @GetMapping(EndPoints.GETPROFILE)
    public ResponseEntity<BaseResponse<User>> getProfile(@RequestParam String token){
        return ResponseEntity.ok(BaseResponse.<User>builder()
                .code(200)
                .data(userService.getProfile(token))
                .success(true)
                .message("Profil getirme başarılı")
                .build());
    }
}
