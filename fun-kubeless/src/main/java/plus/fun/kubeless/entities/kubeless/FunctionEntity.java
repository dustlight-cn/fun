package plus.fun.kubeless.entities.kubeless;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.google.gson.annotations.SerializedName;
import io.kubernetes.client.common.KubernetesObject;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1ServiceSpec;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionEntity implements KubernetesObject {

    private V1ObjectMeta metadata;
    private String kind;
    private String apiVersion;
    private Spec spec;

    @Getter
    @Setter
    public static class Spec {

        private String checksum;
        private V1Deployment deployment;
        @JsonAlias("deps")
        @SerializedName("deps")
        private String dependencies;
        private String function;

        @JsonAlias("function-content-type")
        @SerializedName("function-content-type")
        private String functionContentType;
        private String handler;

        private String runtime;
        private V1ServiceSpec service;
        private String timeout;

    }
}
