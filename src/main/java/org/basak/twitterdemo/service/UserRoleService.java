package org.basak.twitterdemo.service;

import lombok.RequiredArgsConstructor;
import org.basak.twitterdemo.model.entity.UserRole;
import org.basak.twitterdemo.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    public List<UserRole> findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }
}