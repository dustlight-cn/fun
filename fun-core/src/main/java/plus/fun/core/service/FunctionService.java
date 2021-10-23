package plus.fun.core.service;

import plus.fun.core.entities.Function;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface FunctionService<T extends Function> {

    Mono<T> create(String clientId,
                   String owner,
                   String name,
                   String runtime,
                   String handler,
                   String contentUrl);

    Mono<T> get(String clientId, String name);

    Mono<Void> delete(String clientId, String name);

    Flux<T> list(String clientId);

    Mono<Collection<String>> getRuntimes();
}
