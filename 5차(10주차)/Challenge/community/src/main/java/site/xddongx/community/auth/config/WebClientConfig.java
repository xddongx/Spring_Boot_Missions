package site.xddongx.community.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient authSsoWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8000/check-user").build();
    }
}
