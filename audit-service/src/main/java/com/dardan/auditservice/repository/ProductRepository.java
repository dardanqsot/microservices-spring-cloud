package com.dardan.auditservice.repository;

import com.mitocode.microservices.commonmodels.model.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {
}
