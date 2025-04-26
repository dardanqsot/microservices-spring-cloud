package com.dardan.commonmodels.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.Serial;
import java.io.Serializable;

//@Data //Getter, Setter, ToString, Hash, Value
@Setter
@Getter
@Builder
@Valid
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UserEntity  extends GenericEntity<UserEntity> implements Serializable {

    @Serial
    private static final long serialVersionUID = 129348938L;

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
