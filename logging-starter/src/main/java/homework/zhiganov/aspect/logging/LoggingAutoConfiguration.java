package homework.zhiganov.aspect.logging;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
public class LoggingAutoConfiguration {

    @Bean
    public LoggingAspect loggingAspect(LoggingProperties properties, Boolean printArgs){
        return new LoggingAspect(properties, printArgs);
    }

}
