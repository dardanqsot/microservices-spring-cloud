package com.dardan.commonmodels.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
@Document(collection = "product")
//@Entity
//@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends GenericEntity<ProductEntity> implements Serializable {

    @Serial
    private static final long serialVersionUID = 129348938L;

    @Id
    private String productId;
    private String productName;
    private Long price;
    private Integer stock;
    private String productType;


}
