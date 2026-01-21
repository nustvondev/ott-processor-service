package com.onmicrosoft.ott.processor.api.dto.req;

import lombok.*;

@Builder
public record RegisterTokenRequest(String deviceId, String firebaseToken) {}
