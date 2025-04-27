package com.dardan.microservices.productqueryservice.service.repository;

import com.dardan.commonmodels.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, String> {
}
