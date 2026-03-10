package adapteurs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication(
        scanBasePackages = {
                "com.dev.domain",
                "com.dev.persistances",
                "com.dev.rest"
        }
)
@EntityScan(basePackages = "com.dev.persistence")
@EnableDiscoveryClient
public class QuoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteApplication.class, args);
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
