package com.sunflower.onlinetest.service.mapper;

import com.sunflower.onlinetest.entity.TopicEntity;
import com.sunflower.onlinetest.rest.request.TopicRequest;
import com.sunflower.onlinetest.service.response.TopicDTO;
import com.sunflower.onlinetest.util.CustomBase64;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface TopicEntityMapper {

    @Mappings({
            @Mapping(target = "createdDate", source = "topicEntity.createdDate", dateFormat = "dd/MM/yyyy hh:mm:ss")
    })
    TopicDTO entityToDTO(TopicEntity topicEntity);
    @AfterMapping
    default void convertIdToCode(TopicEntity topicEntity, @MappingTarget TopicDTO topicDTO) {
        topicDTO.setCode(CustomBase64.encode(String.valueOf(topicEntity.getId())));
    }

    List<TopicDTO> entityToDTOs(List<TopicEntity> topics);

    TopicEntity requestToEntity(TopicRequest topicRequest);
}
