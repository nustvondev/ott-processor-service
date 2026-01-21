package com.onmicrosoft.ott.processor.api.controller.admin;

import com.onmicrosoft.ott.processor.api.dto.mapper.TemplateMapper;
import com.onmicrosoft.ott.processor.api.dto.req.TemplateCursorRequest;
import com.onmicrosoft.ott.processor.api.dto.res.TemplateResponse;
import com.onmicrosoft.ott.processor.core.domain.common.PageData;
import com.onmicrosoft.ott.processor.core.spring.api.ResponseApi;
import com.onmicrosoft.ott.processor.core.usecase.TemplateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(NotificationBOController.PATH)
@RequiredArgsConstructor
public class NotificationBOController {
    public static final String PATH = "/v1/admin/notifications";

    private final TemplateUseCase templateUseCase;
    private final TemplateMapper templateMapper;

    @GetMapping("/search")
    public ResponseApi<PageData<TemplateResponse>> searchNotifications(
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) String languageCode) {

        var param =
                templateMapper.toDomain(
                        TemplateCursorRequest.builder()
                                .cursor(cursor)
                                .pageSize(pageSize)
                                .eventType(eventType)
                                .languageCode(languageCode)
                                .build());
        var domainPage = templateUseCase.searchTemplate(param);
        var responsePage = templateMapper.asPageDataResponse(domainPage);

        return ResponseApi.success(responsePage);
    }
}
