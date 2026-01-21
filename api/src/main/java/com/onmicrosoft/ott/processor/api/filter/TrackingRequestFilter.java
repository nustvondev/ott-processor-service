package com.onmicrosoft.ott.processor.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrackingRequestFilter extends OncePerRequestFilter {
    private static final String HEADERS_STRING_FORMAT = "%s = %s; ";
    private static final String REQUEST_ID = "X-Request-ID";

    @Value("1000") // TODO: move to config
    private int maxLengthPayloadLog;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CachedHttpServletRequest requestWrapper = new CachedHttpServletRequest(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        String payload =
                IOUtils.toString(requestWrapper.getInputStream(), request.getCharacterEncoding());
        logRequest(payload, requestWrapper);
        executeFilter(payload, requestWrapper, responseWrapper, filterChain);
        log.info(
                "Completed incoming request to service: {} {}",
                request.getMethod(),
                request.getRequestURI());
    }

    @SneakyThrows
    private void logRequest(String payload, HttpServletRequest request) {
        log.info("Incoming request to service: {} {}", request.getMethod(), request.getRequestURI());
        log.info(REQUEST_ID + ": {}", request.getHeader(REQUEST_ID));
        log.info("Query Params: [{}]", request.getQueryString());
        log.debug("Headers: [{}]", buildHeaders(request));
    }

    private String buildHeaders(HttpServletRequest request) {
        Iterator<String> headers = request.getHeaderNames().asIterator();

        StringBuilder headersString = new StringBuilder();
        while (headers.hasNext()) {
            String headerName = headers.next();
            headersString.append(
                    String.format(HEADERS_STRING_FORMAT, headerName, request.getHeader(headerName)));
        }

        return headersString.toString();
    }

    @SneakyThrows
    private void executeFilter(
            String payload,
            CachedHttpServletRequest request,
            ContentCachingResponseWrapper responseWrapper,
            FilterChain filterChain) {
        try {
            filterChain.doFilter(request, responseWrapper);
        } finally {
            byte[] responseArray = responseWrapper.getContentAsByteArray();
            String responseStr = new String(responseArray, responseWrapper.getCharacterEncoding());
            if (responseWrapper.getStatus() != 200) {
                log.error(
                        "has an error occur with status [{}] and response [{}]",
                        responseWrapper.getStatus(),
                        responseStr);
            }

            // copy response back to request
            responseWrapper.copyBodyToResponse();
        }
    }
}
