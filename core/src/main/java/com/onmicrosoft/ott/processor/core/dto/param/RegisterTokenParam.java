package com.onmicrosoft.ott.processor.core.dto.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterTokenParam {
    private String deviceId;
    private String firebaseToken;
}
