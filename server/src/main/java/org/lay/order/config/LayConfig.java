package org.lay.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Create by Lay
 * 2018-04-29 16:01
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "lay")
public class LayConfig {

    private String name;

    private String age;

}
