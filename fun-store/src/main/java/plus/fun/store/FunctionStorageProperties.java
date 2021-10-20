package plus.fun.store;

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

    private Expiration expiration = new Expiration();

    @Getter
    @Setter
    public static class Expiration {

        /**
         * 下载链接过期时间
         */
        private long get = 1000 * 60 * 15;
        /**
         * 存储链接过期时间
         */
        private long store = 1000 * 60 * 60 * 24 * 365 * 5;

    }

}
