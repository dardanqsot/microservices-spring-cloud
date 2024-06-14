package com.dardan.microservices.userservice.model.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Data ==> Getter, Setter, ToString, Hash, Value
@Getter
@Setter
@Builder
@Document("user")
@JsonPropertyOrder({"name", "lastname", "roles"})
public class UserEntity {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String lastname;

    @NonNull
    private String username;
    private String email;
    private String password;
    private String[] roles;

}
