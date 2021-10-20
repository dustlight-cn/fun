package plus.fun.application.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import plus.auth.client.reactive.ReactiveAuthClient;
import plus.auth.resources.core.AuthPrincipal;
import plus.fun.application.ClientUtils;
import plus.fun.core.service.FunctionService;
import plus.fun.core.service.FunctionStore;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
@SecurityRequirement(name = "auth")
@CrossOrigin
public class FunctionController {

    @Autowired
    private FunctionStore store;

    @Autowired
    private FunctionService functionService;

    @PutMapping(value = "/function/{name}", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono setFunction(
            @PathVariable(name = "name") String name,
            @RequestParam(name = "cid", required = false) String clientId,
            @RequestBody byte[] data,
            ReactiveAuthClient reactiveAuthClient,
            AuthPrincipal principal) {
        return ClientUtils.obtainClientId(reactiveAuthClient, clientId, principal)
                .flatMap(cid -> store.store(cid, name, data));
    }

    @GetMapping("/runtimes")
    public Flux<String> getRuntimes() {
        return functionService.getRuntimes();
    }
}
