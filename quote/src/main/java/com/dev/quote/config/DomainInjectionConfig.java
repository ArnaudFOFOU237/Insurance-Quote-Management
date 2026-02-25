package com.dev.quote.config;

import com.dev.quote.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.dev.quote.domaine",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = DomainService.class
        )
)
public class DomainInjectionConfig {
}
