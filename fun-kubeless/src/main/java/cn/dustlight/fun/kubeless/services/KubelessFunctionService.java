package cn.dustlight.fun.kubeless.services;

import cn.dustlight.fun.kubeless.entities.kubeless.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.custom.IntOrString;
import io.kubernetes.client.openapi.ApiCallback;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import io.kubernetes.client.openapi.models.*;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import okhttp3.Call;
import cn.dustlight.fun.core.exceptions.ErrorEnum;
import cn.dustlight.fun.core.service.FunctionService;
import cn.dustlight.fun.kubeless.entities.KubelessFunction;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class KubelessFunctionService implements FunctionService<KubelessFunction> {

    public static final String RUNTIME_IMAGES_KEY = "runtime-images";

    private ApiClient client;
    private ObjectMapper mapper;

    @Getter
    @Setter
    private String kubelessConfigName = "kubeless-config";

    @Getter
    @Setter
    private String kubelessNamespace = "kubeless";

    @Getter
    @Setter
    private String namespace = "functions";

    @Getter
    @Setter
    private String hostFormat = "%s.fun.dustlight.cn";

    @Getter
    @Setter
    private String hostTls = "";

    @Getter
    @Setter
    private String ingressClass = "nginx";

    private RuntimeImages runtimeImages;

    protected CoreV1Api coreV1Api = new CoreV1Api();
    protected CustomObjectsApi customObjectsApi = new CustomObjectsApi();

    protected final static V1ServicePort v1ServicePort = new V1ServicePortBuilder().withName("http-function-port")
            .withPort(8080)
            .withProtocol("TCP")
            .withTargetPort(new IntOrString(8080))
            .build();


    public KubelessFunctionService(ApiClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public Mono<KubelessFunction> create(String clientId,
                                         String owner,
                                         String name,
                                         String runtime,
                                         String handler,
                                         String contentUrl) {
        return createFunction(clientId, owner, name, runtime, handler, contentUrl, "");
    }

    @Override
    public Mono<KubelessFunction> create(String clientId, String owner, String name, String runtime, String handler, String contentUrl, byte[] data) {
        return getRuntimeImages()
                .map(runtimeImages1 -> this.getDepFilename(runtime, runtimeImages1))
                .map(depFilename -> this.readZipText(data, depFilename))
                .flatMap(depFile -> createFunction(clientId, owner, name, runtime, handler, contentUrl, depFile));
    }

    @Override
    public Mono<KubelessFunction> get(String clientId, String name) {
        return Mono.create(sink -> sink.onRequest(unused ->
        {
            try {
                Call call = customObjectsApi.getNamespacedCustomObjectCall("kubeless.io",
                        "v1beta1",
                        namespace,
                        "functions",
                        String.format("c%s-%s", clientId, name), null);
                this.client.executeAsync(call,
                        FunctionEntity.class,
                        new ApiCallback<FunctionEntity>() {
                            @Override
                            public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                                if (e.getCode() == 404)
                                    sink.error(ErrorEnum.FUNCTION_NOT_FOUND.getException());
                                else
                                    sink.error(ErrorEnum.UNKNOWN.details(e).getException());
                            }

                            @Override
                            public void onSuccess(FunctionEntity result, int statusCode, Map<String, List<String>> responseHeaders) {
                                sink.success(new KubelessFunction(result));
                            }

                            @Override
                            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                            }

                            @Override
                            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                            }
                        });
            } catch (ApiException e) {
                sink.error(e);
            }
        }));
    }

    @Override
    public Mono<Void> delete(String clientId, String name) {
        return Mono.create(sink -> sink.onRequest(unused -> {
            try {
                Call call = customObjectsApi.deleteNamespacedCustomObjectCall("kubeless.io",
                        "v1beta1",
                        namespace,
                        "functions",
                        String.format("c%s-%s", clientId, name),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                this.client.executeAsync(call, new ApiCallback<Object>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (e.getCode() == 404)
                            sink.error(ErrorEnum.FUNCTION_NOT_FOUND.getException());
                        else
                            sink.error(ErrorEnum.DELETE_FUNCTION_FAILED.details(e).getException());
                    }

                    @Override
                    public void onSuccess(Object result, int statusCode, Map<String, List<String>> responseHeaders) {
                        try {
                            Call c = customObjectsApi.deleteNamespacedCustomObjectCall("kubeless.io",
                                    "v1beta1",
                                    namespace,
                                    "httptriggers",
                                    String.format("c%s-%s", clientId, name),
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null);
                            client.executeAsync(c, new ApiCallback<Object>() {
                                @Override
                                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                                    sink.error(ErrorEnum.DELETE_FUNCTION_FAILED.details(e).getException());
                                }

                                @Override
                                public void onSuccess(Object result, int statusCode, Map<String, List<String>> responseHeaders) {
                                    sink.success();
                                }

                                @Override
                                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                                }

                                @Override
                                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                                }
                            });
                        } catch (ApiException e) {
                            sink.error(ErrorEnum.DELETE_FUNCTION_FAILED.details(e).getException());
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });
            } catch (ApiException e) {
                sink.error(ErrorEnum.DELETE_FUNCTION_FAILED.details(e).getException());
            }
        }));
    }

    @Override
    public Flux<KubelessFunction> list(String clientId) {
        return Flux.create(sink -> sink.onRequest(unused -> {
            try {
                Call call = customObjectsApi.listNamespacedCustomObjectCall("kubeless.io",
                        "v1beta1",
                        namespace,
                        "functions",
                        "",
                        "",
                        "",
                        String.format("clientId=%s", clientId),
                        null,
                        "",
                        null,
                        null,
                        null);
                this.client.executeAsync(call,
                        FunectionListEntity.class,
                        new ApiCallback<FunectionListEntity>() {
                            @Override
                            public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                                sink.error(ErrorEnum.UNKNOWN.details(e).getException());
                            }

                            @Override
                            public void onSuccess(FunectionListEntity result, int statusCode, Map<String, List<String>> responseHeaders) {
                                for (FunctionEntity entity : result.getItems())
                                    sink.next(new KubelessFunction(entity));
                                sink.complete();
                            }

                            @Override
                            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                            }

                            @Override
                            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                            }
                        });
            } catch (ApiException e) {
                sink.error(e);
            }
        }));
    }

    @Override
    public Mono<Collection<String>> getRuntimes() {
        return getRuntimeImages()
                .map(this::listRuntimes);
    }

    @SneakyThrows
    protected String readZipText(byte[] data, String filename) {
        if (data == null || !StringUtils.hasText(filename))
            return "";
        try (ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(data))) {
            ZipEntry entry;

            while ((entry = zipIn.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().equals(filename)) {
                    byte[] bytes = IOUtils.toByteArray(zipIn);
                    zipIn.closeEntry();
                    return new String(bytes);
                }
            }
        }
        return "";
    }

    protected Mono<KubelessFunction> createFunction(String clientId,
                                                    String owner,
                                                    String name,
                                                    String runtime,
                                                    String handler,
                                                    String contentUrl,
                                                    String deps) {

        FunctionEntity entity = new FunctionEntity();
        FunctionEntity.Spec spec = new FunctionEntity.Spec();
        V1ObjectMeta meta = new V1ObjectMeta();
        entity.setMetadata(meta);
        entity.setSpec(spec);
        entity.setKind("Function");
        entity.setApiVersion("kubeless.io/v1beta1");

        meta.setNamespace(namespace);
        meta.setName(String.format("c%s-%s", clientId, name));
        meta.putLabelsItem("clientId", clientId);
        meta.putLabelsItem("owner", owner);
        meta.putLabelsItem("name", name);

        spec.setFunction(contentUrl);
        spec.setRuntime(runtime);
        spec.setFunctionContentType("url+zip");
        spec.setHandler(handler);
        spec.setDependencies(deps);

        V1Deployment deployment = new V1Deployment();
        spec.setDeployment(deployment);

        V1ServiceSpec service = new V1ServiceSpec();
        spec.setService(service);
        service.putSelectorItem("function", meta.getName());
        service.addPortsItem(v1ServicePort);

        HttpTriggerEntity httpTrigger = new HttpTriggerEntity();
        V1ObjectMeta meta1 = new V1ObjectMeta();
        HttpTriggerEntity.Spec spec1 = new HttpTriggerEntity.Spec();
        httpTrigger.setMetadata(meta1);
        httpTrigger.setSpec(spec1);
        httpTrigger.setKind("HTTPTrigger");
        httpTrigger.setApiVersion("kubeless.io/v1beta1");

        meta1.setNamespace(namespace);
        meta1.setName(String.format("c%s-%s", clientId, name));
        meta1.putLabelsItem("clientId", clientId);
        meta1.putLabelsItem("owner", owner);
        meta1.putLabelsItem("name", name);

        spec1.setFunctionName(meta.getName());
        spec1.setHostName(String.format(hostFormat, clientId));
        spec1.setGateway(ingressClass);
        spec1.setPath(name);
        if (StringUtils.hasText(hostTls)) {
//            spec1.setTls(true);
            spec1.setTlsSecret(hostTls);
        }
        spec1.setCorsEnable(true);

        return Mono.create(sink -> sink.onRequest(unused -> {
            try {
                Call call = customObjectsApi.createNamespacedCustomObjectCall("kubeless.io",
                        "v1beta1",
                        namespace,
                        "functions",
                        entity,
                        null,
                        null,
                        null,
                        null);
                this.client.executeAsync(call, FunctionEntity.class, new ApiCallback<FunctionEntity>() {
                    @Override
                    public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                        if (statusCode == 409)
                            sink.error(ErrorEnum.FUNCTION_EXISTS.getException());
                        else
                            sink.error(ErrorEnum.CREATE_FUNCTION_FAILED.details(e).getException());
                    }

                    @Override
                    public void onSuccess(FunctionEntity result, int statusCode, Map<String, List<String>> responseHeaders) {
                        try {
                            Thread.sleep(1000);

                            Call c = customObjectsApi.createNamespacedCustomObjectCall("kubeless.io",
                                    "v1beta1",
                                    namespace,
                                    "httptriggers",
                                    httpTrigger,
                                    null,
                                    null,
                                    null,
                                    null);
                            client.executeAsync(c, new ApiCallback<Object>() {
                                @Override
                                public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                                    sink.error(ErrorEnum.CREATE_FUNCTION_FAILED.details(e).getException());
                                }

                                @Override
                                public void onSuccess(Object resultx, int statusCode, Map<String, List<String>> responseHeaders) {
                                    sink.success(new KubelessFunction(result));
                                }

                                @Override
                                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                                }

                                @Override
                                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                                }
                            });
                        } catch (ApiException | InterruptedException e) {
                            sink.error(ErrorEnum.CREATE_FUNCTION_FAILED.details(e).getException());
                        }
                    }

                    @Override
                    public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {

                    }

                    @Override
                    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {

                    }
                });
            } catch (Exception e) {
                sink.error(ErrorEnum.CREATE_FUNCTION_FAILED.details(e).getException());
            }
        }));
    }

    protected Mono<RuntimeImages> getRuntimeImages() {
        return Mono.justOrEmpty(runtimeImages)
                .switchIfEmpty(Mono.create(sink -> sink.onRequest(unused ->
                        {
                            try {
                                coreV1Api.readNamespacedConfigMapAsync(kubelessConfigName,
                                        kubelessNamespace,
                                        "",
                                        false,
                                        false,
                                        new ApiCallback<>() {
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
                        .map(this::convertRuntimeImages)
                        .map(runtimeImages1 -> this.runtimeImages = runtimeImages1));
    }

    protected String getDepFilename(String runtime, RuntimeImages runtimeImages) {
        for (RuntimeImage image : runtimeImages) {
            if (runtime.startsWith(image.getId())) {
                return image.getDepName();
            }
        }
        return "";
    }

    @SneakyThrows
    protected RuntimeImages convertRuntimeImages(String json) {
        return mapper.readValue(json, RuntimeImages.class);
    }

    @SneakyThrows
    protected Collection<String> listRuntimes(RuntimeImages images) {
        HashSet<String> runtimes = new HashSet<>();
        for (RuntimeImage image : images) {
            if (image.getVersions() == null || image.getVersions().size() == 0)
                continue;
            for (RuntimeImage.Version version : image.getVersions()) {
                runtimes.add(String.format("%s%s", image.getId(), version.getVersion()));
            }
        }
        return runtimes;
    }
}
