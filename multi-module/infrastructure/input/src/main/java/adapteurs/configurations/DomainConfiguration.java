package adapteurs.configurations;

import com.dev.configurations.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.dev.service",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = DomainService.class
        )
)
public class DomainConfiguration {
}
