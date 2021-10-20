package plus.fun.application;

import org.springframework.util.StringUtils;
import plus.auth.client.reactive.ReactiveAuthClient;
import plus.auth.resources.core.AuthPrincipal;
import plus.fun.core.exceptions.ErrorEnum;
import reactor.core.publisher.Mono;

public class ClientUtils {

    public static Mono<String> obtainClientId(ReactiveAuthClient client, String clientId, AuthPrincipal principal) {
        if (StringUtils.hasText(clientId) && !client.equals(principal.getClientId()))
            return client.isClientMember(principal.getUid(), clientId)
                    .flatMap(aBoolean -> aBoolean ? Mono.just(clientId) : Mono.error(ErrorEnum.ACCESS_DENIED.getException()));
        return Mono.just(principal.getClientId());
    }
}
