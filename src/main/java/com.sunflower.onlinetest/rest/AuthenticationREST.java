package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.request.LoginRequest;
import com.sunflower.onlinetest.rest.request.SignupRequest;
import com.sunflower.onlinetest.service.AuthenticationService;
import com.sunflower.onlinetest.service.mapper.UserEntityMapper;
import com.sunflower.onlinetest.service.response.LoginSignupResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
@RequestScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationREST {

    public static final String EMAIL_OR_PASSWORD_IS_INVALID = "Email or password is invalid!";
    public static final String COULD_NOT_SIGNUP_PLEASE_TRY_AGAIN = "Could not signup! Please try again!";

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private UserEntityMapper userEntityMapper;

    @POST
    @Path("login")
    public Response login(LoginRequest loginRequest) {
        try {
            UserEntity userEntity = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword()).get();
            LoginSignupResponse loginSignupResponse = userEntityMapper.toLoginSignupResponse(userEntity);
            return Response.ok(loginSignupResponse).build();
        } catch (Exception e) {
            return Response.ok(EMAIL_OR_PASSWORD_IS_INVALID).build();
        }
    }

    @POST
    @Path("signup")
    public Response signup(SignupRequest signupRequest) {
        try {
            UserEntity userEntity = authenticationService.signup(signupRequest.getFullName(), signupRequest.getEmail(), signupRequest.getPassword()).get();
            LoginSignupResponse loginSignupResponse = userEntityMapper.toLoginSignupResponse(userEntity);
            return Response.ok(loginSignupResponse).build();
        } catch (Exception e) {
            return Response.ok(COULD_NOT_SIGNUP_PLEASE_TRY_AGAIN).build();
        }
    }
}
