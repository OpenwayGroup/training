package com.openwaygroup.ic.service.history;

import io.opentracing.Tracer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TraceConfig {

    @Bean
    public Tracer jaegerTracer() {
        io.jaegertracing.Configuration configuration = io.jaegertracing.Configuration.fromEnv();
        return configuration
                .getTracerBuilder()
                .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

}
