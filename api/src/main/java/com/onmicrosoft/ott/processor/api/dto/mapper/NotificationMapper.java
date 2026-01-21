package com.onmicrosoft.ott.processor.api.dto.mapper;

import com.onmicrosoft.ott.processor.api.dto.req.RegisterTokenRequest;
import com.onmicrosoft.ott.processor.core.dto.param.RegisterTokenParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    RegisterTokenParam toDomain(RegisterTokenRequest request);
}
