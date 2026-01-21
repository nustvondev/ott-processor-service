package com.onmicrosoft.ott.processor.api.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TemplateCursorRequest {
    private Long cursor;
    private Integer pageSize = 5;
    private String eventType;
    private String languageCode;
}
