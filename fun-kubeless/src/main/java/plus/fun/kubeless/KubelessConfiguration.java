package plus.fun.kubeless;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({KubelessProperties.class})
public class KubelessConfiguration {


}
