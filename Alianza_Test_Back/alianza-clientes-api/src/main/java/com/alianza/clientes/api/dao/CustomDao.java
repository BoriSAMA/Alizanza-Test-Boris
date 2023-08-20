package com.alianza.clientes.api.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.alianza.clientes.api.entity.Entity;

@Repository
public interface CustomDao<E extends Entity<ID>, ID extends Serializable> {

    void save(E entity);

}
