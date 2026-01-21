package com.onmicrosoft.ott.processor.core.domain.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> {
    private List<T> content;
    private Pagination pagination;
}
