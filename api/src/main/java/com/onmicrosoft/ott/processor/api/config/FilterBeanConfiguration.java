package com.onmicrosoft.ott.processor.api.config;

import com.onmicrosoft.ott.processor.api.filter.RequestIdMdcFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterBeanConfiguration {
    @Bean
    public FilterRegistrationBean<RequestIdMdcFilter> requestHeaderMdcFilter() {
        FilterRegistrationBean<RequestIdMdcFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestIdMdcFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
