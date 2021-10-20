package plus.fun.store;

import cn.dustlight.storage.core.RestfulStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({FunctionStorageProperties.class})
public class FunctionStorageConfiguration {

    @Bean
    @ConditionalOnBean(RestfulStorage.class)
    public FunctionStorage functionStorage(@Autowired RestfulStorage storage,
                                           @Autowired FunctionStorageProperties properties) {
        FunctionStorage functionStorage = new FunctionStorage();
        functionStorage.setStorage(storage);
        functionStorage.setPrefix(properties.getPrefix());
        functionStorage.setGetExpiration(properties.getExpiration());
        return functionStorage;
    }

}
