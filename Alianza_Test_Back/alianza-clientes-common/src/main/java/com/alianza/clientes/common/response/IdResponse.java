package com.alianza.clientes.common.response;

import org.springframework.http.HttpStatus;

public class IdResponse extends ObjectResponse<Long> {

    public IdResponse() {
        super();
    }

    public IdResponse(Long id) {
        super(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase(), id);
    }

}
