package com.alianza.clientes.common.exception;

public class ValidationError {

    private String field;
    private String message;

    public ValidationError() {
        super();
    }

    public ValidationError(String message) {
        this(null, message);
    }

    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
