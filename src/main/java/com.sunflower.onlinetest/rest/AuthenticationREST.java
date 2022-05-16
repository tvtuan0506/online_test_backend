package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.request.LoginRequest;
import com.sunflower.onlinetest.rest.request.SignupRequest;
import com.sunflower.onlinetest.service.AuthenticationService;
import com.sunflower.onlinetest.service.JWTAuthenticationService;
import com.sunflower.onlinetest.service.mapper.UserEntityMapper;
import com.sunflower.onlinetest.service.response.JWT;
import com.sunflower.onlinetest.service.response.ResponseObjectWithJWT;
import com.sunflower.onlinetest.service.response.UserDTO;

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

    public static final String SUCCESSFULLY = "successfully";

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private UserEntityMapper userEntityMapper;

    @Inject
    private JWTAuthenticationService jwtAuthenticationService;

    @POST
    @Path("login")
    public Response login(LoginRequest loginRequest) {
        try {
            UserEntity userEntity = authenticationService.login(loginRequest);
            UserDTO userDTO = userEntityMapper.toUserResponse(userEntity);
            JWT authorizedToken = jwtAuthenticationService.createAuthorizedToken(loginRequest);
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message(SUCCESSFULLY)
                    .data(userDTO)
                    .jwt(authorizedToken)
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(responseObject)
                    .build();
        }
    }

    @POST
    @Path("signup")
    public Response signup(SignupRequest signupRequest) {
        try {
            UserEntity userEntity = authenticationService.signup(signupRequest);
            UserDTO userDTO = userEntityMapper.toUserResponse(userEntity);
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message(SUCCESSFULLY)
                    .data(userDTO)
                    .jwt("jwt is not available")
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObjectWithJWT responseObject = ResponseObjectWithJWT.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(responseObject)
                    .build();
        }
    }
}
