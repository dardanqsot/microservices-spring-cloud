package com.dardan.microservices.authenticationserveroauth2.config;

import com.dardan.microservices.authenticationserveroauth2.model.entity.dto.UserDTO;
import com.dardan.microservices.authenticationserveroauth2.service.CoreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class CustomTokenEnhancer implements TokenEnhancer {

    private final CoreUserService coreUserService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        UserDTO userDTO = coreUserService.findUserByUsername(oAuth2Authentication.getName());
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("name", userDTO.getName());
        customClaims.put("lastname", userDTO.getLastname());
        customClaims.put("email", userDTO.getEmail());
//        customClaims.put("sub", userDTO.getUsername());
//        customClaims.put("roles", userDTO.getRoles());


        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(oAuth2AccessToken);
        customAccessToken.setAdditionalInformation(customClaims);

        return customAccessToken;

    }
}
