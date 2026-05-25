package com.zcklab.api.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor //injection
public class AuthenticationService {

    public String authenticate(){
        return "token";
    }
}
