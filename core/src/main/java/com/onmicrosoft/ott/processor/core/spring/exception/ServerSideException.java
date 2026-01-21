package com.onmicrosoft.ott.processor.core.spring.exception;

/** Represent the exception cause by internal service. */
public class ServerSideException extends RuntimeException {
    private final String code;
    private transient Object payload;
    private transient String requestId;

    public ServerSideException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServerSideException(String code, String message, Exception exception) {
        super(message, exception);
        this.code = code;
    }

    public ServerSideException(String code, String message, Exception exception, String requestId) {
        super(message, exception);
        this.code = code;
        this.requestId = requestId;
    }

    public ServerSideException(
            String code, String message, Exception exception, Object payloadParams) {
        super(message, exception);
        this.code = code;
        this.payload = payloadParams;
    }

    public String getCode() {
        return code;
    }

    public String getRequestId() {
        return requestId;
    }

    public Object getPayload() {
        return payload;
    }
}
