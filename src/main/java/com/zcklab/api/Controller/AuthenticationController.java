package com.zcklab.api.Controller;

import com.zcklab.api.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor //injection
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public String authenticate() {
        return authenticationService.authenticate();
    }
}
