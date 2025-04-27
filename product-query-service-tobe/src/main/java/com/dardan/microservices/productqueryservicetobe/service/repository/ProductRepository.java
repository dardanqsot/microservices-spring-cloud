package com.dardan.microservices.productqueryservicetobe.service.repository;

import com.mitocode.microservices.commonmodels.model.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, String> {
}
