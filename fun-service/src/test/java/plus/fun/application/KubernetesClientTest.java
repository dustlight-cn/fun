package plus.fun.application;

import io.kubernetes.client.openapi.ApiCallback;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.*;
import io.kubernetes.client.openapi.models.V1ConfigMap;
import io.kubernetes.client.openapi.models.V1ConfigMapList;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.proto.V1;
import io.kubernetes.client.util.ClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class KubernetesClientTest {

    private static ApiClient client;

    static {
        try {
            client = ClientBuilder.defaultClient();
            Configuration.setDefaultApiClient(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws ApiException, InterruptedException {
        CoreV1Api coreV1Api = new CoreV1Api();
        V1ConfigMap cfg = coreV1Api.readNamespacedConfigMap("kubeless-config", "kubeless", "", false, false);
        System.out.println(cfg);

        coreV1Api.listNamespacedConfigMapAsync("kubeless",
                "kubeless-config",
                false,
                "",
                "",
                "",
                null,
                "",
                null,
                false,
                new ApiCallback<V1ConfigMapList>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        e.printStackTrace();
                        synchronized (coreV1Api) {
                            coreV1Api.notifyAll();
                        }
                    }

                    @Override
                    public void onSuccess(V1ConfigMapList result, int statusCode, Map<String, List<String>> responseHeaders) {
                        System.out.println(result);
                        synchronized (coreV1Api) {
                            coreV1Api.notifyAll();
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });
        synchronized (coreV1Api) {
            coreV1Api.wait();
        }
    }
}
