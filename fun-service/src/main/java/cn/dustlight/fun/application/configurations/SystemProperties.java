package cn.dustlight.fun.application.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "dustlight.fun.system")
public class SystemProperties {

    private String systemFunctionPrefix = "system.";

}
