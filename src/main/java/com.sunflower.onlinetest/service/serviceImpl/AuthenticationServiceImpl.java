package com.sunflower.onlinetest.service.serviceImpl;

import com.sunflower.onlinetest.dao.UserDAO;
import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.service.AuthenticationService;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private UserDAO userDAO;

    @Override
    public Optional<UserEntity> login(String email, String password) {
        UserEntity processingUser = userDAO.findByEmail(email);
        if (Objects.nonNull(processingUser) && processingUser.getPassword().equals(password)) {
            return Optional.of(processingUser);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> signup(String fullName, String email, String password) {
        UserEntity newUser = UserEntity.builder()
                .fullName(fullName)
                .email(email)
                .password(password)
                .build();
        userDAO.save(newUser);
        return Optional.of(newUser);
    }
}
