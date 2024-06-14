package com.dardan.microservices.userservice.repository;
import com.dardan.microservices.userservice.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<UserEntity, String>, PagingAndSortingRepository<UserEntity, String> {

//    @RestResource(exported = false)
//    void deleteById(String id);

    @RestResource(path = "correo")
    List<UserEntity> getAllByEmail(String email);


}
