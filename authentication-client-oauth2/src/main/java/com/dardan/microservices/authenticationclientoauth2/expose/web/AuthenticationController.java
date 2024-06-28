package com.dardan.microservices.authenticationclientoauth2.expose.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @GetMapping("/authenticate")
    public String login(@AuthenticationPrincipal OAuth2User user) {
        String result = "error";
        if (user != null) {
            String username = user.getAttribute("name");
            result = "Logueo exitoso. Bienvenido: " + username;
        }
        return result;
    }

    @GetMapping("/")
    public String publicResource() {
        return "Soy un recurso publico";
    }

}