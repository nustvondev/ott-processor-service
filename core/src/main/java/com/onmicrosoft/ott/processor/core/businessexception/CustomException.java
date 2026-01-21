package com.onmicrosoft.ott.processor.core.businessexception;

import com.onmicrosoft.ott.processor.core.spring.api.FieldError;
import java.util.List;
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    private final String code;
    private Object payload;
    private List<FieldError> fieldErrors;

    private HttpStatus httpStatus;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage(), null);
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public CustomException(String code, String message, Exception exception) {
        super(message, exception);
        this.code = code;
    }

    public CustomException(ErrorCode errorCode, Object... args) {
        super(errorCode.getMessage().formatted(args), null);
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public CustomException(String code, String message, Exception exception, Object payload) {
        super(message, exception);
        this.code = code;
        this.payload = payload;
    }

    public CustomException(
            String code, String message, Exception exception, List<FieldError> fieldErrors) {
        super(message, exception);
        this.code = code;
        this.fieldErrors = fieldErrors;
    }

    public CustomException(String code, String message, List<FieldError> fieldErrors) {
        super(message, (Throwable) null);
        this.code = code;
        this.fieldErrors = fieldErrors;
    }

    public CustomException(String code, String message) {
        super(message, (Throwable) null);
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return this.code;
    }

    public Object getPayload() {
        return this.payload;
    }

    public List<FieldError> getFieldErrors() {
        return this.fieldErrors;
    }
}
