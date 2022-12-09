package com.example.demo1_pbl4.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class B5EncodePassword {
    public static String encodePass(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
