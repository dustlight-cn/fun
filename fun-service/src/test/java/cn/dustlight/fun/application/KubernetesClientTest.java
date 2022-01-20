package cn.dustlight.fun.application;

import io.kubernetes.client.openapi.ApiCallback;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.*;
import io.kubernetes.client.openapi.models.V1ConfigMap;
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

        coreV1Api.readNamespacedConfigMapAsync("kubeless-config", "kubeless", "", false, false
                , new ApiCallback<V1ConfigMap>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        e.printStackTrace();
                        synchronized (coreV1Api) {
                            coreV1Api.notifyAll();
                        }
                    }

                    @Override
                    public void onSuccess(V1ConfigMap result, int statusCode, Map<String, List<String>> responseHeaders) {

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
