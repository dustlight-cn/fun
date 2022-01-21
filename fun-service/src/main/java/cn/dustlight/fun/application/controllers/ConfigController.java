package cn.dustlight.fun.application.controllers;

import cn.dustlight.auth.client.reactive.AuthClientProperties;
import cn.dustlight.fun.kubeless.KubelessProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/v1")
@CrossOrigin
@Tag(name = "Configs", description = "配置资源")
public class ConfigController {

    @Autowired
    private KubelessProperties kubelessProperties;

    @Autowired
    private AuthClientProperties clientProperties;

    @Value("${dustlight.fun.authorization-endpoint}")
    private String authorizationUri;

    private static Config config;

    @Operation(summary = "获取函数")
    @GetMapping(value = "/config")
    public Mono<Config> getConfiguration() {
        return Mono.justOrEmpty(config)
                .switchIfEmpty(Mono.just(config = new Config(clientProperties.getApiEndpoint(),
                        kubelessProperties.getHostFormat(),
                        clientProperties.getTokenUri(),
                        authorizationUri)));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Config {

        private String authEndpoint;
        private String hostFormat;
        private String tokenUri;
        private String authorizationUri;

    }
}
