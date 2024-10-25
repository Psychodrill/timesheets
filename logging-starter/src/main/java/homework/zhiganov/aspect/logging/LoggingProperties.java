package homework.zhiganov.aspect.logging;

import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("application.logging")
public class LoggingProperties {

    private Level level= Level.DEBUG;// default level value

}
