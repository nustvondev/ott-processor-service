package com.onmicrosoft.ott.processor.core.spring.api;

import java.util.Collections;
import java.util.List;

public record ResponseStatus(String code, String message, List<FieldError> errors) {

    public ResponseStatus(String code, String message) {
        this(code, message, Collections.emptyList());
    }

    public ResponseStatus(String code) {
        this(code, null, null);
    }
}
