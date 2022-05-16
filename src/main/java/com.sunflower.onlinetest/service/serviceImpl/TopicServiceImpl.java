package com.sunflower.onlinetest.service.serviceImpl;

import com.sunflower.onlinetest.dao.TopicDAO;
import com.sunflower.onlinetest.dao.UserDAO;
import com.sunflower.onlinetest.entity.TopicEntity;
import com.sunflower.onlinetest.entity.UserEntity;
import com.sunflower.onlinetest.rest.request.TopicRequest;
import com.sunflower.onlinetest.service.TopicService;
import com.sunflower.onlinetest.service.mapper.TopicEntityMapper;
import com.sunflower.onlinetest.service.response.TopicDTO;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

public class TopicServiceImpl implements TopicService {

    @Inject
    private TopicDAO topicDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private TopicEntityMapper topicEntityMapper;

    @Override
    public List<TopicDTO> getAll() {
        return topicEntityMapper.entityToDTOs(topicDAO.getAllTopics());
    }

    @Override
    public TopicDTO create(TopicRequest topicRequest) {
        UserEntity owner = userDAO.findById(topicRequest.getOwnerId());
        if (Objects.isNull(owner)) {
            throw new RuntimeException("Could not find owner with id = " + topicRequest.getOwnerId());
        }
        TopicEntity topicEntity = topicEntityMapper.requestToEntity(topicRequest);
        topicEntity.setOwner(owner);
        return topicEntityMapper.entityToDTO(topicDAO.save(topicEntity));
    }

    @Override
    public TopicDTO getByCode(String code) {
        Integer id = Integer.valueOf(CustomBase64.decode(code));
        return topicEntityMapper.entityToDTO(topicDAO.findById(id));
    }
}
