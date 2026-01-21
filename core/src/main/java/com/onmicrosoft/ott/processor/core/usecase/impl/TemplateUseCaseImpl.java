package com.onmicrosoft.ott.processor.core.usecase.impl;

import com.onmicrosoft.ott.processor.core.domain.common.PageData;
import com.onmicrosoft.ott.processor.core.dto.param.TemplateParam;
import com.onmicrosoft.ott.processor.core.dto.result.TemplateResult;
import com.onmicrosoft.ott.processor.core.service.TemplateService;
import com.onmicrosoft.ott.processor.core.usecase.TemplateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TemplateUseCaseImpl implements TemplateUseCase {
    private final TemplateService templateService;

    @Override
    public PageData<TemplateResult> searchTemplate(TemplateParam param) {
        return templateService.searchTemplate(param);
    }
}
