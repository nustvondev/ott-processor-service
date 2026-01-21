package com.onmicrosoft.ott.processor.core.service.impl;

import com.onmicrosoft.ott.processor.core.domain.common.PageData;
import com.onmicrosoft.ott.processor.core.dto.param.TemplateParam;
import com.onmicrosoft.ott.processor.core.dto.result.TemplateResult;
import com.onmicrosoft.ott.processor.core.service.TemplateService;
import com.onmicrosoft.ott.processor.core.service.infra.TemplateInfraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateInfraService templateInfraService;

    @Override
    public PageData<TemplateResult> searchTemplate(TemplateParam param) {
        log.info("searchTemplate param1111111111: {}", param);
        return templateInfraService.searchTemplate(param);
    }
}
