package com.example.demo1_pbl4.utils;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}
