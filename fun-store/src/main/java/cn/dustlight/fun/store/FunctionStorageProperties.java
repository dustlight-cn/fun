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

    private long expiration = 1000L * 60 * 5;
    private long storeExpiration = 1000L * 60L * 60L * 24L * 365L * 10L;

}
