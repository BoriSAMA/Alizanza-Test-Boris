package com.alianza.clientes.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.alianza.clientes.common.exception.StackTrace;
import com.alianza.clientes.common.util.Constants;

import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ErrorResponse extends BaseResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.FORMAT_DATE_TIME)
    private LocalDateTime createdDate;

    private String debugMessage;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(@NotNull HttpStatus status, String message) {
        this(status.value(), message);
    }

    public ErrorResponse(int statusCode, String message) {
        this(statusCode, message, null);
    }

    public ErrorResponse(@NotNull HttpStatus status, Throwable ex) {
        this(status.value(), ex);
    }

    public ErrorResponse(int statusCode, Throwable ex) {
        this(statusCode, ex.getLocalizedMessage(), ex);
    }

    public ErrorResponse(HttpStatus status, String message, Throwable ex) {
        this(status.value(), message, ex);
    }

    public ErrorResponse(int statusCode, String message, Throwable ex) {
        super(false, statusCode, message);
        this.createdDate = LocalDateTime.now();
        this.debugMessage = StackTrace.getStackTrace(ex);
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

}
