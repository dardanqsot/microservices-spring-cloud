package com.dardan.commonmodels.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "audit")
public class AuditInfo extends GenericEntity<AuditInfo> implements Serializable {

    @Serial
    private static final long serialVersionUID = 129348939L;

    private Long currentTimestamp;
    private String appCallerName;
    private String opnNumber;
    private String message;
    private String statusCode;
    private String exception;
    private String exceptionDetails;


}
