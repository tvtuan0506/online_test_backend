package com.sunflower.onlinetest.entity;

import com.sunflower.onlinetest.dao.iEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Entity
public class User implements iEntity {

    @Id
    private Integer id;

    @NotNull
    private String name;
}
