package com.alianza.clientes.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.alianza.clientes.common.response.ErrorResponse;
import com.alianza.clientes.common.util.UtilInputStream;
import com.alianza.clientes.common.util.UtilJson;

import java.io.IOException;

/**
 * Manejador de errores generico para web service clients.
 */
public class ClientResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String bodyJson = UtilInputStream.getString(response.getBody());
        ErrorResponse error = extractErrorResponse(bodyJson);
        String message = getErrorMessage(error, bodyJson);

        if (HttpStatus.UNAUTHORIZED.equals(response.getStatusCode())) {
            throw APIExceptions.noAutorizado(message);
        }

        if (HttpStatus.NOT_FOUND.equals(response.getStatusCode())) {
            throw APIExceptions.objetoNoEncontrado(message);
        }

        if (HttpStatus.BAD_REQUEST.equals(response.getStatusCode())) {
            throw APIExceptions.solicitudIncorrecta(message);
        }

        if (error != null) {
            throw APIExceptions.errorResponse(error);
        }

        super.handleError(response);
    }

    private ErrorResponse extractErrorResponse(String bodyJson) {
        return UtilJson.toObject(bodyJson, ErrorResponse.class);
    }

    private String getErrorMessage(ErrorResponse errorResponse, String bodyJson) {
        return errorResponse == null ? bodyJson : errorResponse.getMessage();
    }

}
