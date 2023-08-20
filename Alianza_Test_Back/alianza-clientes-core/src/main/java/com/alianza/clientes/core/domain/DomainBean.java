package com.alianza.clientes.core.domain;

import com.alianza.clientes.core.util.IdObject;

/**
 * Clase base para cualquier bean del dominio.
 *
 * @param <ID> El tipo de ID del objeto del dominio.
 */
public abstract class DomainBean<ID> extends IdObject<ID> {

    public interface Attributes extends IdObject.Attributes {

    }

}
