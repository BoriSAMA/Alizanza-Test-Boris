package com.alianza.clientes.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.alianza.clientes.api.entity.ClienteEntity;
import com.alianza.clientes.core.domain.Cliente;

@Component
public class ClienteEntityToClienteConverter implements Converter<ClienteEntity, Cliente> {

    @Override
    public Cliente convert(ClienteEntity entity) {
        Cliente bean = new Cliente();

        bean.setId(entity.getId());
        bean.setSharedKey(entity.getSharedKey());
        bean.setName(entity.getName());
        bean.setPhone(entity.getPhone());
        bean.setEmail(entity.getEmail());
        bean.setStartDate(entity.getStartDate());
        bean.setEndDate(entity.getEndDate());

        return bean;
    }

}
