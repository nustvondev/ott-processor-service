package com.onmicrosoft.ott.processor.core.dto.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TemplateParam {
    private Long cursor;
    private Integer pageSize;
    private String eventType;
    private String languageCode;
}
