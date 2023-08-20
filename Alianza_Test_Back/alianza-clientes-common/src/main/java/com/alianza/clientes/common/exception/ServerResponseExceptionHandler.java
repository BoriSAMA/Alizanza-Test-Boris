package com.alianza.clientes.common.exception;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;

import com.alianza.clientes.common.message.MessageKeys;
import com.alianza.clientes.common.response.ErrorResponse;
import com.alianza.clientes.common.response.Responses;
import com.alianza.clientes.common.util.UtilJson;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

public abstract class ServerResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({
            // Web
            NotAuthorizedException.class,
            NotFoundException.class,
            NotFoundObjectException.class,
            // RestTemplate
            HttpClientErrorException.class,
            HttpServerErrorException.class,
            // Conversion
            ConversionFailedException.class,
            ConverterNotFoundException.class,
            // Validation
            InvalidEnumValueException.class,
            // Misc
            InvocationTargetException.class,
            IllegalArgumentException.class,
            BadRequestException.class,
            NotSupportedException.class,
            IllegalStateException.class,
            ErrorResponseException.class,
            NullPointerException.class,
            ArithmeticException.class,
            ClassCastException.class
    })
    public final ResponseEntity<Object> handleCommonWebFailures(Exception ex, WebRequest request) {
        if (ex instanceof NotFoundObjectException) {
            return handleNotFoundObjectFailure((NotFoundObjectException) ex, request);
        }
        else if (ex instanceof NotFoundException) {
            return handleNotFoundFailure((NotFoundException) ex, request);
        }
        else if (ex instanceof NotAuthorizedException) {
            return handleNotAuthorizedFailure((NotAuthorizedException) ex, request);
        }
        else if (ex instanceof HttpClientErrorException) {
            return handleClientErrorFailure((HttpClientErrorException) ex, request);
        }
        else if (ex instanceof HttpServerErrorException) {
            return handleServerErrorFailure((HttpServerErrorException) ex, request);
        }
        else if (ex instanceof ConversionFailedException) {
            return handleConversionFailure((ConversionFailedException) ex, request);
        }
        else if (ex instanceof ConverterNotFoundException) {
            return handleConverterNotFoundFailure((ConverterNotFoundException) ex, request);
        }
        else if (ex instanceof InvalidEnumValueException) {
            return handleInvalidEnumValueFailure((InvalidEnumValueException) ex, request);
        }
        else if (ex instanceof ErrorResponseException) {
            return handleErrorResponseFailure((ErrorResponseException) ex, request);
        }
        else if (ex instanceof BadRequestException) {
            return handleBadRequestFailure((BadRequestException) ex, request);
        }
        else {
            return handleMiscFailures(ex, request);
        }
    }

    protected ResponseEntity<Object> handleServerErrorFailure(HttpServerErrorException ex, WebRequest request) {
        String bodyString = ex.getResponseBodyAsString();
        ErrorResponse error = UtilJson.toObject(bodyString, ErrorResponse.class);
        if (error == null) {
            error = Responses.error(ex.getStatusCode(), ex.getResponseBodyAsString());
        }
        return handleExceptionInternal(ex, error, ex.getResponseHeaders(), error.getStatus(), request);
    }


    protected ResponseEntity<Object> handleClientErrorFailure(HttpClientErrorException ex, WebRequest request) {
        String bodyString = ex.getResponseBodyAsString();
        ErrorResponse error = UtilJson.toObject(bodyString, ErrorResponse.class);
        if (error == null) {
            error = Responses.error(ex.getStatusCode(), ex.getResponseBodyAsString());
        }
        return handleExceptionInternal(ex, error, ex.getResponseHeaders(), error.getStatus(), request);
    }


    protected ResponseEntity<Object> handleNotFoundFailure(NotFoundException ex, WebRequest request) {
        ErrorResponse error = Responses.error(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }


    protected ResponseEntity<Object> handleNotFoundObjectFailure(NotFoundObjectException ex, WebRequest request) {
        String message = messageSource.getMessage(MessageKeys.NOT_FOUND_OBJECT, new Object[] {ex.getObjectId()}, getLocale());
        ErrorResponse error = Responses.error(HttpStatus.NOT_FOUND, message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }


    protected ResponseEntity<Object> handleNotAuthorizedFailure(NotAuthorizedException ex, WebRequest request) {
        ErrorResponse error = Responses.error(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }


    protected ResponseEntity<Object> handleConversionFailure(ConversionFailedException ex, WebRequest request) {
        if (ex.getRootCause() instanceof NotFoundException) {
            return handleNotFoundFailure((NotFoundException) ex.getCause(), request);
        }
        String message = messageSource.getMessage(MessageKeys.ERROR_UNEXPECTED, null, getLocale());
        ErrorResponse error = Responses.error(HttpStatus.BAD_REQUEST, message, ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }

    protected ResponseEntity<Object> handleConverterNotFoundFailure(ConverterNotFoundException ex, WebRequest request) {
        String message = messageSource.getMessage(MessageKeys.ERROR_CONVERTER_NOT_FOUND, null, getLocale());
        ErrorResponse error = Responses.error(HttpStatus.INTERNAL_SERVER_ERROR, message, ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }

    protected ResponseEntity<Object> handleInvalidEnumValueFailure(InvalidEnumValueException ex, WebRequest request) {
        String message = messageSource.getMessage(MessageKeys.VALIDATION_ENUM_INVALID, new Object[] {ex.getValue()}, getLocale());
        ErrorResponse response = Responses.error(HttpStatus.BAD_REQUEST, message, ex);
        return handleExceptionInternal(ex, response, new HttpHeaders(), response.getStatus(), request);
    }

    protected ResponseEntity<Object> handleBadRequestFailure(BadRequestException ex, WebRequest request) {
        String message = ex.getLocalizedMessage();
        ErrorResponse error = Responses.error(HttpStatus.BAD_REQUEST, message);
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }

    protected ResponseEntity<Object> handleMiscFailures(Exception ex, WebRequest request) {
        String message = messageSource.getMessage(MessageKeys.ERROR_UNEXPECTED, null, getLocale());
        ErrorResponse error = Responses.error(HttpStatus.BAD_REQUEST, message, ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }

    protected ResponseEntity<Object> handleServerFailures(Exception ex, WebRequest request) {
        String message = messageSource.getMessage(MessageKeys.ERROR_UNEXPECTED, null, getLocale());
        ErrorResponse error = Responses.error(HttpStatus.INTERNAL_SERVER_ERROR, message, ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }

    protected ResponseEntity<Object> handleErrorResponseFailure(ErrorResponseException ex, WebRequest request) {
        ErrorResponse error = ex.getErrorResponse();
        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<ValidationError> errors = new ArrayList<>();
        BindingResult result = ex.getBindingResult();

        for (ObjectError objectError : result.getAllErrors()) {
            ValidationError error = new ValidationError();
            error.setMessage(objectError.getDefaultMessage());

            if (objectError instanceof FieldError) {
                error.setField(((FieldError) objectError).getField());
            }

            errors.add(error);
        }

        String message = messageSource.getMessage(MessageKeys.VALIDATION_MESSAGE, null, getLocale());
        return handleExceptionInternal(ex, Responses.validation(errors, message), headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        if (ex.getRootCause() instanceof InvalidEnumValueException) {
            return handleInvalidEnumValueFailure((InvalidEnumValueException) ex.getRootCause(), request);
        }

        String message = messageSource.getMessage(MessageKeys.VALIDATION_INVALID_JSON, null, getLocale());
        ErrorResponse error = Responses.error(HttpStatus.BAD_REQUEST, message);

        return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        logger.debug(ex.getLocalizedMessage(), ex);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    protected MessageSource getMessageSource() {
        return messageSource;
    }
}
