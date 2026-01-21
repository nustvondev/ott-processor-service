package com.onmicrosoft.ott.processor.core.dto.result;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateResult {
    private Long id;

    private String eventType;
    private String channelType;
    private String titleTemplate;
    private String messageTemplate;
    private String languageCode;
    private String category;
    private Boolean isPushOnly;
    private String serviceRequired;

    private LocalDateTime createdAt;
}
