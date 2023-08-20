package com.alianza.clientes.api.service.impl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alianza.clientes.api.dao.ClienteDao;
import com.alianza.clientes.api.entity.ClienteEntity;
import com.alianza.clientes.api.service.ClienteService;
import com.alianza.clientes.core.domain.Cliente;
import com.alianza.clientes.core.filter.ClienteFilter;
import com.alianza.clientes.core.filter.Page;

@Service
public class ClienteServiceImpl extends ServiceImpl<Cliente, ClienteEntity, Long, ClienteDao> implements ClienteService {

    private static final Logger LOGGER = Logger.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteDao clienteDao;

    @Override
    protected ClienteDao getDao() {
        return clienteDao;
    }

    @Override
    public Page<Cliente> getClientes(ClienteFilter clienteFilter) {

        LOGGER.info("Getting clients by filter");
        Page<ClienteEntity> page = getDao().getPage(Optional.ofNullable(clienteFilter).orElse(new ClienteFilter()));
        
        return new Page<>(page.getTotalElements(), getConverterService().convertTo(page.getContent(), Cliente.class));
    }

}
