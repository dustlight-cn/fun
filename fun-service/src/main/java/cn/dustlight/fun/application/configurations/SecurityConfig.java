package cn.dustlight.fun.application.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import cn.dustlight.auth.resources.AuthSecurityWebFilterChainConfiguration;

@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig extends AuthSecurityWebFilterChainConfiguration {

    @Override
    protected ServerHttpSecurity configure(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/v1/config").permitAll()
                .pathMatchers("/v*/**").authenticated()
                .anyExchange().permitAll()
                .and();
    }
}
