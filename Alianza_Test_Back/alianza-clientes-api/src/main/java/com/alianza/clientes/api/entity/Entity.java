package com.alianza.clientes.api.entity;

import com.alianza.clientes.core.util.IdObject;

import java.io.Serializable;


public abstract class Entity<ID extends Serializable> extends IdObject<ID> implements Serializable {

    private static final long serialVersionUID = -7716070975924354714L;

    /**
     * Atributos de la entity usados para realizar consultas con criteria.
     */
    public interface Attributes {
        String ID = "id";
    }

}
