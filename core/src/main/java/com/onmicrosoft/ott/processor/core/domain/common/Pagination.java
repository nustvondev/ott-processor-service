package com.onmicrosoft.ott.processor.core.domain.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {
    private Long nextCursor;
    private boolean hasNext;
}
