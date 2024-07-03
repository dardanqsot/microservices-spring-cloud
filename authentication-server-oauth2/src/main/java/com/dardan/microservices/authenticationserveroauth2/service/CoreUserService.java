package com.dardan.microservices.authenticationserveroauth2.service;

import com.dardan.microservices.authenticationserveroauth2.model.entity.UserEntity;
import com.dardan.microservices.authenticationserveroauth2.model.entity.core.UserCore;
import com.dardan.microservices.authenticationserveroauth2.model.entity.dto.UserDTO;
import com.dardan.microservices.authenticationserveroauth2.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDTO findUserByUsername(String username)  {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            log.info(("Username: " + username + " no se encuentra en la BD"));
        }

        return UserDTO.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .lastname(userEntity.getLastname())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDTO userDTO = findUserByUsername(username);

        if (userDTO == null) {
            throw new UsernameNotFoundException("Username: " + username + " no se encuentra en la BD");
        }

        List<GrantedAuthority> grantedAuthorities = Arrays.stream(userDTO.getRoles())
                .map(SimpleGrantedAuthority::new)
                .peek(simpleGrantedAuthority -> log.info("[CoreUserService] - [loadUserByUsername] - Rol: " +
                        simpleGrantedAuthority.getAuthority()))
                .collect(Collectors.toList());
        userDTO.setGrantedAuthorities(grantedAuthorities);
        return new UserCore(userDTO);
    }
}
