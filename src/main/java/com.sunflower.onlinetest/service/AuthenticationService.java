package com.sunflower.onlinetest.service;

import com.sunflower.onlinetest.entity.UserEntity;

import java.util.Optional;

public interface AuthenticationService {

    Optional<UserEntity> login(String email, String password);

    Optional<UserEntity> signup(String fullName, String email, String password);
}
