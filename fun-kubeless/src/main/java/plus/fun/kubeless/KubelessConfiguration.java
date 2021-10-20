package plus.fun.kubeless;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plus.fun.kubeless.services.KubelessFunctionService;

@Configuration
@EnableConfigurationProperties({KubelessProperties.class})
public class KubelessConfiguration {

    @Bean
    public KubelessFunctionService kubelessFunctionService() {
        return new KubelessFunctionService();
    }

}
