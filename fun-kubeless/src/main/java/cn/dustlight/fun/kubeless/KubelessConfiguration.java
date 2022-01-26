package cn.dustlight.fun.kubeless;

import cn.dustlight.fun.kubeless.services.KubelessFunctionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                                                           @Autowired ObjectMapper mapper,
                                                           @Autowired KubelessProperties properties) {
        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);
        KubelessFunctionService service = new KubelessFunctionService(client, mapper);
        service.setNamespace(properties.getFunctionsNamespace());
        service.setKubelessNamespace(properties.getKubelessNamespace());
        service.setKubelessConfigName(properties.getKubelessConfigMap());

        service.setHostFormat(properties.getHostFormat());
        service.setIngressClass(properties.getIngressClass());
        service.setHostTls(properties.getHostTls());
        return service;
    }

}
