package com.onmicrosoft.ott.processor.infra.service.impl;

import com.onmicrosoft.ott.processor.core.domain.common.PageData;
import com.onmicrosoft.ott.processor.core.domain.common.Pagination;
import com.onmicrosoft.ott.processor.core.dto.param.TemplateParam;
import com.onmicrosoft.ott.processor.core.dto.result.TemplateResult;
import com.onmicrosoft.ott.processor.core.service.infra.TemplateInfraService;
import com.onmicrosoft.ott.processor.infra.repository.dao.TemplateRepository;
import com.onmicrosoft.ott.processor.infra.repository.entity.TemplateEntity;
import com.onmicrosoft.ott.processor.infra.repository.mapper.TemplateInfraMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateInfraServiceImpl implements TemplateInfraService {
    private final TemplateRepository templateRepository;
    private final TemplateInfraMapper templateInfraMapper;

    @Override
    public PageData<TemplateResult> searchTemplate(TemplateParam param) {
        Pageable pageable = PageRequest.of(0, param.getPageSize());
        List<TemplateEntity> data;
        if (param.getCursor() == null) {
            data =
                    templateRepository.findFirstPage(param.getEventType(), param.getLanguageCode(), pageable);
        } else {
            data =
                    templateRepository.findNextPage(
                            param.getCursor(), param.getEventType(), param.getLanguageCode(), pageable);
        }

        List<TemplateResult> results = templateInfraMapper.toTemplateResults(data);
        Long nextCursor = data.isEmpty() ? null : data.get(data.size() - 1).getId();
        boolean hasNext = data.size() >= param.getPageSize();

        return PageData.<TemplateResult>builder()
                .content(results)
                .pagination(Pagination.builder().nextCursor(nextCursor).hasNext(hasNext).build())
                .build();
    }
}
