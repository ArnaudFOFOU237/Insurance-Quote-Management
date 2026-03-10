package adapteur;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestClient;

@SpringBootApplication
@ComponentScan(basePackages = {"adapteur", "adapteurs"})
public class QuoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteApplication.class, args);
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
