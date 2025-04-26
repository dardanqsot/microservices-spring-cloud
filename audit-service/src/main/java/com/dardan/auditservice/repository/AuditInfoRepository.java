package com.dardan.auditservice.repository;

import com.dardan.commonmodels.entity.AuditInfo;
import org.springframework.data.repository.CrudRepository;

public interface AuditInfoRepository extends CrudRepository<AuditInfo, String> {
}