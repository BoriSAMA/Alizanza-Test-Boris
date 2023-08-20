package com.alianza.clientes.core.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class IdObject<ID> {

    public interface Attributes {
        String ID = "id";
    }

    /**
     * @return Retorna el ID actual del objeto.
     */
    public abstract ID getId();

    /**
     * @param id El nuevo ID del objeto.
     */
    public abstract void setId(ID id);

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        IdObject<ID> other = (IdObject<ID>) obj;
        if (getId() == null) {
            return other.getId() == null;
        }

        return getId().equals(other.getId());
    }

    /**
     * @return {@code true} si el objeto es nuevo, es decir no tiene ID asignado.
     */
    @JsonIgnore
    public boolean isNewObject() {
        return getId() == null;
    }

}
