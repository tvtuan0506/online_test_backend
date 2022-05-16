package com.sunflower.onlinetest.service;


import com.sunflower.onlinetest.rest.request.LoginRequest;
import com.sunflower.onlinetest.service.response.JWT;

public interface JWTAuthenticationService {

     JWT createAuthorizedToken(LoginRequest loginRequest);

     void checkAuthorizedToken(String authorization);
}
