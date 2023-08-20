package com.alianza.clientes.common.exception;

import com.alianza.clientes.common.response.ErrorResponse;

public class ErrorResponseException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public ErrorResponseException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
