package com.alianza.clientes.common.exception;

import javax.ws.rs.NotFoundException;

public class NotFoundObjectException extends NotFoundException {

    private Object objectId;

    public NotFoundObjectException(Object objectId) {
        this.objectId = objectId;
    }

    public Object getObjectId() {
        return objectId;
    }

}
