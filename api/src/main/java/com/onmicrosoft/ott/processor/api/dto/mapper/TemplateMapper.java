package com.onmicrosoft.ott.processor.api.dto.mapper;

import com.onmicrosoft.ott.processor.api.dto.req.TemplateCursorRequest;
import com.onmicrosoft.ott.processor.api.dto.res.TemplateResponse;
import com.onmicrosoft.ott.processor.core.domain.common.PageData;
import com.onmicrosoft.ott.processor.core.dto.param.TemplateParam;
import com.onmicrosoft.ott.processor.core.dto.result.TemplateResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    TemplateParam toDomain(TemplateCursorRequest request);

    PageData<TemplateResponse> asPageDataResponse(PageData<TemplateResult> resultPageData);
}
