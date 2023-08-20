package com.alianza.clientes.api.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;

import com.alianza.clientes.api.dao.CustomDao;
import com.alianza.clientes.api.entity.Entity;
import com.alianza.clientes.api.service.ConverterService;
import com.alianza.clientes.api.service.Service;
import com.alianza.clientes.core.domain.DomainBean;

/**
 * Implementacion base para todos los servicios de los proyectos, provee la
 * implementacion basica de los metodos CRUD.
 *
 * @param <D>  El tipo de objeto del dominio.
 * @param <E>  El tipo de objeto de la entity.
 * @param <ID> El tipo de la ID de la entity y del dominio
 * @param <DA> El tipo del DAO que hara la extraccion de datos
 */
public abstract class ServiceImpl<D extends DomainBean<ID>, E extends Entity<ID>, ID extends Serializable, DA extends CustomDao<E, ID>>
        implements Service<D, ID> {

    @Autowired
    private ConverterService converterService;

    private Class<E> entityClass;

    @SuppressWarnings(value = "unchecked")
    public ServiceImpl() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    @Override
    public ID insert(D domainBean) {
        E entity = converterService.convertTo(domainBean, entityClass);
        getDao().save(entity);
        return entity.getId();
    }

    /**
     * @return Retorna la instancia especifica del DAO que manipula la entidad.
     */
    protected abstract DA getDao();

    protected ConverterService getConverterService() {
        return converterService;
    }

}
