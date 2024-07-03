package com.dardan.microservices.authenticationserveroauth2.model.entity.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
public class UserDTO {

    private String id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String[] roles;
    private Collection<GrantedAuthority> grantedAuthorities;

}
