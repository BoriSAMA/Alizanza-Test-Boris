package com.alianza.clientes.common.exception;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import com.alianza.clientes.common.response.ErrorResponse;

public final class APIExceptions {

    private APIExceptions() {
        super();
    }

    /**
     * Retorna una nueva {@link NotFoundException} con el id del objeto como parte del mensaje.
     *
     * @param id El id del objeto no encontrado.
     * @return La nueva excepcion.
     */
    public static NotFoundException objetoNoEncontrado(final Object id) {
        return new NotFoundObjectException(id);
    }

    /**
     * Retorna una nueva {@link NotFoundException} con el id del objeto como parte del mensaje.
     *
     * @param message El mensaje a enviar
     * @return La nueva excepcion.
     */
    public static NotFoundException objetoNoEncontrado(final String message) {
        return new NotFoundException(message);
    }

    /**
     * Retorna una nueva excepción de usuario no autorizado.
     *
     * @param message mensaje de la exepción
     * @return Nueva excepción
     */
    public static NotAuthorizedException noAutorizado(final String message) {
        return new NotAuthorizedException(message, new Object(), (Object[]) null);
    }

    /**
     * Retorna una excepcion de solicitud incorrecta.
     *
     * @param message mensaje de la excepción
     * @return Nueva excepcion
     */
    public static BadRequestException solicitudIncorrecta(final String message) {
        return new BadRequestException(message);
    }

    /**
     * Retorna una exception de error cuando no es posible hacer soft delete debido a validaciones.
     * @param message El mensaje de error.
     * @return La nueva exception.
     */
    public static SoftDeleteEntityException softDeleteError(String message) {
        return new SoftDeleteEntityException(message);
    }

    public static ErrorResponseException errorResponse(ErrorResponse errorResponse) {
        return new ErrorResponseException(errorResponse);
    }

}
