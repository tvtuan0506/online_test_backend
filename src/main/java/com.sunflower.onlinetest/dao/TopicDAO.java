package com.sunflower.onlinetest.dao;

import com.sunflower.onlinetest.entity.TopicEntity;
import com.sunflower.onlinetest.rest.request.TopicRequest;

import java.util.List;

public class TopicDAO extends GenericDAO<TopicEntity> {

    public List<TopicEntity> getAllTopics() {
        return createTypeQuery("from TopicEntity").getResultList();
    }
}
