package com.alianza.clientes.common.response;

import org.springframework.http.HttpStatus;

public class IdStringResponse extends ObjectResponse<String> {

    public IdStringResponse() {
        super();
    }

    public IdStringResponse(String id) {
        super(HttpStatus.CREATED, id);
    }

}
