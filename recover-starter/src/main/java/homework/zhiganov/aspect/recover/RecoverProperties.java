package homework.zhiganov.aspect.recover;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("application.recover")
public class RecoverProperties {

    private Boolean enabled = false; //default value

}
