package com.onmicrosoft.ott.processor.api.controller;

import com.onmicrosoft.ott.processor.api.dto.mapper.NotificationMapper;
import com.onmicrosoft.ott.processor.api.dto.req.RegisterTokenRequest;
import com.onmicrosoft.ott.processor.core.dto.result.RegisterTokenResult;
import com.onmicrosoft.ott.processor.core.spring.api.ResponseApi;
import com.onmicrosoft.ott.processor.core.usecase.NotificationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/internal/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    //    private final PushNotificationService pushNotificationService;
    private final NotificationUseCase notificationUseCase;
    private final NotificationMapper notificationMapper;

    @PostMapping("/register")
    public ResponseApi<RegisterTokenResult> registerToken(
            @RequestBody @Validated RegisterTokenRequest request) {
        return ResponseApi.success(
                notificationUseCase.registerToken(notificationMapper.toDomain(request)));
    }
}
