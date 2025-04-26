package com.dardan.commonmodels.entity;

import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
public class GenericEntity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 129348931L;

    private T t;

    public GenericEntity(T obj) {
        this.t = obj;
    }

    public T getT() {
        return this.t;
    }

}
