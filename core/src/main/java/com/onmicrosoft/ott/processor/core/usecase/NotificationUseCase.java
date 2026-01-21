package com.onmicrosoft.ott.processor.core.usecase;

import com.onmicrosoft.ott.processor.core.dto.param.RegisterTokenParam;
import com.onmicrosoft.ott.processor.core.dto.result.RegisterTokenResult;

public interface NotificationUseCase {
    RegisterTokenResult registerToken(RegisterTokenParam param);
}
