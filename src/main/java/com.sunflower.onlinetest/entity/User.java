package com.sunflower.onlinetest.entity;

import com.sunflower.onlinetest.dao.iEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class User implements iEntity {

    @Id
    private Integer id;

    @NotNull
    private String name;
}
