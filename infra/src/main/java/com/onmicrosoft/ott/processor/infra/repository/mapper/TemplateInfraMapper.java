package com.onmicrosoft.ott.processor.infra.repository.mapper;

import com.onmicrosoft.ott.processor.core.dto.result.TemplateResult;
import com.onmicrosoft.ott.processor.infra.repository.entity.TemplateEntity;
import java.util.List;
import org.apache.hc.client5.http.utils.DateUtils;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        imports = {JsonUtils.class, DateUtils.class},
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface TemplateInfraMapper {
    public abstract List<TemplateResult> toTemplateResults(List<TemplateEntity> templateEntities);

    public abstract TemplateResult toTemplateResult(TemplateEntity template);
}
