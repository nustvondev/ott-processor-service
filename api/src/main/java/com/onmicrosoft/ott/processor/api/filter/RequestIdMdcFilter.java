package com.onmicrosoft.ott.processor.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;

/**
 * This class will be inserted the requestId from header to MDC( Threadlocal) This class should be
 * registered then it can be use.
 */
public class RequestIdMdcFilter implements Filter {
    public static final String REQUEST_ID_KEY = "X-Request-ID";

    /**
     * Getting RequestId from header or generate new incase dont have and insert to thread local(MDC).
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String headerValue = httpRequest.getHeader(REQUEST_ID_KEY);

        String requestId = headerValue != null ? headerValue : UUID.randomUUID().toString();

        MDC.put(REQUEST_ID_KEY, requestId);
        filterChain.doFilter(httpRequest, response);
        MDC.clear();
    }
}
