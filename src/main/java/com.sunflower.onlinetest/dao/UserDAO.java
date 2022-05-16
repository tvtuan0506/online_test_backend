package com.sunflower.onlinetest.dao;

import com.sunflower.onlinetest.entity.UserEntity;

import java.util.Optional;

public class UserDAO extends GenericDAO<UserEntity> {

    public Optional<UserEntity> findByEmail(String email) {
//        UserEntity userEntity = createTypeQuery("from UserEntity user where user.email = :email")
//                .setParameter("email", email)
//                .getSingleResult();
//        if (Objects.nonNull(userEntity)) {
//            return Optional.of(userEntity);
//        }
        try {
            return Optional.of(createTypeQuery("from UserEntity user where user.email = :email")
                    .setParameter("email", email)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
