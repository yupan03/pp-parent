package fegin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {

    @Bean
    @ConditionalOnMissingBean(FeginInterceptor.class)
    public FeginInterceptor feginInterceptor() {
        return new FeginInterceptor();
    }
}