package cn.dustlight.fun.kubeless.entities.kubeless;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.gson.annotations.SerializedName;
import io.kubernetes.client.common.KubernetesObject;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpTriggerEntity implements KubernetesObject {

    private V1ObjectMeta metadata;
    private String kind;
    private String apiVersion;
    private Spec spec;


    @Getter
    @Setter
    public static class Spec {

        @JsonAlias("basic-auth-secret")
        @SerializedName("basic-auth-secret")
        private String basicAuthSecret;

        @JsonAlias("cors-enable")
        @SerializedName("cors-enable")
        private boolean corsEnable;

        @JsonAlias("function-name")
        @SerializedName("function-name")
        private String functionName;

        private String gateway;

        @JsonAlias("host-name")
        @SerializedName("host-name")
        private String hostName;

        private String path;

        private boolean tls;

        @JsonAlias("tls-secret")
        @SerializedName("tls-secret")
        private String tlsSecret;

    }
}
