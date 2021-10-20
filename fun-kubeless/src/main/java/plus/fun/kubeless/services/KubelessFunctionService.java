package plus.fun.kubeless.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.ApiCallback;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ConfigMap;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import plus.fun.core.service.FunctionService;
import plus.fun.kubeless.entities.KubelessFunction;
import plus.fun.kubeless.entities.RuntimeImage;
import plus.fun.kubeless.entities.RuntimeImages;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class KubelessFunctionService implements FunctionService<KubelessFunction>, InitializingBean, ApplicationContextAware {

    public static final String RUNTIME_IMAGES_KEY = "runtime-images";
    private ApiClient client;
    private ObjectMapper mapper;
    private ApplicationContext applicationContext;
    @Getter
    @Setter
    private String kubelessConfigName = "kubeless-config";
    @Getter
    @Setter
    private String kubelessNamespace = "kubeless";
    protected CoreV1Api coreV1Api = new CoreV1Api();

    @Override
    public Mono<KubelessFunction> create(String clientId, String owner, String name, String runtime, String handler, String contentUrl) {
        return null;
    }

    @Override
    public Mono<KubelessFunction> get(String clientId, String name) {
        return null;
    }

    @Override
    public Flux<KubelessFunction> list(String clientId) {
        return null;
    }

    @Override
    public Flux<String> getRuntimes() {
        return Mono.create(sink -> sink.onRequest(unused ->
                {
                    try {
                        coreV1Api.readNamespacedConfigMapAsync(kubelessConfigName,
                                kubelessNamespace,
                                "",
                                false,
                                false,
                                new ApiCallback<V1ConfigMap>() {
                                    @Override
                                    public void onFailure(ApiException e, int i, Map<String, List<String>> map) {
                                        sink.error(e);
                                    }

                                    @Override
                                    public void onSuccess(V1ConfigMap configMap, int i, Map<String, List<String>> map) {
                                        sink.success(configMap);
                                    }

                                    @Override
                                    public void onUploadProgress(long l, long l1, boolean b) {

                                    }

                                    @Override
                                    public void onDownloadProgress(long l, long l1, boolean b) {

                                    }
                                });
                    } catch (Throwable e) {
                        sink.error(e);
                    }
                }))
                .cast(V1ConfigMap.class)
                .map(v1ConfigMap -> v1ConfigMap.getData().get(RUNTIME_IMAGES_KEY))
                .map(this::listRuntimes)
                .flatMapMany(strings -> Flux.fromIterable(strings));
    }

    @SneakyThrows
    protected Collection<String> listRuntimes(String json) {
        HashSet<String> runtimes = new HashSet<>();
        RuntimeImages images = mapper.readValue(json, RuntimeImages.class);
        for (RuntimeImage image : images) {
            if (image.getVersions() == null || image.getVersions().size() == 0)
                continue;
            for (RuntimeImage.Version version : image.getVersions()) {
                runtimes.add(String.format("%s%s", image.getId(), version.getVersion()));
            }
        }
        return runtimes;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (client == null)
            client = ClientBuilder.defaultClient();
        Configuration.setDefaultApiClient(client);

        if (mapper == null)
            mapper = applicationContext.getBean(ObjectMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }
}
