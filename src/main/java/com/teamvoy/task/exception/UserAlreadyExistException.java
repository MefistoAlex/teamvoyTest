package com.teamvoy.task.exception;

//exception when user with same username already exist
public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
