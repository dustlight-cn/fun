package plus.fun.kubeless;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "plus.fun.kubeless")
public class KubelessProperties {

    private String functionsNamespace = "functions";
    private String kubelessNamespace = "kubeless";
    private String kubelessConfigMap = "kubeless-config";
    private String hostFormat = "%s.functions.wgv.ink";
    private String ingressClass = "nginx";

}
