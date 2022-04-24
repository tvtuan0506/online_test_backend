package com.sunflower.onlinetest.rest.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    private String fullName;

    @Email
    private String email;

    //    @Pattern(regexp = ".*}")
    private String password;
}
