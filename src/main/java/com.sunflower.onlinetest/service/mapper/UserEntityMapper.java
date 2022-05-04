package com.sunflower.onlinetest.service.mapper;

import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper {
    UserDTO toUserResponse(UserEntity userEntity);
}
