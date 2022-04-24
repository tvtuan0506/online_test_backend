package com.sunflower.onlinetest.service.mapper;

import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.service.response.LoginSignupResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper {
    LoginSignupResponse toLoginSignupResponse(UserEntity userEntity);
}
