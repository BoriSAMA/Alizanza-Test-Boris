package com.alianza.clientes.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.alianza.clientes.api.entity.ClienteEntity;
import com.alianza.clientes.core.domain.Cliente;

@Component
public class ClienteToClienteEntityConverter implements Converter<Cliente, ClienteEntity> {

    @Override
    public ClienteEntity convert(Cliente source) {
        ClienteEntity entity = new ClienteEntity();
        
        entity.setSharedKey(source.getSharedKey());
        entity.setName(source.getName());
        entity.setPhone(source.getPhone());
        entity.setEmail(source.getEmail());
        entity.setStartDate(source.getStartDate());
        entity.setEndDate(source.getEndDate());

        return entity;
    }

}
