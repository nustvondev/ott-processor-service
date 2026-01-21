package com.onmicrosoft.ott.processor.core.usecase.impl;

import com.onmicrosoft.ott.processor.core.dto.param.RegisterTokenParam;
import com.onmicrosoft.ott.processor.core.dto.result.RegisterTokenResult;
import com.onmicrosoft.ott.processor.core.usecase.NotificationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationUseCaseImpl implements NotificationUseCase {

    @Override
    public RegisterTokenResult registerToken(RegisterTokenParam param) {
        log.info("register deviceId: {} to receive notification", param.getDeviceId());
        return null;
    }
}
