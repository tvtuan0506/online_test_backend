package com.sunflower.onlinetest.service;

import com.sunflower.onlinetest.rest.request.TopicRequest;
import com.sunflower.onlinetest.service.response.TopicDTO;

import java.util.List;

public interface TopicService {

    List<TopicDTO> getAll();

    TopicDTO create(TopicRequest topicRequest);

    TopicDTO getByCode(String code);
}
