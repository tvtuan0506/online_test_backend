package com.sunflower.onlinetest.rest.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TopicRequest {
    @NotNull
    private String name;

    private String description;

    private int ownerId;
}
