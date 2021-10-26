package plus.fun.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import plus.auth.client.reactive.ReactiveAuthClient;
import plus.auth.resources.AuthResourceServerProperties;
import plus.auth.resources.core.AuthPrincipal;
import plus.fun.application.ClientUtils;
import plus.fun.application.configurations.SystemProperties;
import plus.fun.core.entities.Function;
import plus.fun.core.service.FunctionService;
import plus.fun.core.service.FunctionStore;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/v1")
@SecurityRequirement(name = "auth")
@CrossOrigin
@Tag(name = "Functions", description = "函数相关资源")
public class FunctionController {

    @Autowired
    private FunctionStore store;

    @Autowired
    private FunctionService<? extends Function> functionService;

    @Autowired
    private SystemProperties systemProperties;

    @Autowired
    private AuthResourceServerProperties resourceServerProperties;

    @Operation(summary = "创建函数")
    @PostMapping(value = "/function/{name}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<Function> createFunction(@PathVariable(name = "name") String name,
                                         @RequestParam(name = "runtime", defaultValue = "nodejs14") String runtime,
                                         @RequestParam(name = "handler", defaultValue = "sl_handler.handler") String handler,
                                         @RequestParam(name = "cid", required = false) String clientId,
                                         @RequestBody byte[] data,
                                         ReactiveAuthClient reactiveAuthClient,
                                         AuthPrincipal principal) {
        return ClientUtils.obtainClientIdRequireMember(reactiveAuthClient, clientId, principal)
                .map(this::switchSystemPrefixIfClientIdMatch)
                .flatMap(cid -> store.store(cid, name, data)
                        .flatMap(url -> functionService.create(cid, principal.getUidString(), name, runtime, handler, url)));
    }

    @Operation(summary = "获取函数")
    @GetMapping(value = "/function/{name}")
    public Mono<Function> getFunction(@PathVariable(name = "name") String name,
                                      @RequestParam(name = "cid", required = false) String clientId,
                                      ReactiveAuthClient reactiveAuthClient,
                                      AuthPrincipal principal) {
        return ClientUtils.obtainClientIdRequireMember(reactiveAuthClient, clientId, principal)
                .map(this::switchSystemPrefixIfClientIdMatch)
                .flatMap(cid -> functionService.get(cid, name));
    }

    @Operation(summary = "获取函数代码")
    @GetMapping(value = "/function/{name}/data")
    public Mono<ResponseEntity> getFunctionData(@PathVariable(name = "name") String name,
                                                @RequestParam(name = "cid", required = false) String clientId,
                                                ServerWebExchange exchange,
                                                ReactiveAuthClient reactiveAuthClient,
                                                AuthPrincipal principal) {
        return ClientUtils.obtainClientIdRequireMember(reactiveAuthClient, clientId, principal)
                .map(this::switchSystemPrefixIfClientIdMatch)
                .flatMap(cid -> functionService.get(cid, name)
                        .flatMap(function -> store.getUrl(cid, function.getName())))
                .map(url -> URI.create(url))
                .map(uri -> ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).location(uri).build());
    }

    @Operation(summary = "删除函数")
    @DeleteMapping(value = "/function/{name}")
    public Mono<Void> deleteFunction(@PathVariable(name = "name") String name,
                                     @RequestParam(name = "cid", required = false) String clientId,
                                     ReactiveAuthClient reactiveAuthClient,
                                     AuthPrincipal principal) {
        return ClientUtils.obtainClientIdRequireMember(reactiveAuthClient, clientId, principal)
                .map(this::switchSystemPrefixIfClientIdMatch)
                .flatMap(cid -> functionService.delete(cid, name));
    }

    @Operation(summary = "列出函数")
    @GetMapping(value = "/functions")
    public Flux<Function> listFunctions(@RequestParam(name = "cid", required = false) String clientId,
                                        ReactiveAuthClient reactiveAuthClient,
                                        AuthPrincipal principal) {
        return ClientUtils.obtainClientIdRequireMember(reactiveAuthClient, clientId, principal)
                .map(this::switchSystemPrefixIfClientIdMatch)
                .flatMapMany(cid -> functionService.list(cid));
    }

    @GetMapping("/runtimes")
    @Operation(summary = "查询支持的运行环境")
    public Mono<Collection<String>> getRuntimes() {
        return functionService.getRuntimes();
    }

    protected String switchSystemPrefixIfClientIdMatch(String clientId) {
        if (StringUtils.hasText(clientId) && StringUtils.hasText(resourceServerProperties.getClientId()) &&
                clientId.equals(resourceServerProperties.getClientId())) {
            return systemProperties.getSystemFunctionPrefix();
        }
        return clientId;
    }
}
