package com.onmicrosoft.ott.processor.core.spring.exception;

import com.onmicrosoft.ott.processor.core.spring.api.FieldError;
import java.util.List;

/** Represent the exception cause by client, ex: invalidate field. */
public class ClientSideException extends RuntimeException {

    private final String code;

    private transient Object payload;

    private transient List<FieldError> fieldErrors;

    public ClientSideException(String code, String message, Exception exception) {
        super(message, exception);
        this.code = code;
    }

    public ClientSideException(String code, String message, Exception exception, Object payload) {
        super(message, exception);
        this.code = code;
        this.payload = payload;
    }

    public ClientSideException(
            String code, String message, Exception exception, List<FieldError> fieldErrors) {
        super(message, exception);
        this.code = code;
        this.fieldErrors = fieldErrors;
    }

    public ClientSideException(String code, String message, List<FieldError> fieldErrors) {
        super(message, null);
        this.code = code;
        this.fieldErrors = fieldErrors;
    }

    public ClientSideException(String code, String message) {
        super(message, null);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Object getPayload() {
        return payload;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
