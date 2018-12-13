package com.openwaygroup.ic.service.payment;

import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceConfig {

    @Bean
    public Tracer jaegerTracer() {
        io.jaegertracing.Configuration configuration = new io.jaegertracing.Configuration("paymentService");
        return configuration
                .getTracerBuilder()
                .build();
    }

}
