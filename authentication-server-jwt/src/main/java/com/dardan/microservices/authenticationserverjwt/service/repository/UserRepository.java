package com.dardan.microservices.authenticationserverjwt.service.repository;


import com.dardan.microservices.authenticationserverjwt.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

}
