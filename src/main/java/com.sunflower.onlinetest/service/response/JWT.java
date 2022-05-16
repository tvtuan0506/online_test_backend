package com.sunflower.onlinetest.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWT {

    private String token;

    private int timeToLive;
}
