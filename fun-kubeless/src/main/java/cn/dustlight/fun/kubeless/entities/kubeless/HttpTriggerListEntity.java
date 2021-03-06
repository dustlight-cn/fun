package cn.dustlight.fun.kubeless.entities.kubeless;

import io.kubernetes.client.common.KubernetesListObject;
import io.kubernetes.client.openapi.models.V1ListMeta;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HttpTriggerListEntity implements KubernetesListObject {

    private V1ListMeta metadata;
    private String apiVersion;
    private String kind;
    private List<HttpTriggerEntity> items;

}
