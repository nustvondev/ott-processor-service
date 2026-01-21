package com.onmicrosoft.ott.processor.core.service.infra;

import com.onmicrosoft.ott.processor.core.domain.common.PageData;
import com.onmicrosoft.ott.processor.core.dto.param.TemplateParam;
import com.onmicrosoft.ott.processor.core.dto.result.TemplateResult;

public interface TemplateInfraService {
    PageData<TemplateResult> searchTemplate(TemplateParam param);
}
