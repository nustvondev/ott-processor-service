package com.onmicrosoft.ott.processor.core.businessexception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR(
            "INTERNAL_SERVER_ERROR", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_FAILED("VALIDATION_FAILED", "Failed validation", HttpStatus.BAD_REQUEST),
    SERVER_ERROR("SERVER_ERROR", "Server error: %s", HttpStatus.INTERNAL_SERVER_ERROR);

    @Getter private final String code;
    @Getter private final String message;
    @Getter private final HttpStatus httpStatus;
    @Getter @Setter private Object payloadError;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static ErrorCode from(ErrorCode errorCode, Object payloadError) {
        errorCode.setPayloadError(payloadError);
        return errorCode;
    }

    public CustomException get() {
        return new CustomException(this);
    }

    public CustomException get(Object... args) {
        return new CustomException(this, args);
    }
}
