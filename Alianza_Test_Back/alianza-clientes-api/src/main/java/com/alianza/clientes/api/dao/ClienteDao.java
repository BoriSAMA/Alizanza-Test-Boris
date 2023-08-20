package com.alianza.clientes.api.dao;

import org.springframework.stereotype.Repository;

import com.alianza.clientes.api.entity.ClienteEntity;
import com.alianza.clientes.core.filter.ClienteFilter;
import com.alianza.clientes.core.filter.Page;

@Repository
public interface ClienteDao extends CustomDao<ClienteEntity, Long> {

    Page<ClienteEntity> getPage(ClienteFilter filter);

}
