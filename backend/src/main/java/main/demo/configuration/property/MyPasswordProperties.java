package main.demo.configuration.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("env")
public class MyPasswordProperties {

    private String secretKey;

    public MyPasswordProperties() {
        System.out.println(secretKey);
    }
}
