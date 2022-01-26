package cn.dustlight.fun.kubeless;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "dustlight.fun.kubeless")
public class KubelessProperties {

    private String functionsNamespace = "functions";
    private String kubelessNamespace = "kubeless";
    private String kubelessConfigMap = "kubeless-config";
    private String hostFormat = "%s.fun.dustlight.cn";
    private String hostTls = "";
    private String ingressClass = "nginx";

}
