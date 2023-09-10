package com.krokoq.simple.opentelemetry.rest.configuration;

import io.opentelemetry.instrumentation.spring.autoconfigure.SamplerProperties;
import io.opentelemetry.instrumentation.spring.autoconfigure.exporters.otlp.OtlpExporterProperties;
import io.opentelemetry.instrumentation.spring.autoconfigure.propagators.PropagationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final OtlpExporterProperties otlpExporterProperties;
    private final SamplerProperties samplerProperties;
    private final PropagationProperties propagationProperties;

    @Value("${service.tracing.otl.enabled:false}")
    private boolean enabled;
    @Value("${service.tracing.otl.endpoint}")
    private String endpoint;
    @Value("${service.tracing.otl.traces.probability}")
    private Double probability;
    @Value("${service.tracing.otl.traces.propagation}")
    private List<String> propagation;

    @PostConstruct
    public void setOtlpConfig() {
        otlpExporterProperties.setEnabled(enabled);
        otlpExporterProperties.setEndpoint(endpoint);

        samplerProperties.setProbability(probability);

        propagationProperties.setType(propagation);
    }
}
