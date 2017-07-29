package top.zemal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.MimeType;

import java.nio.charset.Charset;

/**
 * @author zemal-tan
 * @description 模板配置文件
 * @create 2017-07-29 11:06
 */
@ConfigurationProperties("spring.thymeleaf")
public class ThymeleafPropertiesConfig {
    private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");
    private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
    private boolean checkTemplate = true;
    private boolean checkTemplateLocation = true;
    private String prefix = "classpath:/templates/";
    private String suffix = ".html";
    private String mode = "HTML5";

    public boolean isCheckTemplate() {
        return checkTemplate;
    }

    public void setCheckTemplate(boolean checkTemplate) {
        this.checkTemplate = checkTemplate;
    }

    public boolean isCheckTemplateLocation() {
        return checkTemplateLocation;
    }

    public void setCheckTemplateLocation(boolean checkTemplateLocation) {
        this.checkTemplateLocation = checkTemplateLocation;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
