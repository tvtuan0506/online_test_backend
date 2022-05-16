package com.sunflower.onlinetest.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseObjectWithJWT {
    private String message;
    private Object data;
    private Object jwt;
}
