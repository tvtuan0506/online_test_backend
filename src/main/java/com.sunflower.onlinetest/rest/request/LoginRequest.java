package com.sunflower.onlinetest.rest.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class LoginRequest {

    @Email
    private String email;

//    @Pattern(regexp = "")
    private String password;
}
