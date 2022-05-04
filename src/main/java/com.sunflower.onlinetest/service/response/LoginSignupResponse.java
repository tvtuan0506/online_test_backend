package com.sunflower.onlinetest.service.response;

import com.sunflower.onlinetest.service.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginSignupResponse {
    private int status;
    private String message;
    private String jwt;
    private UserDTO userDTO;
}
