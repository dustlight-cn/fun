package cn.dustlight.fun.store;

import cn.dustlight.storage.core.RestfulStorage;
import cn.dustlight.storage.core.StorableObject;
import lombok.Getter;
import lombok.Setter;
import cn.dustlight.fun.core.service.FunctionStore;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Getter
@Setter
public class FunctionStorage implements FunctionStore {

    private RestfulStorage storage;
    private String prefix = "";
    private long getExpiration = 1000L * 60 * 5;
    private long storeExpiration = 1000L * 60L * 60L * 24L * 365L * 10L;

    @Override
    public Mono<String> store(String clientId, String name, byte[] data) {
        return Mono.just(generateKey(clientId, name))
                .flatMap(key -> Mono.fromCallable(() -> storage.put(key, new ByteArrayStorableObject(data))))
                .flatMap(storableObject -> Mono.fromCallable(() -> storage.generateGetUrl(storableObject.getKey(), storeExpiration)));
    }

    @Override
    public Mono<String> getUrl(String client, String name) {
        return Mono.fromCallable(() -> storage.generateGetUrl(generateKey(client, name), getExpiration));
    }


    protected String generateKey(String clientId, String name) {
        return String.format("%s%s/%s.zip", prefix, clientId, name);
    }

    private static class ByteArrayStorableObject implements StorableObject {

        private byte[] data;

        ByteArrayStorableObject(byte[] data) {
            this.data = data;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return null;
        }

        @Override
        public String getKey() {
            return null;
        }

        @Override
        public int getPermission() {
            return 0;
        }
    }
}
