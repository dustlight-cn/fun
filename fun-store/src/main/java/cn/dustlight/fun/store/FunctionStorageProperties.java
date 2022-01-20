package cn.dustlight.fun.store;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "plus.fun.store")
public class FunctionStorageProperties {

    /**
     * 储存路径前缀
     */
    private String prefix = "";

    private long expiration = 1000 * 60 * 15;

}
