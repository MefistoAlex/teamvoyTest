package com.teamvoy.task.model;

import com.teamvoy.task.entity.UserEntity;

public class User {
    private Long id;
    private String userName;

    //convert UserEntity to model
    public static User toModel(UserEntity entity) {
        return new User(entity.getId(), entity.getUsername());
    }

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
