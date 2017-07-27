package top.zemal.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-25 15:57
 */

@Component
@ConfigurationProperties(prefix = "common")
@PropertySource("classpath:common_url.properties")
public class CommonUrlConfig {

    private List<String> url = new ArrayList<>();

    public List<String> getUrl() {
        return url;
    }

}
