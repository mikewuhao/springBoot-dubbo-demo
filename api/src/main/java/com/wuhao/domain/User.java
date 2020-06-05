package com.wuhao.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * pojo类
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private int age;

    private String address;

    public Long getId() {
        return id;
    }

}
