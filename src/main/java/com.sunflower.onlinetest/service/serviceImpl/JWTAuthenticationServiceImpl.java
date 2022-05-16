package com.sunflower.onlinetest.service.serviceImpl;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.sunflower.onlinetest.config.AppConfig;
import com.sunflower.onlinetest.rest.request.LoginRequest;
import com.sunflower.onlinetest.service.AuthenticationService;
import com.sunflower.onlinetest.service.JWTAuthenticationService;
import com.sunflower.onlinetest.service.exception.ErrorCode;
import com.sunflower.onlinetest.service.exception.UnauthorizedException;
import com.sunflower.onlinetest.service.response.JWT;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Log4j2
public class JWTAuthenticationServiceImpl implements JWTAuthenticationService {

    @Inject
    AuthenticationService authenticationService;

    public JWT createAuthorizedToken(LoginRequest loginRequest) {
//        log.info("start creating token for username: {}", accountRequest.getUsername());
        authenticationService.checkValidUser(loginRequest);
        String token = null;
        String secretKey = AppConfig.SECRET_KEY;
        String issuer = AppConfig.ISSUER;
        int timeToLive = AppConfig.TIME_TO_LIVE;
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            token = com.auth0.jwt.JWT.create()
                    .withIssuer(issuer)
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim("email", loginRequest.getEmail())
                    .withExpiresAt(this.setTokenTimeToLive(timeToLive))
                    .sign(algorithm);
        } catch (IllegalArgumentException illegalArgumentException) {
//           log.error("has error when create token ", illegalArgumentException);
            throw illegalArgumentException;
        }
//        log.info("create successfully");
        return new JWT(token, timeToLive);
    }

    public void checkAuthorizedToken(String authorization) {
//            log.info("start checking authorization: {}", authorization);
        if (Objects.isNull(authorization)) {
//                log.error("authorization token string is null");
            throw new UnauthorizedException(ErrorCode.INVALID_INPUT_REQUEST);
        }
        String[] authParts = authorization.split("\\s+");
        if (authParts.length < 2 || !"Bearer".equals(authParts[0])) {
//                log.error("authorization parts is invalid: length < 2 or Bearer part could not found");
            throw new UnauthorizedException(ErrorCode.INVALID_INPUT_REQUEST);
        }
        String jwtToken = authParts[1];
        try {
            String secretKey = AppConfig.SECRET_KEY;
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
                    .withIssuer(AppConfig.ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(jwtToken);
        } catch (JWTVerificationException | IllegalArgumentException exception) {
//                log.error("has error when check authorized token ", exception);
            throw exception;
        }
//        log.info("checked successfully");
    }

    private Date setTokenTimeToLive(int timeToLive) {
        return new Date(System.currentTimeMillis() + timeToLive);
    }
}
