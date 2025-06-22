package org.basak.twitterdemo.service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.basak.twitterdemo.exception.ErrorType;
import org.basak.twitterdemo.exception.TwitterDemoException;
import org.basak.twitterdemo.mapper.UserMapper;
import org.basak.twitterdemo.model.dto.request.UserDoLoginRequestDto;
import org.basak.twitterdemo.model.dto.request.UserRegisterRequestDto;
import org.basak.twitterdemo.model.entity.User;
import org.basak.twitterdemo.model.enums.AccountState;
import org.basak.twitterdemo.repository.UserRepository;
import org.basak.twitterdemo.util.JwtManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtManager jwtManager;
    private final MailService mailService;
   /* public void register(UserRegisterRequestDto dto) {
        User user= UserMapper.INSTANCE.toEntity(dto);
        user.setAccountState(AccountState.PASSIVE);
        userRepository.save(user);
    }

    */
   @Transactional
   public void register(UserRegisterRequestDto dto) {
       User user = UserMapper.INSTANCE.toEntity(dto);
       user.setAccountState(AccountState.PASSIVE);
       // 5 haneli kod
       String activationCode = String.valueOf((int)(Math.random() * 90000) + 10000);
       user.setActivationCode(activationCode);
       user.setActivationCodeExpireDate(LocalDateTime.now().plusMinutes(15));
       userRepository.save(user);
       mailService.sendActivationCode(user.getEmail(), activationCode);
   }
    public void activateAccount(Long userId, String code) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TwitterDemoException(ErrorType.USER_NOT_FOUND));

        if (user.getAccountState() == AccountState.ACTIVE)
            throw new TwitterDemoException(ErrorType.ALREADY_ACTIVE);

        if (!user.getActivationCode().equals(code))
            throw new TwitterDemoException(ErrorType.INVALID_ACTIVATION_CODE);

        if (user.getActivationCodeExpireDate().isBefore(LocalDateTime.now()))
            throw new TwitterDemoException(ErrorType.ACTIVATION_CODE_EXPIRED);
        user.setAccountState(AccountState.ACTIVE);
        user.setActivationCode(null);
        user.setActivationCodeExpireDate(null);
        user.setIsVerified(true);
        userRepository.save(user);
    }

    public String doLogin(UserDoLoginRequestDto dto) {
        Optional<User> userOptional = userRepository.findOptionalByUsernameAndPassword(dto.username(),dto.password());
        if(userOptional.isEmpty()) {
            throw new TwitterDemoException(ErrorType.INVALID_USERNAME_OR_PASSWORD);
        }
        return jwtManager.generateToken(userOptional.get().getId());
    }

    public User getProfile(String token) {
        Optional<Long> userId= jwtManager.validateToken(token);
        if(userId.isEmpty()) {
            throw new TwitterDemoException(ErrorType.INVALID_TOKEN);
        }
        Optional<User> optionalUser = userRepository.findById(userId.get());
        if(optionalUser.isEmpty()) {
            throw new TwitterDemoException(ErrorType.USER_NOT_FOUND);
        }
        return optionalUser.get();
    }
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new TwitterDemoException(ErrorType.USER_NOT_FOUND));
    }
    public Optional<User> findById2(Long id) {
        return userRepository.findById(id);
    }
}
