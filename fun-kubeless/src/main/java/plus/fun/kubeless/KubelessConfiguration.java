package plus.fun.kubeless;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plus.fun.kubeless.services.KubelessFunctionService;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({KubelessProperties.class})
public class KubelessConfiguration {

    @Bean
    @ConditionalOnMissingBean(ApiClient.class)
    public ApiClient apiClient() throws IOException {
        return Config.defaultClient();
    }

    @Bean
    public KubelessFunctionService kubelessFunctionService(@Autowired ApiClient client,
                                                           @Autowired ObjectMapper mapper) {
        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);
        return new KubelessFunctionService(client, mapper);
    }

}
