package com.alianza.clientes.api.service;

import java.io.Serializable;

import com.alianza.clientes.core.domain.DomainBean;

/**
 * Definicion base para los servicios que manupilan datos de un objeto del dominio especifico.
 *
 * @param <D>  El tipo del objeto del dominio
 * @param <ID> El tipo del ID de la entity.
 */
public interface Service<D extends DomainBean<ID>, ID extends Serializable> {

    /**
     * Inserta el domain recibido como parametro.
     *
     * @param domainBean El domain bean a guardar.
     * @return El id asignado al objeto.
     */
    ID insert(D domainBean);
}
