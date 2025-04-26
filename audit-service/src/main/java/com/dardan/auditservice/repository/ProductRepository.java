package com.dardan.auditservice.repository;

import com.dardan.commonmodels.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {
}
