package com.sunflower.onlinetest.dao;

import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.request.LoginRequest;

import java.util.Optional;

public class UserDAO extends GenericDAO<UserEntity> {

    public Optional<UserEntity> findByEmail(String email) {
        try {
            return Optional.of(createTypeQuery("from UserEntity user where user.email = :email")
                    .setParameter("email", email)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
