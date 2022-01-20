package cn.dustlight.fun.core.service;

import reactor.core.publisher.Mono;

public interface FunctionStore {

    Mono<String> store(String clientId, String name, byte[] data);

    Mono<String> getUrl(String client, String name);

}
