package com.onmicrosoft.ott.processor.api;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.onmicrosoft.ott.processor")
@EntityScan("com.onmicrosoft.ott.processor.infra.repository.entity")
public class OttProcessorServiceApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(OttProcessorServiceApplication.class, args);
    }
}
