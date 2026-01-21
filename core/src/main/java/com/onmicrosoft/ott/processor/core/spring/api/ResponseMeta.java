package com.onmicrosoft.ott.processor.core.spring.api;

public record ResponseMeta(String requestId, String nextCursor) {

    public static ResponseMeta fromRequestId(String requestId) {
        return new ResponseMeta(requestId, null);
    }
}
