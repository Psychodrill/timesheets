package homework.zhiganov.aspect.recover;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RecoverProperties.class)
public class RecoverAutoConfiguration {
        @Bean
    public RecoverAspect loggingAspect(Boolean enabled){
        return new RecoverAspect(enabled);
    }

}
