package org.basak.twitterdemo.config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basak.twitterdemo.model.entity.User;
import org.basak.twitterdemo.model.entity.UserRole;
import org.basak.twitterdemo.service.UserRoleService;
import org.basak.twitterdemo.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetails implements UserDetailsService {
    private final UserService userService;
    private final UserRoleService userRoleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findById2(userId);
        if(userOptional.isEmpty()) return null;
        User user = userOptional.get();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		/*grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
		grantedAuthorities.add(new SimpleGrantedAuthority("VIP"));*/

        List<UserRole> userRoles = userRoleService.findByUserId(userId);
        userRoles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole())));
        log.warn(grantedAuthorities.toString());
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(grantedAuthorities)
                .build();
    }
}