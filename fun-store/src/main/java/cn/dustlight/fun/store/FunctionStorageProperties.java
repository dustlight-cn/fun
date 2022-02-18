package cn.dustlight.fun.store;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "dustlight.fun.store")
public class FunctionStorageProperties {

    /**
     * 储存路径前缀
     */
    private String prefix = "";

    private long expiration = 1000 * 60 * 15;
    private long storeExpiration = 1000 * 60 * 60 * 24 * 365 * 10L;

}